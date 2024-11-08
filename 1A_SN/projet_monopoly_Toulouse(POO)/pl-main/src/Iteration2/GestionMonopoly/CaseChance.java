package GestionMonopoly;


public class CaseChance extends Case {


    public CaseChance(String nom, int id) {
        super(nom,id);
    }

    
    public void action(JoueurMonopoly joueur) {
        // Supposons que nous avons une méthode pour tirer une carte de chance
        // Carte carte = tirerCarteChance();

        // les informations de la carte tirée
        System.out.println(joueur.getNom() + " tire la carte " + "test");

        // Exécute l'action de la carte tirée
        //carte.actionCarte(joueur);
        // Affichage de la carte
        //afficherCarte(carte);
    }
    /**
    private Carte tirerCarteChance() {
        // La logique pour tirer une carte chance du jeu
        return new CarteChance();
    } */
}
