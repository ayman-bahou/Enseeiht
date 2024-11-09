package allumettes;
/**Classe d'arbitre qui gère le jeu.
 * Un arbitre est caractérisé par les deux joueurs participant à la partie.
 * Un arbitre est aussi caractérisé par sa confiance(true s'il tolère
 * la triche et false sinon).
 *
 * @author Ayman Bahou
 *
 */

public class Arbitre {

	/**le premier joueur du jeu.
	 */
	private Joueur j1;

	/**le deuxième joueur.
	 */
	private Joueur j2;

	/**la confiance de l'arbitre qui est true s'il tolère la triche et false sinon.
	 */
	private boolean confiance;

	/**Construire un arbitre par les deux joueurs de la partie.
	 *
	 * @param j1 premier joueur.
	 * @param j2 deuxième joueur.
	 */
	public Arbitre(Joueur j1, Joueur j2) {
		assert ((j1 != null) && (j2 != null));
		this.j1 = j1;
		this.j2 = j2;
	}

	/**Construire un arbitre à partir des deux joueurs de la partie et sa confiance.
	 *
	 * @param j1 premier joueur.
	 * @param j2 deuxième joueur.
	 * @param confiance la confiance de l'arbitre.
	 */
	public Arbitre(Joueur j1, Joueur j2, boolean confiance) {
		this(j1, j2);
		this.confiance = confiance;
			}

	/**Indiquer à chaque fois le joueur qui doit jouer.
	 *
	 * @param res paramètre boolean servant à basculer le tour entre les deux joueurs.
	 * @return le joueur qui doit jouer.
	 */
	public Joueur tour(boolean res) {
		if (res) {
			return this.j1;
		} else {
			return this.j2;
			}
			}

	/**Gérer la partie entre les deux joueurs.
	 *
	 * @param jeu le jeu en cours.
	 */
	public void arbitrer(Jeu jeu) {
		assert (jeu != null);
		assert (jeu.getNombreAllumettes() > 0);
		boolean res = true;
		while (jeu.getNombreAllumettes() > 0) {
			try {
				Joueur tour = tour(res);
				System.out.println("\nAllumettes restantes : "
				+ jeu.getNombreAllumettes());
				int prise = this.getPriseConfiant(jeu, tour);
				int valeur = gererCoup(prise, res, jeu);
				this.resultat(jeu, res);
				if (valeur == 0) {
					res = !res;
				}
			} catch (OperationInterditeException e) {
				Joueur tricheur = tour(res);
				System.out.println("[Je triche...]");
				System.out.println("Abandon de la partie car "
				+ tricheur.getNom() + " triche !");
				break;
				}
				}
				}

	/**retourner la chaine allumette(s) en se basant sur si le nombre d'allumettes pris
	 *  est singulier ou pluriel.
	 * @param prise
	 * @return la chaine allumette(s)
	 */
	public String getChaine(int prise) {
		if (prise <= 1) {
			return " allumette.";
		} else {
			return " allumettes.";
				}
				}

	/**indiquer en se basant sur la confiance de l'arbitre si on doit arrêter le jeu
	 * quand il a triche ou pas.
	 * @param jeu jeu en cours.
	 * @param j joueur tricheur potentiel.
	 * @return le nombre choisi par j au cas où l'arbitre est confiant ou le joueur ne
	 * triche pas.
	 */
	public int getPriseConfiant(Jeu jeu, Joueur joueur) {
		boolean res = !(joueur.getStrategie() instanceof StrategieTricheur);
		if ((joueur.getStrategie() instanceof StrategieHuman) && (!this.confiance)) {
			return joueur.getPrise(new JeuAntitriche(jeu.getNombreAllumettes()));
		} else if (res || (this.confiance)) {
			return joueur.getPrise(jeu);
		} else {
			return joueur.getPrise(new JeuAntitriche(jeu.getNombreAllumettes()));
				}
				}

	/**Anoncer le resultat de la partie.
	 *
	 * @param jeu le jeu en cours.
	 * @param res valeur boolean indiquant le tour.
	 */
	public void resultat(Jeu jeu, boolean res) {
		if (jeu.getNombreAllumettes() == 0) {
			Joueur gagnant = tour(!res);
			System.out.println("\n" + tour(res).getNom() + " perd !");
			System.out.println(gagnant.getNom() + " gagne !");
	}
	}

	/**Gérer le coup de chaque joueur.
	 *
	 * @param prise le coup de joueur.
	 * @param tour le joueur au cours de jeu.
	 * @param jeu le jeu en cours.
	 */
	public int gererCoup(int prise, boolean res, Jeu jeu) {
		try {
			if (prise == StrategieTricheur.TRICHE) {
				System.out.println("[Allumettes restantes : 2]");
				System.out.println(tour(res).getNom() + " prend 1 allumette.");
				jeu.retirer(1);
			} else {
				System.out.println(tour(res).getNom() + " prend " + prise
						+ getChaine(prise));
				jeu.retirer(prise);
			}
			return 0;
		} catch (CoupInvalideException e) {
			System.out.println("Impossible ! Nombre invalide : " + e.getCoup()
				+ " (" + e.getProbleme() + ")");
			return -1;
			}

	}
	}

