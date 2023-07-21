
package com.mio.GUI.swing.scroll;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

    public ScrollBarCustom() {
        setUnitIncrement(16);
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setForeground(new Color(10, 38, 71));
        setOpaque(false);
    }
}