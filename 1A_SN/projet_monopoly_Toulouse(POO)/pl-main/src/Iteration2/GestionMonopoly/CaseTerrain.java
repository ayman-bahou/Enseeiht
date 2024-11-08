package GestionMonopoly;

import java.util.ArrayList;

public class CaseTerrain extends Case {
	
	private JoueurMonopoly proprietaire;
	private String couleur;
	private int valeurachat;
	private int[] loyer;
	private int prixmaison;
	private int nbrmaison;
	
	
	public CaseTerrain(String nom, int valeurachat, int[] loyer, String couleur, int prixMaison, int id){
		super(nom, id);
		assert( valeurachat > 0 && couleur != null && prixMaison > 0 );
		this.valeurachat = valeurachat;
		this.loyer = loyer;
		this.couleur = couleur;
		this.prixmaison = prixMaison;	
		this.nbrmaison = 0;
	}

	public int getprixmaison() {
		return this.prixmaison;
	}
	public String getcouleur() {
		return this.couleur;
	}
	public int getnbrmaison() {
		return this.nbrmaison;
	}
	public int getprixachat() {
		return this.valeurachat;
	}
	public JoueurMonopoly getprop() {
		return this.proprietaire;
	}
	
	public void setProprietaire(JoueurMonopoly proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public boolean peutacheterterrain() {
		return (this.proprietaire == null);
	}

	public boolean peutMettreMaison() {
		return (this.nbrmaison < 5); 
		
	}

	public void ajouterMaison() {
		if (peutMettreMaison()) {
			this.nbrmaison++;
		} else {
			System.out.println("Nombre maximum de maisons atteint.");
		}
	}

	public void vendremaison() {
		assert(this.nbrmaison > 0);
		this.nbrmaison--;
	}

	public int[] getLoyer(){
		return this.loyer;
	}
	 
	public void action() {
			
	}
}
