package Interface_graphique;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


/** Classe qui sert à créer un panneau qui présente les informations d'un joueur */
public class Panneau_joueur extends RoundedPanel {
    
    private JLabel argent;
    private ListeLabel proprietesPanel;
    private ListeLabel compagniesPanel;
    private ListeLabel garesPanel;
    
    /** Méthode pour créer le JPanel d'un joueur avec son nom, son argent et ses propriétés 
     * @param nomJoueur Nom du joueur
    */
    public Panneau_joueur(String nomJoueur,int argent, String[] propriétés, String[] gares) {
        // Créer un RoundedPanel pour le joueur
        super(15,Color.decode("#fae5d3"));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(6,1,6,1));
    
        // Nom du joueur
        JLabel nomJoueurLabel = new JLabel(nomJoueur);
        nomJoueurLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomJoueurLabel.setOpaque(false);
        nomJoueurLabel.setFont(VuePlateau.nomJoueurFont);

        JPanel nomJoueurPanel = new JPanel();
        nomJoueurPanel.setBackground(Color.decode("#f0b27a"));
        nomJoueurPanel.setLayout(new BoxLayout(nomJoueurPanel, BoxLayout.Y_AXIS));
        nomJoueurPanel.add(Box.createVerticalStrut(0));
        nomJoueurPanel.add(nomJoueurLabel);

        this.proprietesPanel = new ListeLabel("Propriétés",propriétés,Color.decode("#f0b27a"));
        this.garesPanel = new ListeLabel("Gares",gares,Color.decode("#f0b27a"));
        
        //Ajout et agencement des différents composants
        this.add(nomJoueurPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(créerArgentPanel(argent));
        this.add(Box.createVerticalStrut(10));
        this.add(proprietesPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(garesPanel);
    }

    public Panneau_joueur(String nomJoueur, int argent){
        this(nomJoueur, argent, new String[0], new String[0]);
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
        titre.setBackground(Color.decode("#abebc6"));
        titre.setLayout(new BoxLayout(titre, BoxLayout.Y_AXIS));
        titre.add(Box.createVerticalStrut(0));
        titre.add(titreArgentLabel);
    
        // Montant du joueur
        this.argent = new JLabel(argent + " M$");
        this.argent.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.argent.setFont(VuePlateau.argentFont);
    
        // Création du Panel
        JPanel argentPanel = new JPanel();
        argentPanel.setBackground(Color.decode("#d5f5e3"));
        argentPanel.setLayout(new BoxLayout(argentPanel, BoxLayout.Y_AXIS));
        //Ajout des éléments dans le panel
        argentPanel.add(titre); // Ajouter le JPanel contenant le titre
        argentPanel.add(Box.createVerticalStrut(4));
        argentPanel.add(this.argent);
    
        return argentPanel;
    }

    public void updateArgent(int argent) {
        this.argent.setText("" + argent);
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
}