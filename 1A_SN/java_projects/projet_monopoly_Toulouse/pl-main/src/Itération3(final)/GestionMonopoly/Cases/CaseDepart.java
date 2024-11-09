package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import Interface_graphique.FenetreCases.FenetreMessageSimple;
import java.awt.Color;

/**
 * La classe {@code CaseDepart} représente la case de départ dans le jeu de Monopoly.
 * Lorsqu'un joueur passe par cette case, il reçoit un montant d'argent.
 */
public class CaseDepart extends Case {

	private int montant;

    public CaseDepart(String nom, int id,int montant) {
        super(nom, id);
		this.montant = montant;
	}

    public void action(JoueurMonopoly joueur, Plateau plateau) {
		System.out.println(joueur.getNom() + " se voit crediter de " + this.montant);
        joueur.crediter(this.montant);

        // Ouvrir la fenêtre pour informer le joueur
        FenetreMessageSimple fenetre = new FenetreMessageSimple(joueur.getNom() + " est passé par la case départ \n et a reçu " + this.montant + "€ !", new Color(0xd5f5e3), Color.BLACK);
        plateau.setFenetreAction(fenetre);
        fenetre.setVisible(true);
    }
}
