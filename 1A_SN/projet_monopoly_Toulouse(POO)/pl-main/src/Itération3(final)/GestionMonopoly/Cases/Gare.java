package GestionMonopoly.Cases;


import java.awt.Color;
import javax.swing.JFrame;
import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import Interface_graphique.FenetreCases.FenetreAchatCase;
import Interface_graphique.FenetreCases.FenetreMessageSimple;

/**
 * La classe CaseGare représente une case de type gare sur le plateau du Monopoly.
 * Elle hérite de la classe Case.
 */
public class Gare extends CaseAchetable {
    
    private int[] loyer; // Les loyers en fonction du nombre de gares possédées
    
    /**
     * Constructeur de la classe CaseGare.
     * @param nom Le nom de la gare.
     * @param id L'identifiant de la gare sur le plateau.
     * @param valeurAchat Le prix d'achat de la gare.
     * @param loyer Les loyers en fonction du nombre de gares possédées.
     */
    public Gare(String nom, int id, int valeurAchat, int[] loyer) {
        super(nom, id,valeurAchat);
        this.loyer = loyer;
    }

    /**
     * Obtenir le loyer actuel de la gare en fonction du nombre de gares possédées par le propriétaire.
     * @return Le loyer actuel de la gare.
     */
    public int getLoyer() {
        if (!estAchetable())
            return this.loyer[getProprietaire().getNbGares()];
        return 0; // Retourne 0 si la gare n'a pas de propriétaire
    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        System.out.println("Le joueur est tombé sur la gare : " + this.getNom());

        if (this.estAchetable()) {
            JFrame fenetre = new FenetreAchatCase(joueur, this);
            plateau.setFenetreAction(fenetre);
            fenetre.setVisible(true);
        } else if (!(this.getProprietaire() == joueur)){
            joueur.debiter(this.getLoyer());

            // Ouvrir la fenêtre pour informer le joueur
            FenetreMessageSimple fenetre = new FenetreMessageSimple(joueur.getNom() + " paie " + this.getLoyer() + "M$ de loyer à " + this.getProprietaire().getNom() + " !", new Color(0xd5f5e3), Color.BLACK);
            plateau.setFenetreAction(fenetre);
            fenetre.setVisible(true);
        }
    }
}
