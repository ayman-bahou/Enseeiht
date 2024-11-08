package petrinet.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import petrinet.Arc;
import petrinet.noeud;
import petrinet.petriNet;
import petrinet.petriNetElement;
import petrinet.PetrinetPackage;
import petrinet.Place;
import petrinet.Transition;
import petrinet.util.PetrinetSwitch;


public class petrinetValidator extends PetrinetSwitch<Boolean> {
	/**
	 * Expression rÃ©guliÃ¨re qui correspond Ã  un identifiant bien formÃ©.
	 */
	private static final String IDENT_REGEX = "^[A-Za-z_][A-Za-z0-9_]*$";
	
	/**
	 * RÃ©sultat de la validation (Ã©tat interne rÃ©initialisÃ© Ã  chaque nouvelle validation).
	 */
	private ValidationResultpetri result = null;
	
	/**
	 * Construire un validateur
	 */
	public petrinetValidator() {}
	
	
	
	public ValidationResultpetri validate(Resource resource) {
		this.result = new ValidationResultpetri();
		
		for (EObject object : resource.getContents()) {
			this.doSwitch(object);
		}
		
		return this.result;
	}


	@Override
	public Boolean casepetriNet(petrinet.petriNet object) {
		// Contraintes sur process
		this.result.recordIfFailed(
				object.getName() != null && object.getName().matches(IDENT_REGEX), 
				object, 
				"Le nom du process ne respecte pas les conventions Java");
		for (petriNetElement pe : object.getPetriElement()) {
			this.doSwitch(pe);
		}
		
		return null;
	}
	
	@Override
	public Boolean caseTransition(petrinet.Transition object) {
		// Contraintes sur process
		this.result.recordIfFailed(
				object.getName() != null && object.getName().matches(IDENT_REGEX), 
				object, 
				"Le nom du process ne respecte pas les conventions Java");
		
		return null;
	}
	
	@Override
	public Boolean casePlace(petrinet.Place object) {
		// Contraintes sur process
		this.result.recordIfFailed(
				object.getName() != null && object.getName().matches(IDENT_REGEX), 
				object, 
				"Le nom du process ne respecte pas les conventions Java");
		this.result.recordIfFailed(
				object.getJetons() >= 0,
				object,
				"le nombre de jetons doi etre positif");
		

		return null;
		
	}
	@Override
	public Boolean caseArc(petrinet.Arc object) {
		// Contraintes sur process
		this.result.recordIfFailed(
				object.getJetons()>=1,
				object, 
				"le nombre de jetons doi etre superieur ou égale à 1");	
		this.result.recordIfFailed(
				((object.getSource() instanceof Place)&&(object.getDestination() instanceof Transition)||((object.getSource() instanceof Transition) && (object.getDestination() instanceof Place))),
				object,
				"un arc ne relie pas deux noueds de même type");
				
		return null;
	}
	
	@Override
	public Boolean defaultCase(EObject object) {
		return null;
	}
	
}





	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
