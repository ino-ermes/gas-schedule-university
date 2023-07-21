
package com.mio.GUI.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import com.mio.GUI.swing.scroll.ScrollBarCustom;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

public class ComboBox<E> extends JComboBox<E> {

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    private Color lineColor = new Color(20, 66, 114);
    private boolean mouseOver;

    public ComboBox() {
        setOpaque(false);
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.PLAIN, 13));
        setBackground(new Color(0,0,0,0));
        //setBorder(new EmptyBorder(15, 3, 5, 3));
        setUI(new ComboUI(this));
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int i, boolean bln, boolean bln1) {
                jlist.setOpaque(false);
                Component com = super.getListCellRendererComponent(jlist, o, i, bln, bln1);
                //setBorder(new EmptyBorder(5, 5, 5, 5));
                if (bln) {
                    com.setBackground(new Color(20, 66, 114));
                }
                return com;
            }
        });
        //ComboPopup popup = (ComboPopup) this.getUI().getAccessibleChild(this, 0);
        //((JComponent) popup).setPreferredSize(new Dimension(300, 300));
        //((JComponent) popup).setLayout(new GridLayout(1, 1));
    }

    private class ComboUI extends BasicComboBoxUI {
        
        private ComboBox combo;

        public ComboUI(ComboBox combo) {
            this.combo = combo;
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

            addPopupMenuListener(new PopupMenuListener() {
                @Override
                public void popupMenuWillBecomeVisible(PopupMenuEvent pme) {
                    arrowButton.setBackground(Color.WHITE);
                }

                @Override
                public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
                    arrowButton.setBackground(new Color(20, 66, 114));
                }

                @Override
                public void popupMenuCanceled(PopupMenuEvent pme) {
                    arrowButton.setBackground(new Color(20, 66, 114));
                }
            });
        }

        @Override
        public void paintCurrentValueBackground(Graphics grphcs, Rectangle rctngl, boolean bln) {
            
        }

        @Override
        protected JButton createArrowButton() {
            return new ArrowButton();
        }

        @Override
        protected ComboPopup createPopup() {
            BasicComboPopup pop = new BasicComboPopup(comboBox) {
                @Override
                protected JScrollPane createScroller() {
                    list.setFixedCellHeight(30);
                    JScrollPane scroll = new JScrollPane(list);
                    scroll.setBackground(new Color(32, 82, 149));
                    scroll.setVerticalScrollBar(new ScrollBarCustom());
                    return scroll;
                }
            };
            pop.setBorder(new LineBorder(new Color(200, 200, 200), 1));
            return pop;
        }

        @Override
        public void paint(Graphics grphcs, JComponent jc) {
            super.paint(grphcs, jc);
            Graphics2D g2 = (Graphics2D) grphcs;
            if (mouseOver) {
                g2.setColor(lineColor);
            } else {
                g2.setColor(Color.WHITE);
            }
            g2.fillRect(2, getHeight() - 1, getWidth() - 4, 1);
            g2.dispose();
        }

        private class ArrowButton extends JButton {

            public ArrowButton() {
                setContentAreaFilled(false);
                setBorder(new EmptyBorder(5, 5, 10, 5));
                setBackground(new Color(20, 66, 114));
            }

            @Override
            public void paint(Graphics grphcs) {
                super.paint(grphcs);
                Graphics2D g2 = (Graphics2D) grphcs;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                int size = 10;
                int x = (width - size) / 2;
                int y = (height - size) / 2 + 5;
                int px[] = {x, x + size, x + size / 2};
                int py[] = {y, y, y + size};
                g2.setColor(getBackground());
                g2.fillPolygon(px, py, px.length);
                g2.dispose();
            }
        }
    }
}
