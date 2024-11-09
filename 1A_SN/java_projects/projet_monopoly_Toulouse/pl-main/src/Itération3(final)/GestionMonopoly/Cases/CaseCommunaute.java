package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import GestionMonopoly.Cartes.Carte;

/**
 * La classe {@code CaseCommunaute} représente une case de type "Communauté" dans le jeu de Monopoly.
 * Lorsqu'un joueur atterrit sur cette case, il doit tirer une carte "Communauté".
 */
public class CaseCommunaute extends Case {

    public CaseCommunaute(String nom, int id) {
        super(nom, id);
    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        Carte carte = plateau.tirerCarteCommunaute();
        System.out.println(joueur.getNom() + " tire la carte " + carte.getNom());
        carte.actionCarte(joueur, plateau);
    }
}
