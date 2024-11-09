package allumettes;
import java.util.Scanner;

/**Classe qui modélise la strategie "humain".
 * cette classe est caractérisé par un objet de la classe Scanner qui permet la lecture
 * de la valeur entré par le joueur humain ,et puis le nom du joueur humain.
 *
 * @author Ayman Bahou
 *
 */
public class StrategieHuman implements StrategieJoueur {

	/**Objet permettant la lecture de la valeur entré au clavier.
	 */
	private static Scanner scanner = new Scanner(System.in);
	/**le nom du joueur qui suit la strategie humain.
	 */
	private String nomJoueur;
	/**Construire la strategie humain par le nom du joueur.
	 * @param nomJoueur le nom du joueur.
	 */
	public StrategieHuman(String nomJoueur) {
		this.nomJoueur = nomJoueur;
	}

	/**retourner le nombre d'allumettes choisi par le joueur humain.
	 */
	@Override
	public int getPrise(Jeu jeu) {
		assert (jeu != null);
		assert (jeu.getNombreAllumettes() > 0);
		while (true) {
			System.out.print(this.nomJoueur + ", combien d'allumettes ? ");
			String input = scanner.nextLine();
			if (input.equals("triche")) {
				try {
				jeu.retirer(1);
				} catch (CoupInvalideException e) {
					}
				System.out.println("[Une allumette en moins, plus que "
					+ (jeu.getNombreAllumettes()) + ". Chut !]");
			} else {
				try {
					int prise = Integer.parseInt(input);
					return prise;
				} catch (NumberFormatException e) {
					System.out.println("Vous devez donner un entier.");
					}
					}
}
}
}
