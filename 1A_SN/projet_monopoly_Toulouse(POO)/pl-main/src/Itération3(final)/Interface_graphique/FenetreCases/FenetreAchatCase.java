package Interface_graphique.FenetreCases;

import javax.swing.*;
import java.awt.*;
import GestionMonopoly.*;
import GestionMonopoly.Cases.*;
import Interface_graphique.Utilitaires.ModernButton;

public class FenetreAchatCase extends JFrame {
    private JoueurMonopoly joueur;
    private CaseAchetable caseAchetable;

    public FenetreAchatCase(JoueurMonopoly joueur, CaseAchetable caseAchetable) {
        super("Propriété - " + caseAchetable.getNom());
        this.joueur = joueur;
        this.caseAchetable = caseAchetable;

        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setupComponents();
    }

    private void setupComponents() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JLabel label = new JLabel(
                "Voulez-vous acheter " + caseAchetable.getNom() + " pour " + caseAchetable.getValeurAchat() + "€ ?");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setHorizontalAlignment(JLabel.CENTER);

        // Utilisation de ModernButton
        JButton buyButton = new ModernButton("Acheter", new Color(40, 180, 99), Color.BLACK);
        JButton cancelButton = new ModernButton("Annuler", new Color(253, 237, 236), Color.BLACK);

        buyButton.addActionListener(e -> {
            acheter();
            dispose();
        });
        cancelButton.addActionListener(e -> dispose());

        panel.add(label);
        panel.add(buyButton);
        panel.add(cancelButton);
        add(panel);
    }

    private void acheter() {
        if (caseAchetable.estAchetable()) {
            joueur.acheter(caseAchetable);
            JOptionPane.showMessageDialog(this, "Vous avez acheté " + caseAchetable.getNom(), "Achat réussi",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Achat impossible. Fonds insuffisants ou propriété déjà achetée.",
                    "Achat échoué", JOptionPane.ERROR_MESSAGE);
        }
        dispose(); // Ferme la fenêtre après l'interaction
    }
}
