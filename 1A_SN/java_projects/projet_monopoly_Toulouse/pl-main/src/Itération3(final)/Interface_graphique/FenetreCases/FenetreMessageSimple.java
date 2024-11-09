package Interface_graphique.FenetreCases;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FenetreMessageSimple extends JFrame {

    private final int TAILLE_BORDURE = 50;

    public FenetreMessageSimple(String texte, Color backColor, Color fontColor) {
        // Configurer la fenêtre
        this.setTitle("Message");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600,300);

        // Créer le panneau principal avec un BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(backColor);

        // Créer le JTextArea avec le message
        JTextArea message = new JTextArea(texte);
        message.setFont(new Font("Arial", Font.BOLD, 18));
        message.setBorder(new EmptyBorder(TAILLE_BORDURE, TAILLE_BORDURE, TAILLE_BORDURE, TAILLE_BORDURE));
        message.setForeground(fontColor);
        message.setBackground(backColor);
        message.setOpaque(true);
        message.setEditable(false);
        message.setWrapStyleWord(true);

        // Centrer le JTextArea horizontalement et verticalement
        JPanel messagePanel = new JPanel(new GridBagLayout());
        messagePanel.setBackground(backColor);
        messagePanel.add(message);

        // Ajouter le JTextArea au panneau principal au centre
        panel.add(messagePanel, BorderLayout.CENTER);

        // Ajouter le panneau à la fenêtre
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);  // Centrer la fenêtre sur l'écran
    }
}
