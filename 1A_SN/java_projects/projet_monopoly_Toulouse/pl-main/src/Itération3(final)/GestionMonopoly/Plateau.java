package GestionMonopoly;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

import GestionMonopoly.Cartes.Carte;
import GestionMonopoly.Cartes.CarteArgent;
import GestionMonopoly.Cases.*;
import Interface_graphique.VuePlateau;
import Interface_graphique.FenetreCases.FenetreMessageSimple;

/**
 * La classe Plateau représente le plateau de jeu du Monopoly.
 * Elle contient les cases, les joueurs, les dés, les cartes, et gère le déroulement du jeu.
 */
public class Plateau {

    private final int nbFaces = 6;
    private final int nbCartesChance = 1;
    private final int nbCarteCommunaute = 1;
    public static final int NB_CASES = 41;
    public static final int ID_CASE_PRISON = 40;
    public static final int ID_CASE_VISITE_SIMPLE = 10;

    private PaireDes des;
    private ArrayList<JoueurMonopoly> joueurs;
    private Carte[] cartesChance;
    private Carte[] cartesCommunaute;
    private Case[] cases;
    private int nbTour;
    private boolean tourFini;
    private boolean aLancerDes = false;
    private VuePlateau fenetrePlateau;
    private JFrame fenetreAction = null;

    // --------- Groupes de propriétés -------------
    private GroupeProprietes marron = new GroupeProprietes(new Color(125, 93, 57));
    private GroupeProprietes Cyan = new GroupeProprietes(Color.cyan);
    private GroupeProprietes Pink = new GroupeProprietes(Color.pink);
    private GroupeProprietes Orange = new GroupeProprietes(Color.orange);
    private GroupeProprietes Rouge = new GroupeProprietes(Color.red);
    private GroupeProprietes Jaune = new GroupeProprietes(Color.yellow);
    private GroupeProprietes Vert = new GroupeProprietes(Color.green);
    private GroupeProprietes Bleu = new GroupeProprietes(new Color(29, 33, 128));

    /**
     * Constructeur de la classe Plateau.
     * @param joueurs La liste des joueurs participant à la partie.
     */
    public Plateau(ArrayList<JoueurMonopoly> joueurs) {
        this.des = new PaireDes(nbFaces);
        this.joueurs = joueurs;
        this.nbTour = 1;
        this.fenetrePlateau = new VuePlateau(this);
        this.cartesChance = new Carte[nbCartesChance];
        this.cartesCommunaute = new Carte[nbCarteCommunaute];
        this.cases = new Case[41];
        creerCases();
        creerCartesChance();
        creerCartesCommunaute();
    }

    // ||||||||||||||||||||||||| Méthodes privées ||||||||||||||||||||||||||||||
    
