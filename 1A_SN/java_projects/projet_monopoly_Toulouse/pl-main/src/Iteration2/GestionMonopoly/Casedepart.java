package GestionMonopoly;

public class Casedepart extends Case {
	
	public Casedepart(String nom, int id) {
        super(nom,id);
    }
	
	public void action(JoueurMonopoly joueur) {
		joueur.ajouterArgent(200);		
	}

}
