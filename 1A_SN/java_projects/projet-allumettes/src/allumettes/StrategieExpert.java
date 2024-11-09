package allumettes;
import java.util.Random;
/**Classe modélisant la stratégie expert où le joueur gagne certainment lorsqu'il peut.
 *
 * @author Ayman Bahou
 *
 */
public class StrategieExpert implements StrategieJoueur {
	private static final int VALEUR = 4;
	@Override
	public int getPrise(Jeu jeu) {
		assert (jeu != null);
		assert (jeu.getNombreAllumettes() > 0);
		int nb = Math.min(jeu.getNombreAllumettes(), Jeu.PRISE_MAX);
		for (int k = 1; k <= nb; k++) {
			if ((jeu.getNombreAllumettes() - k) % VALEUR == 1) {
				return k;
			}
			}
		Random alea = new Random();
		return alea.nextInt(nb) + 1;
			}
			}
