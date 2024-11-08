package Interface_graphique.Utilitaires;

import java.awt.*;
import javax.swing.*;

public class CerclePanel extends JLabel {

    public CerclePanel() {
        super();
    }

    public CerclePanel(Icon image) {
        super(image);
    }

    public CerclePanel(String text) {
        super(text);
    }

    public CerclePanel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Définir la couleur de fond
        g2d.setColor(getBackground());

        // Dessiner le cercle
        int diameter = Math.min(getWidth(), getHeight());
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;
        g2d.fillOval(x, y, diameter, diameter);

        g2d.dispose();
    }
}