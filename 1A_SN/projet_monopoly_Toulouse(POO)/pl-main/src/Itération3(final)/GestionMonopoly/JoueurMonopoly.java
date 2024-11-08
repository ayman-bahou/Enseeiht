package GestionMonopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GestionMonopoly.Cases.CaseAchetable;
import GestionMonopoly.Cases.Gare;
import GestionMonopoly.Cases.GroupeProprietes;
import GestionMonopoly.Cases.Propriete;
import GestionMonopoly.Cases.Service;
import Interface_graphique.Panneau_joueur;
import Interface_graphique.Pion;

/**
 * La classe JoueurMonopoly représente un joueur dans le jeu Monopoly.
 */
public class JoueurMonopoly {

    private static final double pourcentageReventePropriete = 1;
    private static final double pourcentageReventeMaison = 0.5;

    private String nom; // Le nom du joueur
    private int id; // L'identifiant du joueur
    private int position; // La position actuelle du joueur sur le plateau
    private int argent; // Le montant d'argent que possède le joueur
    private int toursEnPrison; // Le nombre de tours passés en prison
    private boolean enPrison; // Indique si le joueur est actuellement en prison
    private int carteSortiePrison; // Le nombre de cartes "Sortie de prison" que possède le joueur
    private ArrayList<Gare> gares; // Les gares possédées par le joueur
    private ArrayList<Service> services; // Les services publics (compagnies) possédés par le joueur
    private Map<GroupeProprietes, List<Propriete>> proprietes; // Les propriétés possédées par le joueur, regroupées par groupe de propriétés
    private Panneau_joueur panneau; // Le panneau d'affichage du joueur dans l'interface graphique
    private Pion pion; // Le pion représentant le joueur sur le plateau

    /**
     * Constructeur de la classe JoueurMonopoly.
     * @param nom Le nom du joueur.
     * @param id L'identifiant du joueur.
     * @param argent Le montant d'argent initial du joueur.
     * @param couleur La couleur du pion représentant le joueur.
     */
    public JoueurMonopoly(String nom, int id, int argent, Color couleur) {
        assert (nom != null && id > 0 && argent > 0);
        this.nom = nom;
        this.id = id;
        this.argent = argent;
        this.position = 0;
        this.toursEnPrison = 0;
        this.carteSortiePrison = 0;
        this.gares = new ArrayList<>();
        this.services = new ArrayList<>();
        this.proprietes = new HashMap<>();
        this.enPrison = false;
        this.pion = new Pion(this, couleur);
        this.panneau = new Panneau_joueur(this, argent);
    }

    // |||||||||||||||||||| Requêtes ||||||||||||||||||||||

    /**
     * Obtenir le nom du joueur.
     * @return Le nom du joueur.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Obtenir l'identifiant du joueur.
     * @return L'identifiant du joueur.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Obtenir la position actuelle du joueur sur le plateau.
     * @return La position du joueur.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Obtenir le solde d'argent du joueur.
     * @return Le montant d'argent que possède le joueur.
     */
    public int getSolde() {
        return this.argent;
    }

    /**
     * Obtenir le nombre de tours passés en prison par le joueur.
     * @return Le nombre de tours passés en prison.
     */
    public int getToursPrison() {
        return this.toursEnPrison;
    }

    /**
     * Vérifier si le joueur est actuellement en prison.
     * @return true si le joueur est en prison, false sinon.
     */
    public boolean estEnPrison() {
        return this.enPrison;
    }

    /**
     * Vérifier si le joueur est en situation de banqueroute.
     * @return true si le joueur est en banqueroute (sans argent), false sinon.
     */
    public boolean estBanqueroute() {
        return this.argent <= 0;
    }

    /**
     * Obtenir le panneau d'affichage du joueur dans l'interface graphique.
     * @return Le panneau d'affichage du joueur.
     */
    public Panneau_joueur getPanel() {
        return this.panneau;
    }

    /**
     * Obtenir le pion représentant le joueur sur le plateau.
     * @return Le pion du joueur.
     */
    public Pion getPion() {
        return this.pion;
    }

