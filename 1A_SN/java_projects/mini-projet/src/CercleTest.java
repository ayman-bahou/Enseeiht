import java.awt.Color;
import org.junit.*;
import static org.junit.Assert.*;

/**
  * Classe de test des exigences E12,E13 et E14.
  * @author Ayman Bahou
  * @version	$Revision$
  */
public class CercleTest {

	// précision pour les comparaisons réelle
	public final static double EPSILON = 0.001;

	// Les points du sujet
	private Point A, B, C, D, E;

	// Les cercles du sujet
	private Cercle C1, C2, C3;

	@Before public void setUp() {
		// Construire les points
		A = new Point(-2, 5);
		B = new Point(-4, 3);
		C = new Point(4, 1);
		D = new Point(8, 3);
		E = new Point(1, 1);

		// Construire les cercles
		C1 = new Cercle(A,B);
		C2 = new Cercle(C,D,Color.orange);
		C3 = Cercle.creerCercle(D, E);
	}

	/** Vérifier si deux points ont mêmes coordonnées.
	  * @param p1 le premier point
	  * @param p2 le deuxième point
	  */
	static void memesCoordonnees(String message, Point p1, Point p2) {
		assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
		assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
	}
	
	@Test public void testerE12() {
		memesCoordonnees("E12 : Centre de C1 incorrect",C1.getCentre(),new Point(-3,4));
		assertEquals("E12 : Rayon de C1 incorrect",C1.getRayon(),A.distance(B)/2,EPSILON);
		assertEquals(C1.getCouleur(),Color.blue);
	}
	
	@Test public void testerE13() {
		memesCoordonnees("E13 : Centre de C2 incorrect",C2.getCentre(),new Point(6,2));
		assertEquals("E13 : Rayon de C2 incorrect",C2.getRayon(),C.distance(D)/2,EPSILON);
		assertEquals(C2.getCouleur(),Color.orange);
		
	}
	
	@Test public void testerE14() {
		memesCoordonnees("E14 : Centre de C3 incorrect",C3.getCentre(),D);
		assertEquals("E14 : Rayon de C3 incorrect",C3.getRayon(),D.distance(E),EPSILON);
		assertEquals(C3.getCouleur(),Color.blue);
		
	}
}