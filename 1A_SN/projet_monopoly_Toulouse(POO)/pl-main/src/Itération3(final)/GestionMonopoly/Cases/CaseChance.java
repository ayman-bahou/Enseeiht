package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import GestionMonopoly.Cartes.Carte;

/**
 * La classe {@code CaseChance} représente une case de type "Chance" dans le jeu de Monopoly.
 * Lorsqu'un joueur atterrit sur cette case, il doit tirer une carte "Chance".
 */
public class CaseChance extends Case {


    public CaseChance(String nom, int id) {
        super(nom, id);
    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        Carte carte = plateau.tirerCarteChance();
        System.out.println(joueur.getNom() + " tire la carte " + carte.getNom());
        carte.actionCarte(joueur, plateau);
    }
}
