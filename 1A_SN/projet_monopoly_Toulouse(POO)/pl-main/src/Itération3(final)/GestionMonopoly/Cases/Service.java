package GestionMonopoly.Cases;

import java.awt.Color;
import javax.swing.JFrame;

import GestionMonopoly.JoueurMonopoly;
import GestionMonopoly.Plateau;
import Interface_graphique.FenetreCases.FenetreAchatCase;
import Interface_graphique.FenetreCases.FenetreMessageSimple;

/**
 * La classe CaseService représente une case de type service public (ou compagnie) sur le plateau du Monopoly.
 * Elle hérite de la classe Case.
 */
public class Service extends CaseAchetable {
    
    private final int[] multiplicateurServives = {4, 10}; // Les multiplicateurs de loyer pour les services
    
    public Service(String nom, int id, int valeurAchat) {
        super(nom, id,valeurAchat);
    }

    /**
     * Calculer le loyer à payer en fonction du résultat du lancer de dés
     * et du nombre de services possédés par le propriétaire.
     * @param resultatDes Le résultat du lancer de dés.
     * @return Le loyer à payer.
     */
    public int getLoyer(int resultatDes) {
        if (!estAchetable() && this.getProprietaire().getNbServices() == 2) {
            return resultatDes * multiplicateurServives[1];
        }
        return resultatDes * multiplicateurServives[0];
    }

    public void action(JoueurMonopoly joueur, Plateau plateau) {
        if (this.estAchetable()) {
            System.out.println("Le joueur est tombé sur le service : " + this.getNom());
            JFrame fenetre = new FenetreAchatCase(joueur, this);
            plateau.setFenetreAction(fenetre);
            fenetre.setVisible(true);
        } else if (this.getProprietaire() != joueur){
            joueur.debiter(this.getLoyer(plateau.getDes().getResultat()));
            System.out.println("Vous lancez les dés ...");
            plateau.getDes().lancer();
            // Ouvrir la fenêtre pour informer le joueur
            FenetreMessageSimple fenetre = new FenetreMessageSimple(
                "Vous avez fait " + plateau.getDes().getResultat() + " aux dés " + "\n" + 
                joueur.getNom() + " paie " + this.getLoyer(plateau.getDes().getResultat()) + "M$ de loyer à " + this.getProprietaire().getNom() + " !", new Color(0xd5f5e3), Color.BLACK);
            plateau.setFenetreAction(fenetre);
            fenetre.setVisible(true);
        }
    }
}
