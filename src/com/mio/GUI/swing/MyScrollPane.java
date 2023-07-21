/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.GUI.swing;

import com.mio.GUI.swing.scroll.ScrollBarCustom;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Mio
 */
public class MyScrollPane extends JScrollPane{
    public MyScrollPane(JPanel panel) {
        super(panel);
        setCustomScrollBar();
        getVerticalScrollBar().setUnitIncrement(16);
    }
    private void setCustomScrollBar() {
        this.setViewportBorder(null);
        this.getViewport().setOpaque(false);
        this.setBorder(null);
        this.setVerticalScrollBar(new ScrollBarCustom());
        this.setHorizontalScrollBar(new ScrollBarCustom());
    }
}