    private void creerCases() {
        //TODO : A refaire avec les nouveaux paramètres suites au clean
        // Attention : Pour numéroter les cases : la case 0 est la case de depart ensuite on compte dans le sens du plateau
        //             Cas particulier : case 10 = visite simple et case 40 = case prison
        cases[0] = new CaseDepart("START", 0, 200);             
        cases[1] = new Propriete("Rue Bayard",1,marron , 60 , new int[]{2,4,10,30,90,160,250},50);
        cases[2] = new CaseCommunaute("Case Communauté", 2);
        cases[3] = new Propriete("Rue Matabiau", 3, marron,60, new int[]{4,8,20,60,180,320,450}, 50);
        cases[4] = new CaseImpots("IMPOTS SUR LE REVENU", 4, 200);
        cases[5] = new Gare("Gare Matabiau",5, 200, new int[]{25,50,100,200,0,0,0});         
        cases[6] = new Propriete("Rue De La Colombette",6, Cyan,100, new int[]{6,12,30,90,270,400,550}, 50);
        cases[7] = new CaseChance("Case Chance", 7);
        cases[8] = new Propriete("Rue Saint-Rome", 8,Cyan,100, new int[]{6,12,30,90,270,400,550},50);
        cases[9] = new Propriete("Rue des Filatiers",9,Cyan, 120, new int[]{8,16,40,100,300,450,600}, 50);
        cases[10] = new CaseVisiteSimple("Visite Simple", ID_CASE_VISITE_SIMPLE);
        cases[11] = new Propriete("Place Esquirol",11,Pink, 140, new int[]{10,20,50,150,450,625,750}, 100);
        cases[12] = new Service("Compagnie de distribution d'électricité", 12,150);
        cases[13] = new Propriete("Avenue Honoré Serres", 13, Pink, 140, new int[]{10,20,50,150,450,625,750},100);
        cases[14] = new Propriete("Place Wilson", 14, Pink, 160, new int[]{12,24,60,180,500,700,900}, 100);
        cases[15] = new Gare("Réseau de métro",15, 200, new int[]{25,50,100,200,0,0,0});        
        cases[16] = new Propriete("Rue Gambetta", 16,Orange, 180, new int[]{14,28,70,200,550,750,950},100);
        cases[17] = new CaseCommunaute("Case Communauté", 17);
        cases[18] = new Propriete("Rue de la République",18, Orange, 180, new int[]{14,28,70,200,550,750,950}, 100);
        cases[19] = new Propriete("Boulevard Lazare-Carnot", 19, Orange, 200, new int[]{16,32,80,220,600,800,1000}, 100);
        cases[20] = new CaseParking("Parking Gratuit", 20);         
        cases[21] = new Propriete("Quai de la Daurade", 21, Rouge,220, new int[]{18,36,90,250,700,875,1050}, 150);
        cases[22] = new CaseChance("Case Chance",22);
        cases[23] = new Propriete("Place Victor-Hugo",23, Rouge, 220, new int[]{18,36,90,250,700,875,1050},150);
        cases[24] = new Propriete("Place Occitanie", 24, Rouge, 240, new int[]{20,40,100,300,750,925,1100},150);
        cases[25] = new Gare("Gare St-Agne",25, 200, new int[]{25,50,100,200,0,0,0});      
        cases[26] = new Propriete("Allé Jean-Jaurès", 26, Jaune, 260, new int[]{22,44,110,330,800,975,1150}, 150);
        cases[27] = new Propriete("Rue du Taur", 27, Jaune, 260, new int[]{22,44,110,330,800,975,1150},150);
        cases[28] = new Service("Compagnie de distribution des eaux", 28, 150);
        cases[29] = new Propriete("Place Saint-Georges", 29, Jaune, 280, new int[]{24,48,120,360,850,1025,1200},150);        
        cases[30] = new CaseDirectionPrison("Direction La Prison", 30);        
        cases[31] = new Propriete("Rue de Metz", 31, Vert, 300, new int[]{26,52,130,390,900,1100,1275},200);
        cases[32] = new Propriete("Quai de Tounis",32, Vert, 300, new int[]{26,52,130,390,900,1100,1275}, 200);
        cases[33] = new CaseCommunaute("Case Communauté", 33);
        cases[34] = new Propriete("Rue Croix Baragnon", 34, Vert, 320, new int[]{28,56,150,450,1000,1200,1400}, 200);
        cases[35] = new Gare("Téléo téléphérique", 35, 200, new int[]{25,50,100,200,0,0,0});
        cases[36] = new CaseChance("Case Chance",36);        
        cases[37] = new Propriete("Rue d'Alsace-Lorraine",37, Bleu, 350, new int[]{35,70,175,500,1100,1300,1500},200);
        cases[38] = new CaseTaxeLuxe("Taxe de Luxe",38,100);
        cases[39] = new Propriete("Rue du Capitole",39, Bleu, 400, new int[]{50,100,200,600,1400,1700,2000}, 200);
        cases[40] = new CasePrison("Case Prison", ID_CASE_PRISON , 50);
    }

