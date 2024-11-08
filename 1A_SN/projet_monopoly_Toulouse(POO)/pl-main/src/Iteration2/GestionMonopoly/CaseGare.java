package GestionMonopoly;

import java.util.ArrayList;

public class CaseGare extends Case {
	
	private JoueurMonopoly proprietaire;
	private int valeurachat;
	private int[] loyer;
	
	public CaseGare(String nom, int valeurachat, int[] loyer, int id){
		super(nom, id);
		assert(valeurachat > 0  && id > 0);
		this.valeurachat = valeurachat;
		this.loyer = loyer;
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
	
	public boolean peutachetergare() {
		return (this.proprietaire == null);
	}
	
	 public int[] getLoyer(){
		 return this.loyer;
	 }
	 
	 public void action() {
			
		}
}
