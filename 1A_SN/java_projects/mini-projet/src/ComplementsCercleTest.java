import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Classe de compléments de test.
 * @author	Ayman Bahou
 * @version	$Revision$
 */
public class ComplementsCercleTest {
	// précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;
	
	//les points du sujet
	private Point A,B;
	
	//le cercle du sujet
	private Cercle C;
	
	@Before public void setUp() {		
		//Construire le point
		A=new Point(-2,3);
		B=new Point(4,3);
		
		//Construire le cercle
		C=Cercle.creerCercle(A,B);
		
}
	/** Vérifier si deux points ont mêmes coordonnées.
	  * @param p1 le premier point
	  * @param p2 le deuxième point
	  */
	static void memesCoordonnees(String message, Point p1, Point p2) {
		assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
		assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
	}
	
	@Test public void testerE18() {
		A.translater(3,1);
		memesCoordonnees("E18 : erreur il faut pas que le centre se translate",C.getCentre(),new Point(-2,3));
		
	}
	
}