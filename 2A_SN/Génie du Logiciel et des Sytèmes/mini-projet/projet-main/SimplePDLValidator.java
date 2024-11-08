package simplepdl.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import simplepdl.Allocation;
import simplepdl.Guidance;
import simplepdl.ProcessElement;
import simplepdl.Ressource;
import simplepdl.SimplepdlPackage;
import simplepdl.WorkDefinition;
import simplepdl.WorkSequence;
import simplepdl.util.SimplepdlSwitch;

/**
 * RÃ©alise la validation d'un EObject issu de SimplePDL (en thÃ©orie, d'un process).
 * Cet classe visite le modÃ¨le et utilise les caseXXX pour rediriger l'algo vers la
 * bonne mÃ©thode.
 * Attention, lorsqu'une classe est un parent il faut aller faire la visite des enfants
 * manuellement (cf. caseProcess typiquement).
 * 
 * La classe Switch exige un paramÃ¨tre de gÃ©nÃ©ricitÃ© (et gÃ¨re une partie de la visite Ã 
 * base de comparaison Ã  null). Ici le paramÃ¨tre est un boolÃ©en mais en rÃ©alitÃ© on ne
 * s'en sert pas...
 * 
 * @author Guillaume Dupont
 * @version 0.1
 */
public class SimplePDLValidator extends SimplepdlSwitch<Boolean> {
	/**
	 * Expression rÃ©guliÃ¨re qui correspond Ã  un identifiant bien formÃ©.
	 */
	private static final String IDENT_REGEX = "^[A-Za-z_][A-Za-z0-9_]*$";
	
	/**
	 * RÃ©sultat de la validation (Ã©tat interne rÃ©initialisÃ© Ã  chaque nouvelle validation).
	 */
	private ValidationResult result = null;
	
	/**
	 * Construire un validateur
	 */
	public SimplePDLValidator() {}
	
	/**
	 * Lancer la validation et compiler les rÃ©sultats dans un ValidationResult.
	 * Cette mÃ©thode se charge de crÃ©er un rÃ©sultat de validation vide puis de
	 *  visiter les process prÃ©sents dans la ressource.
	 * @param resource resource Ã  valider
	 * @return rÃ©sultat de validation
	 */
	public ValidationResult validate(Resource resource) {
		this.result = new ValidationResult();
		
		for (EObject object : resource.getContents()) {
			this.doSwitch(object);
		}
		
		return this.result;
	}


	/**
	 * MÃ©thode appelÃ©e lorsque l'objet visitÃ© est un Process.
	 * Cet mÃ©thode amorce aussi la visite des Ã©lÃ©ments enfants.
	 * @param object Ã©lÃ©ment visitÃ©
	 * @return rÃ©sultat de validation (null ici, ce qui permet de poursuivre la visite
	 * vers les classes parentes, le cas Ã©chÃ©ant)
	 */
	@Override
	public Boolean caseProcess(simplepdl.Process object) {
		// Contraintes sur process
		this.result.recordIfFailed(
				object.getName() != null && object.getName().matches(IDENT_REGEX), 
				object, 
				"Le nom du process ne respecte pas les conventions Java");
		
		// Visite
		for (ProcessElement pe : object.getProcessElements()) {
			this.doSwitch(pe);
		}
		for (ProcessElement pe : object.getProcessElements()) {
			if (pe.eClass().getClassifierID() == SimplepdlPackage.WORK_DEFINITION) {
				for (Allocation e : ((WorkDefinition) pe).getAllocation()) {
					this.doSwitch(e);
				}
				
			}
			
		}
		
		return null;
	}

	/**
	 * MÃ©thode appelÃ©e lorsque l'objet visitÃ© est un ProcessElement (ou un sous type).
	 * @param object Ã©lÃ©ment visitÃ©
	 * @return rÃ©sultat de validation (null ici, ce qui permet de poursuivre la visite
	 * vers les classes parentes, le cas Ã©chÃ©ant)
	 */
	@Override
	public Boolean caseProcessElement(ProcessElement object) {
		return null;
	}

