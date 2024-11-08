package GestionMonopoly.Cases;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@code GroupeProprietes} représente un groupe de propriétés du même type (couleur) dans le jeu de Monopoly.
 * Chaque groupe est défini par une couleur et contient une liste de propriétés appartenant à ce groupe.
 */
public class GroupeProprietes {

    private List<Propriete> proprietes;
    private Color couleur;

    /**
     * Constructeur pour créer une instance de la classe {@code GroupeProprietes}.
     * @param couleur la couleur du groupe de propriétés
     */
    public GroupeProprietes(Color couleur) {
        this.proprietes = new ArrayList<>();
        this.couleur = couleur;
    }

    /**
     * Ajoute une propriété au groupe de propriétés.
     * @param propriete la propriété à ajouter
     */
    public void addPropriete(Propriete propriete) {
        this.proprietes.add(propriete);
    }

    /**
     * Renvoie le nombre de propriétés dans le groupe.
     * @return le nombre de propriétés
     */
    public int getNbProprietes() {
        return this.proprietes.size();
    }

    /**
     * Renvoie la couleur du groupe de propriétés.
     * @return la couleur du groupe
     */
    public Color getCouleur() {
        return this.couleur;
    }
}
