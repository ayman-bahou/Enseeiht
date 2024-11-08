package GestionMonopoly;

public class GestionEvenementsMonopoly {
    private Plateau plateau;
    private Des des;

    public GestionEvenementsMonopoly(Plateau plateau, Des des) {
        this.plateau = plateau;
        this.des = des;
    }

    public void jouerTour(JoueurMonopoly joueur) {
        int[] resultatsDes = des.lancer2des();
        int totalDes = resultatsDes[0] + resultatsDes[1];

        // Vérifie si le joueur fait un double
        if (resultatsDes[0] == resultatsDes[1]) {
            joueur.addToursEnPrison(); // Utilise addToursEnPrison pour gérer les doubles
            if (joueur.getToursPrison() == 3) {
                envoyerEnPrison(joueur);
                return;
            }
        } else {
            joueur.setToursEnPrison(0); // Reset du nombre de tours en prison si pas de double
        }

        // Déplacement du joueur
        joueur.deplacerDe(totalDes);
        Case caseCourante = plateau.getCase(joueur.getPosition());

        // Gestion des événements de la case
        gererCase(joueur, caseCourante);

        // Si le joueur passe par la case départ
        if (joueur.getPosition() >= 40) {
            joueur.setPosition(joueur.getPosition() % 40); // S'assure que la position est valide
            joueur.ajouterArgent(200); // Argent pour le passage sur la case départ
        }

        // Si double et joueur n'est pas en prison, il rejoue
        if (resultatsDes[0] == resultatsDes[1] && !joueur.getEnPrison()) {
            jouerTour(joueur);
        }
    }

    private void gererCase(JoueurMonopoly joueur, Case caseCourante) {
        if (caseCourante instanceof Casedepart) {
            joueur.ajouterArgent(200); // Donne 200€ au joueur qui passe ou tombe sur la case départ.
        } else if (caseCourante instanceof CasePrison) {
            envoyerEnPrison(joueur); // Envoie le joueur en prison.
        } else if (caseCourante instanceof CaseImpots) {
            ((CaseImpots) caseCourante).action(joueur); // Le joueur paie les impôts définis par la case.
        } else if (caseCourante instanceof CaseTerrain) {
            CaseTerrain terrain = (CaseTerrain) caseCourante;
            // Vérifie si la case a un propriétaire et si le propriétaire n'est pas le
            // joueur actuel
            if (terrain.getprop() != null && !terrain.getprop().equals(joueur)) {
                joueur.payerloyer(terrain); // Le joueur paie le loyer selon les règles définies dans payerloyer.
            }
        } else if (caseCourante instanceof CaseGare) {
            // Traitement similaire pour les gares, si nécessaire
            CaseGare gare = (CaseGare) caseCourante;
            if (gare.getprop() != null && !gare.getprop().equals(joueur)) {
                joueur.payerloyergare(gare);
            }
            // } else if (caseCourante instanceof CaseService) {
            // Traitement similaire pour les services, si nécessaire
            // CaseService service = (CaseService) caseCourante;
            // if (service.getProprietaire() != null &&
            // !service.getProprietaire().equals(joueur)) {
            // joueur.payerservice(service);
        }
    }

    private void envoyerEnPrison(JoueurMonopoly joueur) {
        joueur.setEstPrison(true);
        joueur.setPosition(40); // Supposer qu'il y a une méthode pour obtenir la position de la prison
        joueur.setToursEnPrison(0); // Reset les tours en prison après l'envoi en prison
    }
}

