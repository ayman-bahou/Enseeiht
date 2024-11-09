package Interface_graphique;

import java.awt.*;
import GestionMonopoly.JoueurMonopoly;
import Interface_graphique.Utilitaires.CerclePanel;

/**
 * La classe Pion représente un pion de jeu dans l'interface graphique du Monopoly.
 * Chaque pion est associé à un joueur et possède une couleur déterminée.
 */
public class Pion extends CerclePanel {

    final int lengthPionAbsolute = 40;

    private JoueurMonopoly joueur;
    private Color couleur;
    private int diameter = lengthPionAbsolute;
    private double scaleFactor;

    /**
     * Constructeur de la classe Pion.
     * @param joueur Le joueur associé au pion.
     * @param couleur La couleur du pion.
     */
    public Pion(JoueurMonopoly joueur, Color couleur){
        super();
        this.joueur = joueur;
        this.couleur = couleur;

        // Mise en forme du pion
        this.setSize(diameter, diameter);
        this.setBackground(couleur);

        // Position initiale du pion
        updatePosition(0);
    }

    public int getPosition(){
        return joueur.getPosition();
    }

    public Color getCouleur(){
        return this.couleur;
    }

    /**
     * Calcule la position absolue du pion sur le plateau pour une résolution de 850x850.
     * @param nbCase Le numéro de la case sur laquelle se trouve le pion.
     * @param nbPionsCase Le nombre de pions déjà présents sur la case.
     * @return Un tableau contenant les coordonnées absolues du pion.
     * @throws CaseInvalideException Si le numéro de la case est invalide.
     */
    static private int[] getPositionPionAbsolute850(int nbCase, int nbPionsCase){
        int x = 0; int y = 0;
        if (nbCase == 0) { //Case de départ
            x = 775; y = 775;
        }
        else if (nbCase <= 9) { //Ligne du bas
            x = 680 - 69 * (nbCase - 1); y = 775;
        }
        else if (nbCase == 10){ //Visite simple
            x = 40; y = 810;
        }
        else if (nbCase <= 19) { //Ligne de gauche
            x = 30 ; y = 680 - 69 * (nbCase - 11);
        }
        else if (nbCase == 20) { //Parking gratuit
            x = 30; y = 30;
        }
        else if (nbCase <= 29) { //Ligne du haut
            x = 130 + 69 * (nbCase - 21);; y = 20;
        }
        else if (nbCase == 30) { //Direction prison
            x = 765; y = 25;
        }
        else if (nbCase <= 39) { //Ligne de droite
            x = 780; y = 125 + + 69 * (nbCase - 31);
        }
        else if (nbCase == 40) { //Case prison
            x = 60; y = 760;
        }
        else{
            throw new CaseInvalideException();
        }

        // Si il y a chevauchement
        if (nbPionsCase > 0) {
            x = x - 10 * nbPionsCase;
            y = y - 10 * nbPionsCase;
        }

        int[] res = {x,y};
        return res;
    }

    /**
     * Met à jour la position du pion sur le plateau en fonction du nombre de pions sur la case.
     * @param nbPionsCase Le nombre de pions déjà présents sur la case.
     */
    public void updatePosition(int nbPionsCase){
        int[] pos850 = getPositionPionAbsolute850(this.joueur.getPosition(), nbPionsCase);
        int x = (int) (pos850[0] * scaleFactor);
        int y = (int) (pos850[1] * scaleFactor);
        this.setBounds(x, y, diameter, diameter);
    }

    /**
     * Met à jour la position du pion de la taille de la fenêtre.
     * @param scaleFactor Le facteur d'échelle pour ajuster la taille du plateau.
     * @param nbPionsCase Le nombre de pions déjà présents sur la case.
     */
    public void updatePlateauWidth(double scaleFactor, int nbPionsCase){
        this.scaleFactor = scaleFactor;
        this.diameter = (int) (lengthPionAbsolute * scaleFactor);
        updatePosition(nbPionsCase);
    }
}
