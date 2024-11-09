package allumettes;
/** Cette classe modélise un joueur.
 * Un joueur est caractérisé par son nom et la strategie qu'il suit.
 *
 * @author Ayman Bahou
 *
 */
public class Joueur {
	private String nom;
	private StrategieJoueur strategie;

	/**Construire un Joueur à partir de son nom et sa stratégie.
	 *
	 * @param nom le nom du joueur.
	 * @param strategie la strategie suivie par le joueur.
	 */
	public Joueur(String nom, StrategieJoueur strategie) {
		assert (strategie != null);
		this.nom = nom;
		this.strategie = strategie;
	}

	/**Obtenir le nom du joueur.
	 *
	 * @return nom du joueur
	 */
	public String getNom() {
		return this.nom;
	}

	/**Obtenir le nombre d'Allumettes choisi par le joueur.
	 *
	 * @param jeu le Jeu en cours.
	 * @return le nombre d'Allumettes choisi par le joueur.
	 */
	public int getPrise(Jeu jeu) {
		return this.strategie.getPrise(jeu);
		}

	/**Obtenir la strategie choisie par le joueur.
	 *
	 * @return la strategie choisie par le joueur.
	 */
	public StrategieJoueur getStrategie() {
		return this.strategie;
	}

	/**Modifier la strategie du joueur.
	 *
	 * @param s la nouvelle strategie du joueur.
	 */
	public void setStrategie(StrategieJoueur s) {
		assert (s != null);
		this.strategie = s;
	}
}
