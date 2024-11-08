package GestionMonopoly;
import java.util.Random;

public class Des {
    private int nbrfaces;
    private int valeurDe1;
    private int valeurDe2;
    
    public Des() {
        this.nbrfaces = 6;
        this.valeurDe1 = 0; // se modifie apres avoir lance les des
        this.valeurDe2 = 0; // se modifie apres avoir lance les des
    }
    
    public int[] lancer2des() {
        int[] resultat = new int[2];
        int i;
        for ( i = 0; i < 2; i++) {
            Random alea = new Random();
            resultat[i] = alea.nextInt( nbrfaces ) + 1;
        }
        this.valeurDe1 = resultat[0];
        this.valeurDe2 = resultat[1];

        return resultat;

    }

    public int getValeurDe1() {
        return this.valeurDe1;
    }

    public int getValeurDe2() {
        return this.valeurDe2;
    }
    
    public int getNbrfaces() {
        return this.nbrfaces;
    }
}
