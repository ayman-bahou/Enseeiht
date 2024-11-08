package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import Interface_graphique.FenetreCases.FenetreMessageSimple;
import java.awt.Color;

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

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        System.out.println(joueur.getNom() + " paie " + this.montantImpot + "€ d'impôts.");
        joueur.debiter(this.montantImpot);

        // Ouvrir la fenêtre pour informer le joueur
        FenetreMessageSimple fenetre = new FenetreMessageSimple(joueur.getNom() + " paie " + this.montantImpot + "€ d'impôts !", new Color(0xd5f5e3), Color.BLACK);
        plateau.setFenetreAction(fenetre);
        fenetre.setVisible(true);
    }
}
