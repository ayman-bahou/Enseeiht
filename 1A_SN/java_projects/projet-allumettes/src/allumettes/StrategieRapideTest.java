package allumettes;
import static org.junit.Assert.assertEquals;
import org.junit.*;

/**Classe de test de la strategie rapide.
 * 
 * @author Ayman Bahou
 *
 */
public class StrategieRapideTest {

	private StrategieJoueur strategie;

	@Before public void setUp() {
		strategie = new StrategieRapide();
	}
	/**test pour vérifier qu'on choisi à chaque fois le nombre maximale d'Allumettes qu'on peut.
	 */
	@Test public void testerstrategieRapide() {
		for(int i = 1;i <= 13;i += 1) {
			if(i < Jeu.PRISE_MAX) {
				assertEquals("Coup invalide pour la strategie rapide",strategie.getPrise(new Jeureel(i)),i);
			}
			else {
				assertEquals("Coup invalide pour la strategie rapide",strategie.getPrise(new Jeureel(i)),Jeu.PRISE_MAX);
			}
		}
	}
}
