package allumettes;

/** Lance une partie des 13 allumettes en fonction des arguments fournis
 * sur la ligne de commande.
 * @author	Xavier Crégut
 * @version	$Revision: 1.5 $
 */
public class Jouer {
	/**nombre d'allumettes initial.
	 */
	private static final int NOMBRE_ALLUMETTES_INITIAL = 13;
	/** Lancer une partie. En argument sont donnés les deux joueurs sous
	 * la forme nom@stratégie.
	 * @param args la description des deux joueurs
	 */
	public static void main(String[] args) {
		final int nbMaxArguments = 3;
		try {
			String[] listeJoueurs;
			String chaineJoueurs;
			boolean confiance;
			verifierNombreArguments(args);
			if ((args.length == nbMaxArguments) && (args[0].equals("-confiant"))) {
				confiance = true;
				chaineJoueurs = args[1] + "@" + args[2];
			} else if (args.length == nbMaxArguments - 1) {
				confiance = false;
				chaineJoueurs = args[0] + "@" + args[1];
			} else {
				throw new ConfigurationException("argument inconnue");
			}
			listeJoueurs = chaineJoueurs.split("@");
			nouvellePartie(listeJoueurs, confiance);
		} catch (ConfigurationException e) {
			System.out.println();
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
			System.exit(1);
		}
	}
	private static void verifierNombreArguments(String[] args) {
		final int nbJoueurs = 2;
		if (args.length < nbJoueurs) {
			throw new ConfigurationException("Trop peu d'arguments : "
					+ args.length);
		}
		if (args.length > nbJoueurs + 1) {
			throw new ConfigurationException("Trop d'arguments : "
					+ args.length);
		}
		}

	/** Afficher des indications sur la manière d'exécuter cette classe. */
	public static void afficherUsage() {
		System.out.println("\n" + "Usage :"
				+ "\n\t" + "java allumettes.Jouer joueur1 joueur2"
				+ "\n\t\t" + "joueur est de la forme nom@stratégie"
				+ "\n\t\t" + "strategie = naif | rapide | expert | humain | tricheur"
				+ "\n"
				+ "\n\t" + "Exemple :"
				+ "\n\t" + "	java allumettes.Jouer Xavier@humain "
					   + "Ordinateur@naif"
				+ "\n"
				);
	}
	/**retourner la strategie du joueur.
	 *
	 * @param s la chaine indiquant la strategie du joueur.
	 * @param nomj nom du joueur.
	 * @return strategie suivie par joueur.
	 */
	public static StrategieJoueur strategie(String s, String nomj) {
		switch (s) {
			case "humain":
				return new StrategieHuman(nomj);
			case "tricheur":
				return new StrategieTricheur();
			case "expert":
				return new StrategieExpert();
			case "rapide":
				return new StrategieRapide();
			case "naif":
				return new StrategieNaif();
			default:
				throw new ConfigurationException("la strategie choisie est inconnue");
		}
		}
	public static void nouvellePartie(String[] listeJoueurs, boolean confiance) {
		try {
			final int dernierIndice = 3;
			StrategieJoueur strategiej1 = strategie(listeJoueurs[1], listeJoueurs[0]);
			StrategieJoueur strategiej2 = strategie(listeJoueurs[dernierIndice],
 listeJoueurs[2]);
			Joueur j1 = new Joueur(listeJoueurs[0], strategiej1);
			Joueur j2 = new Joueur(listeJoueurs[2], strategiej2);
			Jeu jeu = new Jeureel(Jouer.NOMBRE_ALLUMETTES_INITIAL);
			Arbitre arbitre = new Arbitre(j1, j2, confiance);
			arbitre.arbitrer(jeu);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Erreur : " + e.getMessage());
			afficherUsage();
		}
		}
		}
