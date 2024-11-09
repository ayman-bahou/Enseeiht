package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

/**
 * La classe abstraite Case représente une case sur le plateau du Monopoly.
 * Elle sert de classe de base pour différents types de cases du jeu.
 */
public abstract class Case {
    
    private String nom; // Le nom de la case
    private int id; // L'identifiant de la case sur le plateau
    
    /**
     * Constructeur de la classe Case.
     * @param nom Le nom de la case.
     * @param id L'identifiant de la case sur le plateau.
     */
    public Case(String nom, int id) {
        assert (nom != null && id > 0);
        this.nom = nom;
        this.id = id;
    }
    
    /**
     * Obtenir le nom de la case.
     * @return Le nom de la case.
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     * Obtenir l'identifiant de la case sur le plateau.
     * @return L'identifiant de la case sur le plateau.
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Méthode abstraite destinée à être implémentée dans les sous-classes.
     * Elle définit l'action à effectuer lorsque le joueur atterrit sur cette case.
     */
    public abstract void action(JoueurMonopoly joueur, Plateau plateau);
}
