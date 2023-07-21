
package com.mio.GUI.swing;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

public class Navigator extends JLabel{

    public boolean isIsMostRight() {
        return isMostRight;
    }

    public void setIsMostRight(boolean isMostRight) {
        this.isMostRight = isMostRight;
    }

    private boolean isMostRight;
    
    public Navigator() {
        setOpaque(true);
        isMostRight = false;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if(isMostRight) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            g.fillRect(0, 0, 20, getHeight());
            g.fillRect(0, 20, getWidth(), getHeight() - 20);
            setBackground(new Color(0, 0, 0, 0));
        }
        super.paintComponent(g);
    }
}