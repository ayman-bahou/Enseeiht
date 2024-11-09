package allumettes;
/**Classe modélisant la procuration permettant d'arrêter le jeu au cas
 * où l'arbitre n'est pas confiant.
 * @author Ayman Bahou
 *
 */
public class JeuAntitriche implements Jeu {
	/** il s'agit du nombre d'allumettes du Jeureel en cours.
	 */
	private int nbAllumettesJeureel;

	public JeuAntitriche(int nb) {
		assert (nb > 0);
		this.nbAllumettesJeureel = nb;
	}

	@Override
	public int getNombreAllumettes() {
		return (new Jeureel(this.nbAllumettesJeureel)).getNombreAllumettes();
	}

	@Override
	public void retirer(int nbPrises) throws CoupInvalideException {
		throw new OperationInterditeException(" ");
	}

}
