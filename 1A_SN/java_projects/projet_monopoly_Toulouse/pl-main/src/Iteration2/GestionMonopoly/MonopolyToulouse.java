package GestionMonopoly;

import java.awt.Color;

import javax.swing.JFrame;

import Interface_graphique.Panneau_joueur;
import Interface_graphique.Pion;
import Interface_graphique.VuePlateau;

public class MonopolyToulouse {
    public static void main(String[] args) {
        // ----------------- Set-up du jeu ---------------------
            // Création du plateau de jeu
            Plateau plateau = new Plateau();
    
            // Création des joueurs
            JoueurMonopoly joueur1 = new JoueurMonopoly("Alice", 1, 1500);
            JoueurMonopoly joueur2 = new JoueurMonopoly("Bob", 2, 1500);
            JoueurMonopoly joueur3 = new JoueurMonopoly("Charlie", 3, 1500);
            Panneau_joueur[] joueurPanels = {joueur1.getPanel(),joueur2.getPanel(),joueur3.getPanel()};
            // Création des pions
            Pion pion1 = new Pion(0,0,Color.red);
            Pion pion2 = new Pion(0,1,Color.green);
            Pion pion3 = new Pion(0,2,Color.pink);
            Pion[] liste_pions = {pion1,pion2,pion3};

            // Création des dés
            Des des = new Des();

            VuePlateau frame = new VuePlateau(liste_pions,joueurPanels);
            // Dimension de la fenêtre
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // Rendre la fenêtre visible
            frame.setVisible(true);
    }
}