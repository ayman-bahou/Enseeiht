package GestionMonopoly;

public class CasePrison extends Case {

   private static final int AMENDE_PRISON = 50;


    public CasePrison(String nom, int id) {
    	super(nom, id);
    }  
    
    public void entrerEnPrison(JoueurMonopoly joueur) {
        joueur.setEstPrison(true);
        joueur.setToursEnPrison(0);
        System.out.println("Vous êtes maintenant en prison pour les 3 prochaines tours.");
        joueur.setPosition(this.getId());
    }

    public boolean gererTourEnPrison(JoueurMonopoly joueur,int toursEnPrison) {
        toursEnPrison++;
        System.out.print("Tour " + toursEnPrison);
        
        if (toursEnPrison == 3) {
            joueur.setEstPrison(false);
            joueur.addToursEnPrison();
        }

        System.out.println("Option disponible :");
        System.out.println("1. Lancer un double");
        System.out.println("2. Payer 50 euros pour s'echapper");
        
        int choix = 1;
        switch (choix) {

            case 1:
                System.out.println("Vous lancez les dés ...");
                int[] resultatDes = {6,6};//Des.lancer2des(); //Méthode pour lancer 2 des
                int totalDes = resultatDes[0] + resultatDes[1];
                System.out.println("Resultat du lancer : " + totalDes);
                if (resultatDes[0] == resultatDes[1]) {
                    System.out.println("Vous avez fait un double ! Vous sortez de prison.");
                    joueur.setEstPrison(false);
                    joueur.deplacerDe(totalDes);
                } else {
                    System.out.println("Vous n'avez pas fait de double !");
                }
                break;

            case 2:
                System.out.println("Vous payez l'amende pour vous echapper de la prison.");
                joueur.retirerArgent(AMENDE_PRISON);
                joueur.setEstPrison(false);
                break;

            default:
                System.out.println("Option invalide !");

        }
        return joueur.getEnPrison(); //retourne true si le joueur est toujours en prison

 
}  

} 