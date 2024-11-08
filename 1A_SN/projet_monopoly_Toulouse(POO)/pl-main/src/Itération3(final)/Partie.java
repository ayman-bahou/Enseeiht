import java.awt.Color;
import java.util.ArrayList;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

public class Partie {
    public static void main(String[] args) {
        // ----------------- Set-up du jeu ---------------------    
            // Création des joueurs
            ArrayList<JoueurMonopoly> joueurs = new ArrayList<>();
            joueurs.add(new JoueurMonopoly("Alice", 0, 1500,Color.pink));
            joueurs.add(new JoueurMonopoly("Bob", 1, 1500,Color.green));
            joueurs.add(new JoueurMonopoly("Charlie", 2, 1500,Color.red));

            Plateau plateau = new Plateau(joueurs);
            plateau.afficheFenetre();

        // ------------------Jouer la partie -------------------
        
        while (!plateau.partieFinie()){
            plateau.jouerTour();
        }
        
    }
}