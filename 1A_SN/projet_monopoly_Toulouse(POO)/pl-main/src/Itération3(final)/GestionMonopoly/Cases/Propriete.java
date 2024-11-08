package GestionMonopoly.Cases;

import java.awt.Color;
import javax.swing.JFrame;
import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import Interface_graphique.FenetreCases.FenetreAchatCase;
import Interface_graphique.FenetreCases.FenetreAchatMaison;
import Interface_graphique.FenetreCases.FenetreMessageSimple;

/**
 * La classe Propriete représente une case porpriété du Monopoly qui peut être achetée et possédée par un joueur.
 * Elle hérite de la classe Case.
 */
public class Propriete extends CaseAchetable {
    
    private GroupeProprietes groupe; // La groupe auquel la propriété appartient
    private int[] loyer; // Les loyers en fonction du nombre de maisons sur la propriété
    private int prixMaison; // Le prix d'une maison sur la propriété
    private int nbMaison = 0; // Le nombre de maisons actuellement sur la propriété

    /**
     * Constructeur de la classe Propriete.
     * @param nom Le nom de la propriété.
     * @param valeurAchat Le prix d'achat de la propriété.
     * @param loyer Les loyers en fonction du nombre de maisons sur la propriété.
     * @param groupe Le groupe de la propriété.
     * @param prixMaison Le prix d'une maison sur la propriété.
     * @param id L'identifiant de la propriété sur le plateau.
     */
    public Propriete(String nom, int id, GroupeProprietes groupe, int valeurAchat, int[] loyer, int prixMaison) {
        super(nom, id,valeurAchat);
        this.loyer = loyer;
        this.groupe = groupe;
        groupe.addPropriete(this);
        this.prixMaison = prixMaison;
    }

    /**
     * Obtenir le prix d'une maison sur la propriété.
     * @return Le prix d'une maison sur la propriété.
     */
    public int getPrixMaison() {
        return this.prixMaison;
    }

    /**
     * Obtenir la groupe de la propriété.
     * @return La groupe de la propriété.
     */
    public GroupeProprietes getGroupe() {
        return this.groupe;
    }

	/**
     * Obtenir la couleur de la propriété.
     * @return La couleur de la propriété.
     */
    public Color getCouleur() {
        return this.groupe.getCouleur();
    }

    /**
     * Obtenir le nombre de maisons sur la propriété.
     * @return Le nombre de maisons sur la propriété.
     */
    public int getNbMaison() {
        return this.nbMaison;
    }

    /**
     * Ajouter une maison sur la propriété si possible.
     * @return true si la maison a été ajoutée avec succès, false sinon.
     */
    public boolean addMaison() {
        if (this.nbMaison < 5) {
            this.nbMaison++;
            return true;
        }
        return false;
    }

    /**
     * Vendre une maison de la propriété si possible.
     * @return true si la maison a été vendue avec succès, false sinon.
     */
    public boolean vendreMaison() {
        if (this.nbMaison > 0) {
            this.nbMaison--;
            return true;
        }
        return false;
    }

    /**
     * Obtenir le loyer actuel de la propriété en fonction du nombre de maisons.
     * @return Le loyer actuel de la propriété.
     */
    public int getLoyer() {
        return this.loyer[nbMaison];
    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        System.out.println("Le joueur est tombé sur la propiété : " + this.getNom());

        if (this.estAchetable()) {
            JFrame fenetrePropriete = new FenetreAchatCase(joueur, this);
            plateau.setFenetreAction(fenetrePropriete);
            fenetrePropriete.setVisible(true);
        } 
        else if (this.getProprietaire() == joueur) {
            if (this.peutMettreMaison() && joueur.possedeGroupe(this.getGroupe())) {
                JFrame fenetrePropriete = new FenetreAchatMaison(joueur, this);
                plateau.setFenetreAction(fenetrePropriete);
                fenetrePropriete.setVisible(true);
            }
        }
        else {
            joueur.debiter(this.getLoyer());

            // Ouvrir la fenêtre pour informer le joueur
            FenetreMessageSimple fenetre = new FenetreMessageSimple(joueur.getNom() + " paie " + this.getLoyer() + "M$ de loyer à " + this.getProprietaire().getNom() + " !", new Color(0xd5f5e3), Color.BLACK);
            plateau.setFenetreAction(fenetre);
            fenetre.setVisible(true);

        }

    }

	// |||||||||||||||||||| Annexe ||||||||||||||||||||||||||

    public boolean peutMettreMaison() {
        return (this.nbMaison < 5);
    }
}
