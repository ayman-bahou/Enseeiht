package GestionMonopoly.Cases;

import GestionMonopoly.JoueurMonopoly;

public abstract class CaseAchetable extends Case {

    private JoueurMonopoly proprietaire = null; // Le joueur qui possède le service
    private int valeurAchat; // Le prix d'achat du service
    
    /**
     * Constructeur de la classe CaseService.
     * @param nom Le nom du service.
     * @param id L'identifiant du service sur le plateau.
     * @param valeurAchat Le prix d'achat du service.
     */
    public CaseAchetable(String nom, int id, int valeurAchat) {
        super(nom, id);
        assert (valeurAchat > 0 && id > 0);
        this.valeurAchat = valeurAchat;
    }
    

    /**
     * Obtenir le prix d'achat du service.
     * @return Le prix d'achat du service.
     */
    public int getValeurAchat() {
        return this.valeurAchat;
    }
    
    /**
     * Obtenir le propriétaire du service.
     * @return Le joueur qui possède le service.
     */
    public JoueurMonopoly getProprietaire() {
        return this.proprietaire;
    }
    
    /**
     * Vérifier si le service est achetable.
     * @return true si le service est achetable (sans propriétaire), false sinon.
     */
    public boolean estAchetable() {
        return (this.proprietaire == null);
    }

    /**
     * Définir le propriétaire du service.
     * @param proprietaire Le joueur qui devient propriétaire du service.
     */
    public void setProprietaire(JoueurMonopoly proprietaire) {
        this.proprietaire = proprietaire;
    }

    /**
     * Retirer le propriétaire du service, le rendant disponible à l'achat.
     */
    public void removeProprietaire() {
        this.proprietaire = null;
    }
}