    private void creerCartesChance(){
        cartesChance[0] = new CarteArgent("Chanceux", "Vous avez trouver une liasse de billet par terre", 100);
    }

    private void creerCartesCommunaute(){
        cartesCommunaute[0] = new CarteArgent("Quartier", "Votre quartier veut rénover votre rue, vous devez participer à hauteur de 200 M$", 200);
    }

    // ||||||||||||||||||||||||| Requêtes ||||||||||||||||||||||||||||||

    /**
     * Récupère la case située à une position donnée sur le plateau.
     * @param position La position de la case.
     * @return La case à la position spécifiée.
     */
    public Case getCase(int position) {
        return cases[position];
    }

    /**
     * Récupère les dés associées au plateau.
     * @return La case à la position spécifiée.
     */
    public PaireDes getDes() {
        return this.des;
    }

    /**
     * Récupère la case sur laquelle se trouve un joueur.
     * @param joueur Le joueur dont on veut connaître la case.
     * @return La case sur laquelle se trouve le joueur.
     */
    public Case getCase(JoueurMonopoly joueur){
        return this.getCase(joueur.getPosition());
    }

    /**
     * Récupère la case sur laquelle se trouve le joueur dont c'est le tour.
     * @return La case sur laquelle se trouve le joueur actif.
     */
    public Case getCaseJoueurActif(){
        return this.getCase(getJoueurActif().getPosition());
    }

    /**
     * Récupère le joueur actif, c'est-à-dire celui dont c'est le tour de jouer.
     * @return Le joueur actif.
     */
    public JoueurMonopoly getJoueurActif(){
        // Les joueurs joue chacun à leur tour en commencant par le premier
        return this.joueurs.get((nbTour - 1) % joueurs.size());
    }

    /**
     * Récupère un joueur à partir de son identifiant.
     * @param id L'identifiant du joueur.
     * @return Le joueur correspondant à l'identifiant spécifié.
     */
    public JoueurMonopoly getJoueur(int id){
        return this.joueurs.get(id);
    }

    /**
     * Récupère le nombre de joueurs.
     * @return nombre de joueurs
     */
    public int getNbJoueurs(){
        return this.joueurs.size();
    }

    /**
     * Détermine le joueur ayant le plus d'argent.
     * @return Le joueur ayant le plus d'argent.
     */
    public JoueurMonopoly estPremier(){
        //TODO : Et si il y a une égalité ?
        int maxSolde = 0;
        JoueurMonopoly premierJoueur = null;
        for (JoueurMonopoly joueur : joueurs){
            if (joueur.getSolde() > maxSolde){
                maxSolde = joueur.getSolde();
                premierJoueur = joueur;
            }
        }
        return premierJoueur;
    }

    /**
     * Tire une carte chance aléatoire parmi celles disponibles.
     * @return La carte chance tirée aléatoirement.
     */
    public Carte tirerCarteChance(){
        if (this.cartesChance.length == 0) {
            return null;
        }
        Random alea = new Random();
        int index = alea.nextInt(this.cartesChance.length);
        return this.cartesChance[index];
    }

    /**
     * Tire une carte communauté aléatoire parmi celles disponibles.
     * @return La carte communauté tirée aléatoirement.
     */
    public Carte tirerCarteCommunaute(){
        if (this.cartesCommunaute.length == 0) {
            return null;
        }
        Random alea = new Random();
        int index = alea.nextInt(this.cartesCommunaute.length);
        return this.cartesCommunaute[index];
    }

    /**
     * Détermine si la partie est terminée en fonction du nombre de joueurs en faillite.
     * @return true si la partie est terminée, false sinon.
     */
    public boolean partieFinie(){
        int nbBanqueroute = 0;
        for (JoueurMonopoly joueur : joueurs){
            if (joueur.estBanqueroute()){
                nbBanqueroute++;
            }
        }
        //La partie se finit si 2 personnes font banqueroute
        //Cas particulier : si 2 joueurs
        if (joueurs.size() == 2){
            return nbBanqueroute >=1;
        }
        return nbBanqueroute >=2; 
    }

