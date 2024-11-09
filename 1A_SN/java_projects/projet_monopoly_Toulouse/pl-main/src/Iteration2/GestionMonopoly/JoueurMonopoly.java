package GestionMonopoly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Interface_graphique.Panneau_joueur;

public class JoueurMonopoly{
	
		private String nom;
		private int id;
		private int position;
		private int argent;
		private int toursEnPrison;
		private boolean estBanqueroute;
		private boolean enPrison;
		private int CarteSortiePrison;
		private ArrayList<CaseGare> gares;
		private ArrayList<CaseService> service;
		private Map<String, List<CaseTerrain>> terrainsparcouleur;
		private Panneau_joueur affichage;

		
		public JoueurMonopoly(String nom, int id, int argent) {
			assert(nom != null && id > 0 && argent > 0);
			this.nom = nom;
			this.id = id;
			this.argent = argent;
			this.position = 0;
			this.toursEnPrison = 0;
			this.CarteSortiePrison = 0;
			this.gares = new ArrayList<CaseGare>();
			this.service = new ArrayList<CaseService>();
			this.terrainsparcouleur = new HashMap<String, List<CaseTerrain>>();
			this.estBanqueroute = false;
			this.enPrison = false;
			this.affichage = new Panneau_joueur(nom,argent);
		}

		public Panneau_joueur getPanel(){
			return this.affichage;
		}
		
		public String getNom() {
			return this.nom;
		}
		
		public int getId() {
			return this.id;
		}
		
		public int getPosition() {
			return this.position;
		}
		
		public int getSolde() {
			return this.argent;
		}
		public int getToursPrison() {
			return this.toursEnPrison;
		}
		public boolean getEnPrison() {
			return this.enPrison;
		}
		public void setNom(String nom) {
			assert(nom != null);
			this.nom = nom;
		}
		
		public void setID( int id) {
			assert(id > 0);
			this.id = id;
		}
		
		public void setArgent( int argent) {
			assert(argent > 0);
			this.argent = argent;
		}
		
		// La position peut être égale à 0, ( case de départ ) ?
		public void setPosition(int pos) {
			assert(pos >= 0);
			this.position = pos;
		}

		public void deplacerDe(int dep) {
			assert(dep >= 0);
			this.position = this.position + dep;
		}
		
		public void setToursEnPrison(int toursEnPrison) {
			assert(toursEnPrison >= 0);
			this.toursEnPrison = toursEnPrison;
		}

		public void addToursEnPrison() {
			assert(this.toursEnPrison < 3);
			this.toursEnPrison = this.toursEnPrison + 1;
		}
		
		public void setEstPrison(boolean prison) {
			this.enPrison = prison;			
		}
		
				
		public void setEstBanqueroute() {
			if (this.argent == 0) {
			this.estBanqueroute = true;
			}
		}
		
		
		public void ajouterTerrain(CaseTerrain terrain) {
			assert(terrain != null && this.argent > terrain.getprixachat());
			if (terrain.peutacheterterrain()) {
				if (terrainsparcouleur.containsKey(terrain.getcouleur())) {
					List<CaseTerrain> terrains = terrainsparcouleur.get(terrain.getcouleur());
					terrains.add(terrain);
					affichage.addPropriété(terrain.getNom());
					terrainsparcouleur.replace(terrain.getcouleur(),terrains);
				} else {
					List<CaseTerrain> terrains = new ArrayList<CaseTerrain>();
					terrains.add(terrain);
					affichage.addPropriété(terrain.getNom());
					terrainsparcouleur.put(terrain.getcouleur(), terrains);
				}				
				this.argent -= terrain.getprixachat();				
			}else {
				System.out.println("Ce terrain appartient à un autre joueur");
			}
		}
		public boolean Groupecomplet(CaseTerrain terrain) {
			boolean Groupecomplet = false;
			List<CaseTerrain> terrains = terrainsparcouleur.get(terrain.getcouleur());
			int nbrprop = terrains.size(); //nbr de propriété pour la couleur, pour les marrons et bleu roi
			// il faut que ce nbr = 2 et pour les autres couleurs = 3 pour completer le groupe
			if ( terrain.getcouleur() == "Marron" || terrain.getcouleur() == "Bleu Roi") {
				if (nbrprop == 2) {
					Groupecomplet = true;
				}
			} else {
				if (nbrprop == 3) { 
					Groupecomplet = true;
				}
			}
			return Groupecomplet;
		}
		
