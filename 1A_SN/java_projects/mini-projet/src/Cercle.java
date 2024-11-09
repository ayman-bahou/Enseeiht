import java.awt.Color;
/** Cercle modélise la forme géométrique d'un cercle dans un plan.
 * Un cercle peut être affiché et translaté.
 * on peut déterminer si le cercle contient un point.
 *
 * @author  Ayman Bahou
 */
public class Cercle implements Mesurable2D {

	/** le centre du cercle.
	 */
	private Point centre;

	/** le rayon du cercle.
	 */
	private double rayon;

	/** la couleur du cercle.
	 */
	private Color couleur;

	/** la Constante pi.
	 */
	public static final double PI = Math.PI;

	/** Construire un cercle à partir de son centre et de son rayon.
	 * @param p centre du cercle
	 * @param r rayon du cercle
	 */
	public Cercle(Point p, double r) {
		assert p != null : "p doit être non null";
		assert r > 0.0 : "rayon doit positive";
    		this.centre = new Point(p.getX(), p.getY());
    		this.rayon = r;
    		this.couleur = Color.blue;
	}

	/** Construire un cercle à partir de deux points diamétralement opposés.
	 * @param p1 premier point
	 * @param p2 deuxième point
	 */
	public Cercle(Point p1, Point p2) {
		assert p1 != null : "p1 doit être non null";
		assert p2 != null : "p2 doit être non null";
		assert (p1.getX() != p2.getX()) || (p1.getY() != p2.getY()) : "p1!=p2";
		double x12 = (p1.getX() + p2.getX()) / 2;
		double y12 = (p1.getY() + p2.getY()) / 2;
    		this.centre = new Point(x12, y12);
    		this.rayon = p1.distance(p2) / 2;
    		this.couleur = Color.blue;
	}

	/** Construire un cercle à partir de deux points diamétralement opposés.
	 * @param p1 premier point
	 * @param p2 deuxième point
	 * @param c couleur du cercle
	 */
	public Cercle(Point p1, Point p2, Color c) {
		this(p1, p2);
		this.couleur = c;
	}

	/** Obtenir le rayon du cercle.
	 * @return rayon du cercle
	 */
	public double getRayon() {
    		return this.rayon;
	}

	/** Obtenir le centre du cercle.
	 * @return centre du cercle
	 */
	public Point getCentre() {
    		return new Point(this.centre.getX(), this.centre.getY());
	}

	/** Obtenir le diametre du cercle.
	 * @return diamètre du cercle
	 */

	public double getDiametre() {
    		return 2 * this.rayon;
	}

	/** Obtenir la couleur du cercle.
	 * @return couleur du cercle.
	 */
	public Color getCouleur() {
    		return this.couleur;
	}

	/** Changer le rayon du cercle.
	 * @param r nouveau rayon
	 */
	public void setRayon(double r) {
		assert r > 0.0 : "rayon doit être positif";
    		this.rayon = r;
	}

	/** Changer le diamètre du cercle.
	 * @param d nouveau diamètre
	 */
	public void setDiametre(double d) {
		assert d > 0.0 : "diamètre doit être positif";
    		this.rayon = d / 2;
	}

	/** Changer la couleur du cercle.
	 * @param c nouvelle couleur
	 */
	public void setCouleur(Color c) {
		assert c != null : "c doit être non null";
    		this.couleur = c;
	}

	/** Translater le cercle.
	* @param dx déplacement suivant l'axe des X
	* @param dy déplacement suivant l'axe des Y
	*/
	public void translater(double dx, double dy) {
    		this.centre.translater(dx, dy);
	}

	/** Verifier l'appartenance d'un point au cercle.
	 * @param p point dont on veut verifier l'appartenance au cercle
	 * @return la proposition 'cercle contient p'
	 */
	public boolean contient(Point p) {
		assert p != null : "le point ne doit pas être null";
    		return (this.centre.distance(p) <= this.rayon);
	}

	/** Créer un cercle à partir d'un point qui joue le role du centre.
	 *  et un autre qui est un point de circonférence.
	 * @param p1 centre du cercle
	 * @param p2 point de circonférence du cercle
	 * @return cercle crée
	 */
	public static Cercle creerCercle(Point p1, Point p2) {
		assert p1 != null : "p1 doit être non null";
		assert p2 != null : "p2 doit être non null";
		assert (p1.getX() != p2.getX()) || (p1.getY() != p2.getY()) : "p1!=p2";
		return new Cercle(p1, p1.distance(p2));
	}

	/** Calculer l'aire du cercle.
	 * @return aire du cercle
	 */
    	public double aire() {
    		return PI * Math.pow(this.rayon, 2);
    	}

	/** Calculer le perimètre du cercle.
	 * @return perimètre du cercle
	 */
    	public double perimetre() {
    		return PI * 2 * this.rayon;
    	}

	/** retourne le cercle affiché.
	 * @return cercle affiché
	 */
    	public String toString() {
    		return ("C" + this.rayon + "@" + this.centre.toString());
    	}

	/** Afficher un cercle.*/
	public void afficher() {
		System.out.print(this);
	}

}

