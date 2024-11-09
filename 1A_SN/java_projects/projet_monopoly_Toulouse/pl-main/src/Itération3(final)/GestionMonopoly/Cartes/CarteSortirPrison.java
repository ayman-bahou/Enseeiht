package GestionMonopoly.Cartes;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

/**
 * La classe CarteSortirPrison représente une carte permettant à un joueur de sortir de prison dans le Monopoly.
 */
public class CarteSortirPrison extends Carte {

    /**
     * Constructeur de la classe CarteSortirPrison.
     * @param titre Le titre de la carte.
     * @param description La description de la carte.
     */
    public CarteSortirPrison(String titre, String description){
        super(titre, description);
    }

    /**
     * Action associée à la carte.
     * Cette méthode permet à un joueur de sortir de prison en ajoutant une carte de sortie de prison à son inventaire.
     * @param joueur Le joueur concerné par l'action de la carte.
     * @param plateau Le plateau de jeu sur lequel se déroule l'action.
     */
    @Override
    public void actionCarte(JoueurMonopoly joueur, Plateau plateau){
        joueur.addCarteSortiePrison();
        // TODO : A compléter pour afficher une fenêtre qui présente la carte
    }
}