		public void ajouterMaisonTerrain(CaseTerrain terrain) {
					
			if (Groupecomplet(terrain) == true) {
				terrain.ajouterMaison();
				this.argent -= terrain.getprixmaison();
			} else {
				System.out.println("Veuillez completez le groupe de la propriété avant de construire une maison");
			}			
		}
		
		public void vendreTerrain(CaseTerrain terrain) {
		    assert(terrain != null && terrain.getnbrmaison() == 0); 
		    // Tout d'abord on vérifie que ce terrain nous appartient
		    boolean Nousappartient = false;
		    for (List<CaseTerrain> liste : terrainsparcouleur.values()) {
	            if (liste.contains(terrain)) {
	                Nousappartient = true;
	            }
		    }
		    if (Nousappartient = true) {
		    	List<CaseTerrain> terrains = terrainsparcouleur.get(terrain.getcouleur());
		    	terrains.remove(terrain);  // on supprime ce terrain de la liste associé à sa couleur
		    	terrainsparcouleur.replace(terrain.getcouleur(),terrains);
		    	this.argent += terrain.getprixachat(); // vendre à la banque avec le même prix d'achat
		    } else {
		    	System.out.println("Ce terrain ne vous appartient pas");
		    }
		}
		
		public void VendreMaisonTerrain(CaseTerrain terrain) {
			terrain.vendremaison();
			this.argent += terrain.getprixmaison()/2; //Vendre une maison à 50% du prix d'achat.
		}
		
		public void ajouterGare(CaseGare gare) {
			assert(gare != null && this.argent > gare.getprixachat());
			if (gare.peutachetergare()) {
				gares.add(gare);
				affichage.addGare(gare.getNom());
				this.argent -= gare.getprixachat();
			}else {
				System.out.println("Cette gare appartient à un autre joueur");
			}
		}
		public void payerloyergare(CaseGare gare) {
			assert(this.position == gare.getId() &&  gare.getprop() != null && gare.getprop().getNom() != this.nom );
			int[] loyer = gare.getLoyer();
			if (gares.size() == 1) {
				this.argent -= loyer[0];
			} else if ( gares.size() == 2) {
				this.argent -= loyer[1];
			} else if ( gares.size() == 3) {
				this.argent -= loyer[2];
			} else if ( gares.size() == 4) {
				this.argent -= loyer[3];
			}
		}
		
		public void ajouterArgent(int montant) {
			assert(montant >= 0);
			this.argent += montant;
		}
		
		public void retirerArgent(int montant) {
			assert(montant >= 0);
			this.argent -= montant;
		}
		
		public void payerloyer(CaseTerrain terrain) {
			assert(this.position == terrain.getId() &&  terrain.getprop() != null && terrain.getprop().getNom() != this.nom );
			int[] loyer = terrain.getLoyer();
			if (terrain.getnbrmaison() == 0 && !Groupecomplet(terrain)) { //loyer de la prop seule
				this.argent -= loyer[0];
			}else if (terrain.getnbrmaison() == 0 && Groupecomplet(terrain)) { //loyer pour le groupe complet
				this.argent -= loyer[1];
			}else if (terrain.getnbrmaison() == 1) { //loyer pour 1 maison
				this.argent -= loyer[2];
			}else if (terrain.getnbrmaison() == 2) {//loyer pour 2 maison
				this.argent -= loyer[3];
			}else if (terrain.getnbrmaison() == 3) {//loyer pour 3 maison
				this.argent -= loyer[4];
			}else if (terrain.getnbrmaison() == 4) {//loyer pour 4 maison
				this.argent -= loyer[5];
			}else if (terrain.getnbrmaison() == 5) { // 5 maisons == 1 hôtel 
				this.argent -= loyer[6];
			}		
		}
		
		public void vendreGare(CaseGare gare) {
		    assert(gare != null && gares.contains(gare)); 
		    gares.remove(gare); 
		    this.argent += gare.getprixachat(); // vendre à la banque avec le même prix d'achat
		}
		
		/** public void vendreservice(CaseService service) {
		    assert(service != null && service.contains(service)); 
		    service.remove(service); 
		    this.argent += service.getprixachat(); // vendre à la banque avec le même prix d'achat
		}
		
		public void ajouterservice(CaseService serv) {
			assert(serv != null && this.argent > serv.getprixachat());
			if (serv.peutacheterservice()) {
				service.add(serv);
				this.argent -= serv.getprixachat();
			}else {
				System.out.println("Cette compagnie de distribution appartient à un autre joueur");
		}
		}*/
		
}