    /**
     * Obtenir le nombre de gares possédées par le joueur.
     * @return Le nombre de gares possédées.
     */
    public int getNbGares() {
        return this.gares.size();
    }

    /**
     * Obtenir le nombre de services publics (compagnies) possédés par le joueur.
     * @return Le nombre de services possédés.
     */
    public int getNbServices() {
        return this.services.size();
    }

    /**
     * Vérifier si il possède au moins une carte "Sortie de prison".
     */
    public boolean possedeCarteSortiePrison() {
        return this.carteSortiePrison > 0;
    }

    /**
     * Vérifier si le joueur possède toutes les propriétés d'un groupe donné.
     * @param groupe Le groupe de propriétés à vérifier.
     * @return true si le joueur possède toutes les propriétés du groupe, false sinon.
     */
    public boolean possedeGroupe(GroupeProprietes groupe) {
        List<Propriete> proprietesGroupe = proprietes.get(groupe);
        return proprietesGroupe != null && proprietesGroupe.size() == groupe.getNbProprietes();
    }

    // |||||||||||||||||||| Commande ||||||||||||||||||||||

    /**
     * Définir le nom du joueur.
     * @param nom Le nouveau nom du joueur.
     */
    public void setNom(String nom) {
        assert (nom != null);
        this.nom = nom;
    }

    /**
     * Définir l'identifiant du joueur.
     * @param id Le nouvel identifiant du joueur.
     */
    public void setID(int id) {
        assert (id > 0);
        this.id = id;
    }

    /**
     * Créditer le compte du joueur avec un montant donné.
     * @param montant Le montant à créditer.
     */
    public void crediter(int montant) {
        assert (montant >= 0);
        this.argent += montant;
        panneau.updateArgent(this.argent);
    }

    /**
     * Débiter le compte du joueur avec un montant donné.
     * @param montant Le montant à débiter.
     */
    public void debiter(int montant) {
        assert (montant >= 0);
        this.argent -= montant;
        panneau.updateArgent(this.argent);
    }

    /**
     * Définir la position du joueur sur le plateau.
     * @param pos La nouvelle position du joueur.
     */
    public void setPosition(int pos) {
        assert (pos >= 0);
        this.position = pos;
    }

    /**
     * Déplacer le joueur d'un certain nombre de cases sur le plateau.
     * @param x Le nombre de cases à avancer (ou reculer si x est négatif).
     */
    public void deplacer(int x) {
        if (x >= 0) {
            this.position = (this.position + x) % (Plateau.NB_CASES - 1);
        } else {
            // Si le déplacement est négatif (vers l'arrière), on ajoute 40 pour assurer un déplacement correct.
            // Par exemple, si la position est 3 et x est -5, on veut que la nouvelle position soit 38.
            // En ajoutant 40, on obtient 35 % 40 = 35, qui est correct.
            this.position = (this.position + x + Plateau.NB_CASES - 1) % (Plateau.NB_CASES - 1);
        }
    }

    /**
     * Ajouter un tour de plus passé en prison par le joueur.
     */
    public void addToursEnPrison() {
        assert (this.toursEnPrison < 3);
        this.toursEnPrison++;
    }

    /**
     * Réinitialiser le nombre de tours passés en prison par le joueur.
     */
    public void resetToursEnPrison() {
        this.toursEnPrison = 0;
    }

    /**
     * Ajouter une carte "Sortie de prison".
     */
    public void addCarteSortiePrison() {
        this.carteSortiePrison++;
    }

    /**
     * Retirer une carte "Sortie de prison".
     */
    public void removeCarteSortiePrison() {
        this.carteSortiePrison--;
    }

    /**
     * Définir si le joueur est actuellement en prison.
     * @param estPrisonnier true si le joueur est en prison, false sinon.
     */
    public void setEstEnPrison(boolean estPrisonnier) {
        this.enPrison = estPrisonnier;
    }

