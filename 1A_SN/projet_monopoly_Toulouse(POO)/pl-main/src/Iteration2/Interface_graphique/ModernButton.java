package Interface_graphique;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
 
public class ModernButton extends JButton {
 
    public ModernButton(String txt, Color backColor, Color frontColor) {
        super(txt);
        setForeground(frontColor);
        setBackground(backColor);
        setFocusPainted(false);
        setFont(new Font("Verdana", Font.BOLD, 14));
    }
}