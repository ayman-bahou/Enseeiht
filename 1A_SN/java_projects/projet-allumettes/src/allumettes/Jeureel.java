package allumettes;
/**Classe modélisant le jeu réel.
 * @author Ayman Bahou
 *
 */
public class Jeureel implements Jeu {
	private int nbAllumettes;

	public Jeureel() {

	}

	public Jeureel(int nbrAllumettes) {
		assert (nbrAllumettes > 0);
		this.nbAllumettes = nbrAllumettes;
	}

	@Override
	public int getNombreAllumettes() {
		return this.nbAllumettes;
	}

	@Override
	public void retirer(int nbPrises) throws CoupInvalideException {
		if (nbPrises > this.nbAllumettes) {
			throw new CoupInvalideException(nbPrises, "> " + this.nbAllumettes);
		} else if (nbPrises > PRISE_MAX) {
			throw new CoupInvalideException(nbPrises, "> " + PRISE_MAX);
		} else if (nbPrises < 1) {
			throw new CoupInvalideException(nbPrises, "< 1");
		} else {
			this.nbAllumettes -= nbPrises;
		}
	}
}
