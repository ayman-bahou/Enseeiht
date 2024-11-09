package allumettes;
import java.util.Random;
/**classe modélisant la stratégie naif qui consiste à choisir aléatoirement.
 *
 * @author Ayman Bahou
 *
 */
public class StrategieNaif implements StrategieJoueur {

	@Override
	public int getPrise(Jeu jeu) {
		assert (jeu != null);
		assert (jeu.getNombreAllumettes() > 0);
		Random alea = new Random();
		int prise = alea.nextInt(Jeu.PRISE_MAX) + 1;
		return prise;
		}
}



