package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import Interface_graphique.FenetreCases.Interface_prison;

/**
 * La classe {@code CasePrison} représente la case "Prison" dans le jeu de Monopoly.
 * Lorsqu'un joueur atterrit sur cette case, il peut être mis en prison et doit suivre les règles associées à la prison.
 */
public class CasePrison extends Case {

    private int amende;

    /**
     * Constructeur pour créer une instance de la classe {@code CasePrison}.
     * @param nom le nom de la case
     * @param id l'identifiant de la case
     * @param amende le montant de l'amende pour sortir de prison
     */
    public CasePrison(String nom, int id, int amende) {
        super(nom, id);
        this.amende = amende;
    }

    /**
     * Méthode pour envoyer un joueur en prison.
     * @param joueur le joueur à envoyer en prison
     */
    public void entrerEnPrison(JoueurMonopoly joueur) {
        joueur.setEstEnPrison(true);
        joueur.resetToursEnPrison();
        System.out.println(joueur.getNom() + " est maintenant en prison pour les 3 prochains tours.");
        joueur.setPosition(Plateau.ID_CASE_PRISON);
    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        Interface_prison fenetre = new Interface_prison(joueur,plateau,amende);
        plateau.setFenetreAction(fenetre);
        fenetre.setVisible(true);
    }
}
