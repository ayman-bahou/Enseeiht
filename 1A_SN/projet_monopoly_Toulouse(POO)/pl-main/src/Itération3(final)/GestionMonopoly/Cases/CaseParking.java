package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import Interface_graphique.FenetreCases.FenetreMessageSimple;
import java.awt.Color;

public class CaseParking extends Case {
	

    
    /**
     * Constructeur pour initialiser le nom et l'id de la case
     * @param nom String  le nom de la case
     * @param id int de la case
     */
    public CaseParking(String nom, int id) {
    	super(nom, id);

    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        // Ouvrir la fenêtre pour informer le joueur
        FenetreMessageSimple fenetre = new FenetreMessageSimple(joueur.getNom() + " est tombé sur la case parking gratuit. \n Il ne s'y passe ... rien du tout !", new Color(0xd5f5e3), Color.BLACK);
        plateau.setFenetreAction(fenetre);
        fenetre.setVisible(true);
    }
}
