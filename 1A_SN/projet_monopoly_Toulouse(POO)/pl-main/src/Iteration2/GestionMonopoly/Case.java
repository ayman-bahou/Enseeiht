package GestionMonopoly;

public abstract class Case {
	
	private String nom;
	private int id;
	
	
	public Case(String nom, int id) {
		assert(nom != null && id > 0);
		this.nom = nom;
		this.id = id;
	}
	
	/**
     * Obtenir le nom de la case
     *
     * @return le nom de la case
     */
	public String getNom() {
		return this.nom;
	}
	
	/**
     * Obtenir l'id de la case dans le plateau
     *
     * @return l'id de la case dans le plateau
     */
	public int getId() {
		return this.id;
	}
	
	public void action() {
	
	}
	
	
}
