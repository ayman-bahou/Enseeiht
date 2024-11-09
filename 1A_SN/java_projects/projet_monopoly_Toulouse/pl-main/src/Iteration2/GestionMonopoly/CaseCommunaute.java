package GestionMonopoly;


public class CaseCommunaute extends Case {

 
    public CaseCommunaute(String nom, int id) {
        super(nom, id);
    }
  
    public void action(JoueurMonopoly joueur) {
        //Supposons que nous avons une méthode pour tirer une carte
        //Carte carte = tirerCarteCommunaute();
        System.out.println(joueur.getNom() + " tire la carte " + "Test communauté");
	    
        //Supposons que les cartes ont une méthode pour exécuter une action
        //carte.executerAction(joueur);

        //Affichez les informations sur la carte tirée
        //afficherCarte(carte);
    }

    /** 
    private Carte tirerCarteCommunaute() {
        
        return new CarteCommunaute();
    }

    private void afficherCarte(Carte carte) {
        
        System.out.println("Carte Communauté: " + carte.getNom() + " - " + carte.getDescription());
    }*/

   }
