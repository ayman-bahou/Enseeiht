package GestionMonopoly;
import java.util.Random;

/**
 * La classe PaireDes représente une paire de dés.
 */
public class PaireDes {
    private int nbFaces;
    private int de1 = 0;
    private int de2 = 0;
    
    /**
     * Constructeur de la classe PaireDes.
     * @param nbFaces Le nombre de faces des dés.
     */
    public PaireDes(int nbFaces) {
        this.nbFaces = nbFaces;
    }

    // ||||||||||||||||||||||||| Requêtes ||||||||||||||||||||||||||||||||

    /**
     * Renvoie la valeur du premier dé.
     * @return La valeur du premier dé.
     */
    public int getDe1() {
        return this.de1;
    }

    /**
     * Renvoie la valeur du deuxième dé.
     * @return La valeur du deuxième dé.
     */
    public int getDe2() {
        return this.de2;
    }

    /**
     * Vérifie si les deux dés affichent le même résultat.
     * @return true si les deux dés affichent le même résultat, false sinon.
     */
    public boolean estDouble(){
        return this.de1 == this.de2;
    }

    /**
     * Renvoie la somme des valeurs des deux dés.
     * @return La somme des valeurs des deux dés.
     */
    public int getResultat(){
        return this.de1 + this.de2;
    }

    // ||||||||||||||||||||||||| Commandes ||||||||||||||||||||||||||||||||

    /**
     * Lance les deux dés, générant des valeurs aléatoires pour chacun d'eux.
     */
    public void lancer() {
        Random alea = new Random();
        this.de1 = alea.nextInt(nbFaces) + 1;
        this.de2 = alea.nextInt(nbFaces) + 1;
    }
}