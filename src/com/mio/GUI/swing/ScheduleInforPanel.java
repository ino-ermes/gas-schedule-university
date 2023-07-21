package com.mio.GUI.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author Mio
 */
public class ScheduleInforPanel extends JPanel {

    public ScheduleInforPanel() {
        setOpaque(false);
    }

    private final int cellW = 60;
    private final int cellH = 25;
    private final int marginL = 20;
    private final int marginT = 10;
    Font f = new Font("Arial", Font.PLAIN, 13);

    private float fitness = 0f;
    private int classConflict = 0;
    private int constraint = 0;
    private int[] constraints = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

    @Override
    protected void paintComponent(Graphics g) {
        g.setFont(f);
        g.setColor(Color.WHITE);

        int fontHeight = g.getFontMetrics().getHeight();
        fontHeight += fontHeight / 2;

        int y = marginT + fontHeight;
        int x = marginL;

        g.drawString("Độ tương thích: " + round(fitness, 4), x, y);

        y += fontHeight;
        g.drawString("Số môn vi phạm: " + classConflict, x, y);

        y += fontHeight;
        g.drawString("Tổng ràng buộc cứng: " + constraint, x, y);

        y += fontHeight;
        g.drawString("Chi tiết", x, y);

        y += fontHeight / 2;

        for (int i = 0; i != 10; ++i) {
            g.drawLine(marginL, y + cellH * i, marginL + cellW * 2, y + cellH * i);
        }
        for (int i = 0; i != 3; ++i) {
            g.drawLine(marginL + cellW * i, y, marginL + cellW * i, y + cellH * 9);
        }

        for (int i = 0; i != 9; ++i) {
            for (int j = 0; j != 2; ++j) {
                Rectangle r = new Rectangle(marginL + j * cellW, y + i * cellH, cellW, cellH);
                if (i == 0) {
                    if (j == 0) {
                        drawCenteredString(g, "Loại", r, f);
                    } else {
                        drawCenteredString(g, "SL", r, f);
                    }
                } else if (j == 0) {
                    drawCenteredString(g, "" + i, r, f);
                } else {
                    drawCenteredString(g, "" + constraints[i - 1], r, f);
                }
            }
        }

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        super.paintComponent(g);
    }

    public void setInfor(float fitness, int classConflict, int[] constraints) {
        this.fitness = fitness;
        this.classConflict = classConflict;
        this.constraints = constraints;
        this.constraint = 0;
        for (int i = 0; i != 8; ++i) {
            this.constraint += constraints[i];
        }
        repaint();
    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
