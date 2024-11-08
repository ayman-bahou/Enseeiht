package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import Interface_graphique.FenetreCases.FenetreMessageSimple;
import java.awt.Color;

public class CaseDirectionPrison extends Case {
	

    
    /**
     * Constructeur pour initialiser le nom et l'id de la case
     * @param nom String  le nom de la case
     * @param id int de la case
     */
    public CaseDirectionPrison(String nom, int id) {
    	super(nom, id);

    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        System.out.println(joueur.getNom() + " est envoyé en prison. ");
        joueur.setEstEnPrison(true);
        joueur.resetToursEnPrison();
        plateau.setPositionJoueur(joueur, Plateau.ID_CASE_PRISON);

        
        // Ouvrir la fenêtre pour informer le joueur
        FenetreMessageSimple fenetre = new FenetreMessageSimple(joueur.getNom() + ", vous êtes en état d'arrestation ! \n Direction la prison.", new Color(0xd5f5e3), Color.BLACK);
        plateau.setFenetreAction(fenetre);
        fenetre.setVisible(true);
    }
}