	/**
	 * MÃ©thode appelÃ©e lorsque l'objet visitÃ© est une WorkDefinition.
	 * @param object Ã©lÃ©ment visitÃ©
	 * @return rÃ©sultat de validation (null ici, ce qui permet de poursuivre la visite
	 * vers les classes parentes, le cas Ã©chÃ©ant)
	 */
	@Override
	public Boolean caseWorkDefinition(WorkDefinition object) {
		// Contraintes sur WD
		this.result.recordIfFailed(
				object.getName() != null && object.getName().matches(IDENT_REGEX), 
				object, 
				"Le nom de l'activitÃ© ne respecte pas les conventions Java");
		
		this.result.recordIfFailed(
				object.getProcess().getProcessElements().stream()
					.filter(p -> p.eClass().getClassifierID() == SimplepdlPackage.WORK_DEFINITION)
					.allMatch(pe -> (pe.equals(object) || !((WorkDefinition) pe).getName().contains(object.getName()))),
				object, 
				"Le nom de l'activitÃ© (" + object.getName() + ") n'est pas unique");

		return null;
	}

	/**
	 * MÃ©thode appelÃ©e lorsque l'objet visitÃ© est une WorkSequence.
	 * @param object Ã©lÃ©ment visitÃ©
	 * @return rÃ©sultat de validation (null ici, ce qui permet de poursuivre la visite
	 * vers les classes parentes, le cas Ã©chÃ©ant)
	 */
	@Override
	public Boolean caseWorkSequence(WorkSequence object) {
		// Contraintes sur WS
		this.result.recordIfFailed(
				!object.getPredecessor().equals(object.getSuccessor()), 
				object,
				"La dÃ©pendance relie l'activitÃ© " + object.getPredecessor().getName() + " Ã  elle-mÃªme");
		this.result.recordIfFailed(
				object.getProcess().getProcessElements().stream()
				.filter(p -> p.eClass().getClassifierID() == SimplepdlPackage.WORK_SEQUENCE)
				.allMatch(pe->(pe.equals(object)||!(((WorkSequence) pe).getLinkType().equals(object.getLinkType()))||!((WorkSequence) pe).getPredecessor().equals(object.getPredecessor())||!((WorkSequence) pe).getSuccessor().equals(object.getSuccessor()))),
				object,
				"On ne doit pas avoir deux dépendances différentes de même type entre deux mêmes activités"
				
				);
		return null;
	}

	/**
	 * MÃ©thode appelÃ©e lorsque l'objet visitÃ© est une Guidance.
	 * @param object Ã©lÃ©ment visitÃ©
	 * @return rÃ©sultat de validation (null ici, ce qui permet de poursuivre la visite
	 * vers les classes parentes, le cas Ã©chÃ©ant)
	 */
	@Override
	public Boolean caseGuidance(Guidance object) {
	    this.result.recordIfFailed(
				object.getText() != null && object.getText().matches(IDENT_REGEX), 
				object, 
				"Le nom de la guidance ne respecte pas les conventions Java");
		return null;
	}
	
	@Override
	public Boolean caseRessource(Ressource object) {
		this.result.recordIfFailed(
				object.getName() != null && object.getName().matches(IDENT_REGEX),
				object,
				"Le nom de l'activitÃ© ne respecte pas les conventions Java");
		this.result.recordIfFailed(
				object.getProcess().getProcessElements().stream()
					.filter(p -> p.eClass().getClassifierID() == SimplepdlPackage.RESSOURCE)
					.allMatch(pe -> (pe.equals(object) || !((Ressource) pe).getName().contains(object.getName()))),
				object, 
				"Le nom de lA ressource (" + object.getName() + ") n'est pas unique");
		this.result.recordIfFailed(
				object.getQuantite()>0,
				object,
				"la quantite d'une ressource doit être strictement positive");
		return null;
	}
	
	@Override
	public Boolean caseAllocation(Allocation object) {
		this.result.recordIfFailed(
				object.getRessource().getQuantite()>=object.getOccurences(),
				object,
				"la quantité d'une ressource doit être sup ou égale au nbr d'occurences alloué par une activité");
		return null; 
	}
	/**
	 * Cas par dÃ©faut, lorsque l'objet visitÃ© ne correspond pas Ã  un des autres cas.
	 * Cette mÃ©thode est aussi appelÃ©e lorsqu'une mÃ©thode renvoie null (comme une sorte de
	 * fallback).
	 * On pourrait implÃ©menter le switch diffÃ©remment, en ne renvoyant null dans les autres
	 * mÃ©thodes que si la contrainte ne sert Ã  rien, et se servir de cette mÃ©thode pour
	 * identifier les Ã©lÃ©ments Ã©trangers (qui de toute faÃ§on ne doivent pas exister).
	 * C'est aussi la mÃ©thode appelÃ©e si on ne redÃ©fini pas un des caseXXX.
	 * @param object objet visitÃ©
	 * @return rÃ©sultat, null ici
	 */
	@Override
	public Boolean defaultCase(EObject object) {
		return null;
	}
	
	
}
