package allumettes;
/**classe modélisant strategie rapide qui consiste à choisir le nbr max qu'on peut.
 *
 * @author Ayman Bahou
 *
 */
public class StrategieRapide implements StrategieJoueur {
	@Override
	public int getPrise(Jeu jeu) {
		assert (jeu != null);
		assert (jeu.getNombreAllumettes() > 0);
		return Math.min(jeu.getNombreAllumettes(), Jeu.PRISE_MAX);
	}
}
