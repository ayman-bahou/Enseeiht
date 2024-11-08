package Interface_graphique;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GestionMonopoly.JoueurMonopoly;
import Interface_graphique.Utilitaires.ListeLabel;
import Interface_graphique.Utilitaires.RoundedPanel;


/** Classe qui sert à créer un panneau qui présente les informations d'un joueur */
public class Panneau_joueur extends RoundedPanel {

    final private static Color COULEUR_SOUSTITRE = Color.decode("#f0b27a");
    final private static Color COULEUR_BACKGROUND = Color.decode("#fae5d3");
    final private static Color COULEUR_ARGENT_VALEUR = Color.decode("#d5f5e3");
    final private static Color COULEUR_ARGENT_TITRE = Color.decode("#abebc6");
    
    private JLabel argent;
    private ListeLabel proprietesPanel;
    private ListeLabel servicesPanel;
    private ListeLabel garesPanel;
    
    /** Méthode pour créer le JPanel d'un joueur avec son nom, son argent et ses propriétés 
     * @param nomJoueur Nom du joueur
    */
    public Panneau_joueur(JoueurMonopoly joueur,int argent, String[] propriétés, String[] gares, String[] services) {
        // Créer un RoundedPanel pour le joueur
        super(15,COULEUR_BACKGROUND);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(6,1,6,1));
    
        // Nom du joueur
        JLabel nomJoueurLabel = new JLabel(joueur.getNom());
        nomJoueurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomJoueurLabel.setOpaque(false);
        nomJoueurLabel.setFont(VuePlateau.nomJoueurFont);

        JPanel nomJoueurPanel = new JPanel();
        nomJoueurPanel.setBackground(joueur.getPion().getCouleur());
        nomJoueurPanel.setLayout(new BoxLayout(nomJoueurPanel, BoxLayout.Y_AXIS));
        nomJoueurPanel.add(Box.createVerticalStrut(0));
        nomJoueurPanel.add(nomJoueurLabel);

        this.proprietesPanel = new ListeLabel("Propriétés",propriétés,COULEUR_SOUSTITRE);
        this.garesPanel = new ListeLabel("Gares",gares,COULEUR_SOUSTITRE);
        this.servicesPanel = new ListeLabel("Services", services, COULEUR_SOUSTITRE);
        
        //Ajout et agencement des différents composants
        this.add(nomJoueurPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(créerArgentPanel(argent));
        this.add(Box.createVerticalStrut(10));
        this.add(proprietesPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(garesPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(servicesPanel);
    }

    public Panneau_joueur(JoueurMonopoly joueur, int argent){
        this(joueur, argent, new String[0], new String[0], new String[0]);
    }
    
    /**
     * Crée le Panel qui donne l'argent du joueur
     * @param argent Somme d'argent à afficher dans le panel
     * @return Le JPanel qui présente l'argent du joueur
     */
    private JPanel créerArgentPanel(int argent) {
        // Titre "Argent"
        JLabel titreArgentLabel = new JLabel("Argent");
        titreArgentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titreArgentLabel.setFont(VuePlateau.titleFont);

        JPanel titre = new JPanel();
        titre.setBackground(COULEUR_ARGENT_TITRE);
        titre.setLayout(new BoxLayout(titre, BoxLayout.Y_AXIS));
        titre.add(Box.createVerticalStrut(0));
        titre.add(titreArgentLabel);
    
        // Montant du joueur
        this.argent = new JLabel(argent + " M$");
        this.argent.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.argent.setFont(VuePlateau.argentFont);
    
        // Création du Panel
        JPanel argentPanel = new JPanel();
        argentPanel.setBackground(COULEUR_ARGENT_VALEUR);
        argentPanel.setLayout(new BoxLayout(argentPanel, BoxLayout.Y_AXIS));
        //Ajout des éléments dans le panel
        argentPanel.add(titre); // Ajouter le JPanel contenant le titre
        argentPanel.add(Box.createVerticalStrut(4));
        argentPanel.add(this.argent);
    
        return argentPanel;
    }

    public void updateArgent(int argent) {
        this.argent.setText(argent + " M$");
    }

    public void addPropriété(String propriété){
        this.proprietesPanel.addList(propriété);
    }

    public void removePropriété(String propriété){
        this.proprietesPanel.removeList(propriété);
    }

    public void addGare(String gares){
        this.garesPanel.addList(gares);
    }

    public void removeGare(String gares){
        this.garesPanel.removeList(gares);
    }

    public void addService(String service){
        this.servicesPanel.addList(service);
    }

    public void removeService(String service){
        this.servicesPanel.removeList(service);
    }
}