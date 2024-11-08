package Interface_graphique;

import java.awt.*;
import javax.swing.*;

public class Pion extends CerclePanel {

    // Données sur l'image du pion
    final ImageIcon pngPion = new ImageIcon("Pion_Monopoly_1.png");
    final int lengthPionAbsolute = 40;

    private Color couleur;
    private int position;
    private int diameter = lengthPionAbsolute;
    private double scaleFactor;

    public Pion(int position, int nbPionsCase, Color couleur){
        super();
        this.position = position;
        this.couleur = couleur;

        // Mise en forme du pion
        this.setSize(diameter, diameter);
        this.setBackground(couleur);

        // Donner la bonne position au pion
        setPositionPion(position, nbPionsCase);
    }

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

    public void setPositionPion(int nbCase, int nbPionsCase){
        int[] pos850 = getPositionPionAbsolute850(nbCase, nbPionsCase);
        int x = (int) (pos850[0] * scaleFactor);
        int y = (int) (pos850[1] * scaleFactor);
        this.position = nbCase;
        this.setBounds(x, y, diameter, diameter);
    }

    public void updatePlateauWidth(double scaleFactor, int nbPionsCase){
        this.scaleFactor = scaleFactor;
        this.diameter = (int) (lengthPionAbsolute * scaleFactor);
        setPositionPion(position, nbPionsCase);
    }

    public int getPosition(){
        return position;
    }
}