    /**
     * Acheter une propriété donnée.
     * @param propriete La propriété à acheter.
     * @return true si l'achat est réussi, false sinon.
     */
    public boolean acheterPropriete(Propriete propriete) {
        if (this.argent < propriete.getValeurAchat()) {
            return false; // Vérifier si le joueur a assez d'argent pour acheter la propriété
        }
        if (propriete.estAchetable()) {
			propriete.setProprietaire(this);
            GroupeProprietes groupe = propriete.getGroupe();
            proprietes.computeIfAbsent(groupe, k -> new ArrayList<>()).add(propriete);
            panneau.addPropriété(propriete.getNom());
            this.debiter(propriete.getValeurAchat());
            return true;
        }
        return false;
    }

    /**
     * Acheter une maison pour une propriété donnée.
     * @param propriete La propriété pour laquelle acheter une maison.
     * @return true si l'achat est réussi, false sinon.
     */
    public boolean acheterMaison(Propriete propriete) {
        if (this.argent < propriete.getPrixMaison()) {
            return false; // Vérifier si le joueur a assez d'argent pour acheter une maison
        }
        if (possedeGroupe(propriete.getGroupe())) {
            propriete.addMaison();
            this.debiter(propriete.getPrixMaison());
            return true;
        }
        return false;
    }

    /**
     * Vendre une propriété donnée.
     * @param propriete La propriété à vendre.
     * @return true si la vente est réussie, false sinon.
     */
    public boolean vendrePropriete(Propriete propriete) {
        if (propriete.getNbMaison() != 0) {
            return false; // Vérifier si la propriété a des maisons
        }
        List<Propriete> proprietesGroupe = proprietes.get(propriete.getGroupe());
        if (proprietesGroupe != null) {
			propriete.removeProprietaire();
            proprietesGroupe.remove(propriete);
			this.crediter((int) (propriete.getValeurAchat() * pourcentageReventePropriete));
			return true;
        }
        return false;
    }

    /**
     * Vendre une maison d'une propriété donnée.
     * @param propriete La propriété dont vendre une maison.
     * @return true si la vente est réussie, false sinon.
     */
    public boolean vendreMaisonPropriete(Propriete propriete) {
        if (propriete.getNbMaison() == 0) {
            return false; // Vérifier si la propriété a des maisons à vendre
        }
        propriete.vendreMaison();
        this.crediter((int) (propriete.getPrixMaison() * pourcentageReventeMaison));
        return true;
    }

    /**
     * Acheter une gare.
     * @param gare La gare à acheter.
     * @return true si l'achat est réussi, false sinon.
     */
    public boolean acheterGare(Gare gare) {
        if (this.argent < gare.getValeurAchat()) {
            return false; // Vérifier si le joueur a assez d'argent pour acheter la gare
        }
        if (gare.estAchetable()) {
            this.debiter(gare.getValeurAchat());
			gare.setProprietaire(this);
            gares.add(gare);
            panneau.addGare(gare.getNom());
            return true;
        }
        return false;
    }

    /**
     * Vendre une gare.
     * @param gare La gare à vendre.
     */
    public void vendreGare(Gare gare) {
        this.gares.remove(gare);
        this.crediter((int) (gare.getValeurAchat() * pourcentageReventePropriete));
		gare.removeProprietaire();
        panneau.removeGare(gare.getNom());
    }

    /**
     * Acheter un service public (compagnie).
     * @param service Le service public à acheter.
     * @return true si l'achat est réussi, false sinon.
     */
    public boolean acheterService(Service service) {
        if (this.argent < service.getValeurAchat()) {
            return false; // Vérifier si le joueur a assez d'argent pour acheter le service
        }
        if (service.estAchetable()) {
            this.debiter(service.getValeurAchat());
			service.setProprietaire(this);
            services.add(service);
            panneau.addService(service.getNom());
            return true;
        }
        return false;
    }

    /**
     * Vendre un service public (compagnie).
     * @param service Le service public à vendre.
     */
    public void vendreService(Service service) {
        this.services.remove(service);
        this.crediter((int) (service.getValeurAchat() * pourcentageReventePropriete));
        panneau.removeGare(service.getNom());
    }

    public void acheter(CaseAchetable caseAchetable){
        if (caseAchetable instanceof Propriete){
            acheterPropriete((Propriete) caseAchetable);
        } else if (caseAchetable instanceof Gare) {
            acheterGare((Gare) caseAchetable);
        } else {
            acheterService((Service) caseAchetable);
        }
    }
}
