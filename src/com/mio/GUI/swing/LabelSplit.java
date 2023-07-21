
package com.mio.GUI.swing;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;

public class LabelSplit extends JLabel {
    
    private boolean gradiet;

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        if(isGradiet()) {
            g2.setPaint(new GradientPaint(0, 0, new Color(255, 255, 255), getWidth(), 0, new Color(255, 255, 255, 0)));
        } else {
            g2.setPaint(new Color(255, 255, 255));
        }
        g2.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g2.dispose();
        super.paint(g);
    }

    public boolean isGradiet() {
        return gradiet;
    }

    public void setGradiet(boolean gradiet) {
        this.gradiet = gradiet;
    }
}