package GestionMonopoly.Cartes;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;

/**
 * La classe abstraite Carte représente une carte dans le Monopoly.
 * Les cartes peuvent avoir un titre et une description, ainsi qu'une action spécifique à effectuer.
 */
public abstract class Carte {
    private String titre;
    private String description;

    /**
     * Constructeur de la classe Carte.
     * @param titre Le titre de la carte.
     * @param description La description de la carte.
     */
    public Carte(String titre, String description){
        this.titre = titre;
        this.description = description;
    }

    /**
     * Renvoie le titre de la carte.
     * @return Le titre de la carte.
     */
    public String getNom(){
        return this.titre;
    }

    /**
     * Renvoie la description de la carte.
     * @return La description de la carte.
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Méthode abstraite décrivant l'action spécifique à effectuer lorsqu'une carte est tirée.
     * Cette méthode doit être implémentée par les sous-classes.
     * @param joueurMonopoly Le joueur concerné par l'action de la carte.
     * @param plateau Le plateau de jeu sur lequel se déroule l'action.
     */
    public abstract void actionCarte(JoueurMonopoly joueurMonopoly, Plateau plateau);
}