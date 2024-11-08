package GestionMonopoly.Cartes;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

/**
 * La classe CarteDeplacement représente une carte qui déplace un joueur sur le plateau de jeu dans le Monopoly.
 */
public class CarteDeplacement extends Carte {

    private int deplacement;

    /**
     * Constructeur de la classe CarteDeplacement.
     * @param titre Le titre de la carte.
     * @param description La description de la carte.
     * @param deplacement Le nombre de cases de déplacement associé à la carte.
     */
    public CarteDeplacement(String titre, String description, int deplacement){
        super(titre, description);
        this.deplacement = deplacement;
    }

    /**
     * Action associée à la carte.
     * Cette méthode déplace un joueur sur le plateau de jeu.
     * @param joueur Le joueur concerné par l'action de la carte.
     * @param plateau Le plateau de jeu sur lequel se déroule l'action.
     */
    @Override
    public void actionCarte(JoueurMonopoly joueur, Plateau plateau){
        plateau.deplacerJoueur(joueur, this.deplacement);
        // TODO : A compléter pour afficher une fenêtre qui présente la carte
    }
}