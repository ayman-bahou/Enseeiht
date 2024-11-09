package allumettes;
/**Classe modélisant la strategie suivie par un joueur tricheur.
 *
 * @author Ayman Bahou
 *
 */
public class StrategieTricheur implements StrategieJoueur {
	public static final int TRICHE = -100;
	/**retourner -100 pour indiquer que le joueur est tricheur et appliquer la strategie
	 * tricheur en retirant toute les allumettes sauf 2, au cas où l'exception
	 * OperationInterditeException ne se déclenche pas.
	 *
	 */
	@Override
	public int getPrise(Jeu jeu) {
		assert (jeu != null);
		assert (jeu.getNombreAllumettes() > 0);
		while (jeu.getNombreAllumettes() > 2) {
			try {
				jeu.retirer(1);
			} catch (CoupInvalideException e) { }
					}
		System.out.println("[Je triche...]");
		return TRICHE;
					}
 					}
