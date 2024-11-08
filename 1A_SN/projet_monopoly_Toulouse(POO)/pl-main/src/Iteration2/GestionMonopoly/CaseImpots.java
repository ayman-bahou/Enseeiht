package GestionMonopoly;

public class CaseImpots extends Case {
	
    private int montantImpot;
    

    /**
     * Constructeur pour initialiser le nom et le montant de l'impôt pour la case.
     * @param nom String  le nom de la case
     * @param montantImpot int  le montant à payer pour l'impôt
     */
    public CaseImpots(String nom, int id , int montantImpot) {
    	super(nom, id);
    	assert(montantImpot >= 0);
        this.montantImpot = montantImpot;
    }

    /**
     * Méthode pour exécuter l'action de la case impôt, 
     * retirant de l'argent du joueur et l'ajoutant au parc gratuit.
     * @param joueur JoueurMonopoly  le joueur qui doit payer l'impôt
     */
    
    public void action(JoueurMonopoly joueur) {
        System.out.println(joueur.getNom() + " paie " + this.montantImpot + "€ d'impôts.");

        joueur.retirerArgent(this.montantImpot);

    }
}
//PS : j'ai (Atif) effacé les deux lignes sur le parcgratuit car je ne voyais l'utilité, on peut initialiser
//le parc gratuit par une caseimpots dont le montantImpots = 0, ça évite de créer une classe de plus :), de même pour la
// visite simple du prison