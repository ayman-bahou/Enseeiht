package GestionMonopoly;

public class Plateau {
    private Case[] cases;

    public Plateau() {
        this.cases = new Case[41];
        creerCases();
    }
    
    private void creerCases() {
    	//Case de départ
        cases[0] = new Casedepart("START", 0);
        cases[1] = new CaseTerrain("Rue Bayard", 60 , new int[]{2,4,10,30,90,160,250}, "Marron", 50, 1);
        cases[2] = new CaseCommunaute("Case Communauté", 2);
        cases[3] = new CaseTerrain("Rue Matabiau", 60, new int[]{4,8,20,60,180,320,450}, "Marron", 50, 3);
        cases[4] = new CaseImpots("IMPOTS SUR LE REVENU", 4, 200);
        cases[5] = new CaseGare("Gare Matabiau", 200, new int[]{25,50,100,200,0,0,0}, 5);
        cases[6] = new CaseTerrain("Rue De La Colombette", 100, new int[]{6,12,30,90,270,400,550}, "Bleu Ciel", 50, 6);
        cases[7] = new CaseChance("Case Chance", 7);
        cases[8] = new CaseTerrain("Rue Saint-Rome", 100, new int[]{6,12,30,90,270,400,550}, "Bleu Ciel", 50, 8);
        cases[9] = new CaseTerrain("Rue des Filatiers", 120, new int[]{8,16,40,100,300,450,600}, "Bleu Ciel", 50, 9);
        cases[10] = new CasePrison("Visite Simple", 10);
        cases[11] = new CaseTerrain("Place Esquirol", 140, new int[]{10,20,50,150,450,625,750}, "Violet", 100, 11);
        //cases[12] = new CaseService("Compagnie de distribution d'électricité", 150,12);
        cases[13] = new CaseTerrain("Avenue Honoré Serres", 140, new int[]{10,20,50,150,450,625,750}, "Violet", 100,13);
        cases[14] = new CaseTerrain("Place Wilson", 160, new int[]{12,24,60,180,500,700,900}, "Violet", 100,14);
        cases[15] = new CaseGare("Réseau de métro", 200, new int[]{25,50,100,200,0,0,0},15);
        cases[16] = new CaseTerrain("Rue Gambetta", 180, new int[]{14,28,70,200,550,750,950}, "Orange", 100,16);
        cases[17] = new CaseCommunaute("Case Communauté", 17);
        cases[18] = new CaseTerrain("Rue de la République", 180, new int[]{14,28,70,200,550,750,950}, "Orange", 100,18);
        cases[19] = new CaseTerrain("Boulevard Lazare-Carnot", 200, new int[]{16,32,80,220,600,800,1000}, "Orange", 100,19);
        //Case Parking
        cases[20] = new CaseImpots("Parking Gratuit", 20, 0);
        cases[21] = new CaseTerrain("Quai de la Daurade", 220, new int[]{18,36,90,250,700,875,1050}, "Rouge", 150,21);
        cases[22] = new CaseChance("Case Chance",22);
        cases[23] = new CaseTerrain("Place Victor-Hugo", 220, new int[]{18,36,90,250,700,875,1050}, "Rouge", 150,23);
        cases[24] = new CaseTerrain("Place Occitanie", 240, new int[]{20,40,100,300,750,925,1100}, "Rouge", 150,24);
        cases[25] = new CaseGare("Gare St-Agne", 200, new int[]{25,50,100,200,0,0,0},25);
        cases[26] = new CaseTerrain("Allé Jean-Jaurès", 260, new int[]{22,44,110,330,800,975,1150}, "Jaune", 150,26);
        cases[27] = new CaseTerrain("Rue du Taur", 260, new int[]{22,44,110,330,800,975,1150}, "Jaune", 150, 27);
        // cases[28] = new CaseCompagnie("Compagnie de distribution des eaux", 150,28))));
        cases[29] = new CaseTerrain("Place Saint-Georges", 280, new int[]{24,48,120,360,850,1025,1200}, "Jaune", 150,29);
        cases[30] = new CasePrison("Direction La Prison", 30);
        cases[31] = new CaseTerrain("Rue de Metz", 300, new int[]{26,52,130,390,900,1100,1275}, "Vert", 200,31);
        cases[32] = new CaseTerrain("Quai de Tounis", 300, new int[]{26,52,130,390,900,1100,1275}, "Vert", 200,32);
        cases[33] = new CaseCommunaute("Case Communauté", 33);
        cases[34] = new CaseTerrain("Rue Croix Baragnon", 320, new int[]{28,56,150,450,1000,1200,1400}, "Vert", 200,34);
        cases[35] = new CaseGare("Téléo téléphérique", 200, new int[]{25,50,100,200,0,0,0} ,35);
        cases[36] = new CaseChance("Case Chance",36);
        cases[37] = new CaseTerrain("Rue d'Alsace-Lorraine", 350, new int[]{35,70,175,500,1100,1300,1500}, "Bleu Roi", 200,37);
        cases[38] = new CaseImpots("TAXE DE LUXE", 38, 100);
        cases[39] = new CaseTerrain("Rue du Capitole", 400, new int[]{50,100,200,600,1400,1700,2000}, "Bleu Roi", 200,39);
        cases[40] = new CasePrison("Case Prison", 40);
    }

    public Case getCase(int position) {
        return cases[position % cases.length];
    }
}