    public boolean getALancerDes(){
        return this.aLancerDes;
    }

    // ||||||||||||||||||||||||| Commandes ||||||||||||||||||||||||||||||

    public void addTour(){
        this.nbTour++;
    }

    public void setFenetreAction(JFrame fenetre){
        if (this.fenetreAction != null){
            this.fenetreAction.dispose();
        }
        this.fenetreAction = fenetre;
    }

    public void deplacerJoueur(JoueurMonopoly joueur, int deplacement){
        int oldPosition = joueur.getPosition();
        joueur.deplacer(deplacement);
        fenetrePlateau.updatePositionPion(joueur.getPion());
        if (oldPosition > joueur.getPosition()){
            getCase(0).action(joueur, this);
        }
    }

    public void deplacerJoueurActif(int deplacement){
        deplacerJoueur(getJoueurActif(), deplacement);
        fenetrePlateau.updatePositionPion(getJoueurActif().getPion());
    }

    public void setPositionJoueur(JoueurMonopoly joueur, int IdCase){
        joueur.setPosition(IdCase);
        fenetrePlateau.updatePositionPion(joueur.getPion());
    }

    public void afficheFenetre(){
        // Dimension de la fenêtre
        this.fenetrePlateau.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Rendre la fenêtre visible
        this.fenetrePlateau.setVisible(true);
    }

    public void tourEstFini(){
        tourFini = true;
    }

    public void attendre(int t){
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lancerDes(){
        this.aLancerDes = true;
    }

    public void jouerTour(){
        fenetrePlateau.updateJoueurActif();
        if (!getJoueurActif().estEnPrison()){
            // 1 - Le joueur actif lance les dés
            while (!aLancerDes){attendre(1);}

            this.des.lancer();
            JFrame fenetreLancerDes = new FenetreMessageSimple(getJoueurActif().getNom() + " lance les dés ...", Color.white, Color.black);
            setFenetreAction(fenetreLancerDes);
            fenetreLancerDes.setVisible(true);
            attendre(1000);
            setFenetreAction(null);

            // 2 - Affiché le résultat du dé
            JFrame fenetreDes = new FenetreMessageSimple(getJoueurActif().getNom() + " a fait " + des.getDe1() +" et "+ des.getDe2() + "\n Résultat : " + des.getResultat(), Color.white, Color.black);
            setFenetreAction(fenetreDes);
            fenetreDes.setVisible(true);

            attendre(2000);
            setFenetreAction(null);
            aLancerDes = true;

            // 3 - Le joueur actif avance
            deplacerJoueurActif(getDes().getResultat());
            System.out.print("Le joueur " + getJoueurActif().getNom() + " est tombé sur la case " + getCaseJoueurActif().getNom());

            // 4 - Activation de l'effet de la case sur laquelle il se trouve
            getCaseJoueurActif().action(getJoueurActif(), this);

            // 5 - Son tour est fini
            while (!tourFini){
                attendre(1);
            } //Atendre t'en que le joueur ne signifit pas qu'il a terminé
            tourFini = false;
            aLancerDes = false;

            if (!des.estDouble()){ //Rejoue si il a fait un double
                nbTour ++;
                return;
            }

            JFrame fenetreDouble = new FenetreMessageSimple(getJoueurActif().getNom() + " rejoue, elle avait fait un double !", Color.white, Color.black);
            setFenetreAction(fenetreDouble);
            fenetreDouble.setVisible(true);
            attendre(1000);
            setFenetreAction(null);
        } else {
            getCase(ID_CASE_PRISON).action(getJoueurActif(), this);
            // 5 - Son tour est fini
            while (!tourFini){
                attendre(1);
            } //Atendre t'en que le joueur ne signifit pas qu'il a terminé
            tourFini = false;
            nbTour ++;
        }
    }
}