package Classes;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private List<Case> cases;

    public Plateau() {
        this.cases = new ArrayList<>();
        creerCases();
        lierCases();
    }

    private void creerCases() {
        int id = 0;
        // cases.add(new CaseDepart("START"));
        cases.add(new CaseTerrain("Rue Bayard", 60000000,null, "marron", 0, id++));
        cases.add(new CaseCommunaute());
        cases.add(new CaseTerrain("Rue Matabiau", 60000000, null, "marron", 0, id++));
        cases.add(new CaseImpots("IMPOTS SUR LE REVENU", 200000000));
        cases.add(new CaseGare("Gare Matabiau", 200000000, null, id++));
        cases.add(new CaseTerrain("Rue De La Colombette", 100000000, null, "bleu ciel", 0, id++));
        cases.add(new CaseChance());
        cases.add(new CaseTerrain("Rue Saint-Rome", 100000000, null, "bleu ciel", 0, id++));
        cases.add(new CaseTerrain("Rue des Filatiers", 120000000, null, "bleu ciel", 0, id++));
        cases.add(new CasePrison("Visite Simple", 0, false, false, 0));
        cases.add(new CaseTerrain("Place Esquirol", 140000000, null, "rose", 0, id++));
        // cases.add(new CaseCompagnie());
        cases.add(new CaseTerrain("Avenue Honoré Serres", 140000000, null, "rose", 0,id++));
        cases.add(new CaseTerrain("Place Wilson", 160000000, null, "rose", 0,id++));
        cases.add(new CaseGare("Réseau de métro", 200000000, null,id++));
        cases.add(new CaseTerrain("Rue Gambetta", 180000000, null, "orange", 0,id++));
        cases.add(new CaseCommunaute());
        cases.add(new CaseTerrain("Rue de la République", 180000000, null, "orange", 0,id++));
        cases.add(new CaseTerrain("Boulevard Lazare-Carnot", 200000000, null, "orange", 0,id++));
        // cases.add(new CaseParking());
        cases.add(new CaseTerrain("Quai de la Daurade", 220000000, null, "rouge", 0,id++));
        cases.add(new CaseChance());
        cases.add(new CaseTerrain("Place Victor-Hugo", 220000000, null, "rouge", 0,id++));
        cases.add(new CaseTerrain("Place Occitanie", 240000000, null, "rouge", 0,id++));
        cases.add(new CaseGare("Gare St-Agne", 200000000, null,id++));
        cases.add(new CaseTerrain("Allé Jean-Jaurès", 260000000, null, "jaune", 0,id++));
        cases.add(new CaseTerrain("Rue du Taur", 260000000, null, "jaune", 0,id++));
        // cases.add(new CaseCompagnie());
        cases.add(new CaseTerrain("Place Saint-Georges", 280000000, null, "jaune", 0,id++));
        cases.add(new CasePrison("Direction La Prison", 0, false, false, 0));
        cases.add(new CaseTerrain("rue de Metz", 300000000, null, "vert", 0,id++));
        cases.add(new CaseTerrain("Quai de Tounis", 300000000, null, "vert", 0,id++));
        cases.add(new CaseCommunaute());
        cases.add(new CaseTerrain("Rue Croix Baragnon", 320000000, null, "vert", 0,id++));
        cases.add(new CaseGare("Téléo téléphérique", 200000000, null,id++));
        cases.add(new CaseChance());
        cases.add(new CaseTerrain("Rue d'Alsace-Lorraine", 350000000, null, "bleu", 0,id++));
        cases.add(new CaseImpots("TAXE DE LUXE", 100000000));
        cases.add(new CaseTerrain("Rue du Capitole", 400000000, null, "bleu", 0,id++));

    }

    private void lierCases() {
    }

    public Case getCase(int position) {
        return cases.get(position % cases.size());
    }

}
