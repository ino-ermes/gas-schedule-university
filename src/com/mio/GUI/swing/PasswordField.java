
package com.mio.GUI.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class PasswordField extends JPasswordField {

    private boolean mouseOver;
    private final Color lineColor = new Color(20, 66, 114);
    
    public PasswordField() {
        setOpaque(false);
        setFont(new Font("Arial", Font.PLAIN, 13));
        setBorder(new EmptyBorder(9, 1, 9, 1));
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.WHITE);
        setSelectionColor(new Color(200, 200, 200, 100));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                repaint();
            }
    });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        if (mouseOver) {
            g2.setColor(lineColor);
        } else {
            g2.setColor(Color.WHITE);
        }
        g2.fillRect(2, getHeight() - 1, getWidth() - 4, 1);
        g2.dispose();
    }
}
