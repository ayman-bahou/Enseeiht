package GestionMonopoly.Cartes;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

/**
 * La classe CarteArgent représente une carte qui donne ou retire de l'argent à un joueur dans le Monopoly.
 */
public class CarteArgent extends Carte {

    private int montant;

    /**
     * Constructeur de la classe CarteArgent.
     * @param titre Le titre de la carte.
     * @param description La description de la carte.
     * @param montant Le montant d'argent associé à la carte (négatif si débiter et positif si créditer).
     */
    public CarteArgent(String titre, String description, int montant){
        super(titre, description);
        this.montant = montant;
    }

    /**
     * Action associée à la carte.
     * Cette méthode crédite ou débite un joueur du montant spécifié par la carte.
     * @param joueur Le joueur concerné par l'action de la carte.
     * @param plateau Le plateau de jeu sur lequel se déroule l'action.
     */
    @Override
    public void actionCarte(JoueurMonopoly joueur, Plateau plateau){
        joueur.crediter(montant);
        // TODO : A compléter pour afficher une fenêtre qui présente la carte
    }
}