
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
public class GAInforPanel extends JPanel{

    public GAInforPanel() {
        setOpaque(false);
    }

    private int cellW = 60;
    private int cellH = 25;
    private int marginL = 20;
    private int marginT = 10;
    Font f = new Font("Arial", Font.PLAIN, 13);
    
    
    private boolean running = false;
    private float fitness = 0f;
    private float averageFitness = 0f;
    private int generationCount = 0;
    private int constraint = 0;
    private int[] constraints = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    private int crossoverProb = 0;
    private int mutationProb = 0;
    private boolean autoReset = false;
    private float resetPoint = 0;
    private int resetCount = 0;
    
    @Override
    protected void paintComponent(Graphics g) {
        g.setFont(f);
        g.setColor(Color.WHITE);
        
        int fontHeight = g.getFontMetrics().getHeight();
        fontHeight += fontHeight / 2;
        
        int y = marginT + fontHeight;
        int x = marginL;
        
        g.drawString("Trạng thái: " + (running ? "Đang chạy" : "Tạm dừng"), x, y);
        
        y += fontHeight;
        g.drawString("Độ tương thích: " + round(fitness, 4), x, y);
        
        y += fontHeight;
        g.drawString("Độ tương thích TB: " + round(averageFitness, 4), x, y);
        
        y += fontHeight;
        g.drawString("Thế hệ thứ: " + generationCount, x, y);
        
        y += fontHeight;
        g.drawString("Tổng ràng buộc cứng: " + constraint, x, y);
        
        y += fontHeight / 2;
        
        for(int i = 0; i != 10; ++i) {
            g.drawLine(marginL, y + cellH * i, marginL + cellW * 2, y + cellH * i);
        }
        for(int i = 0; i != 3; ++i) {
            g.drawLine(marginL + cellW * i, y, marginL + cellW * i, y + cellH * 9);
        }
        
        for(int i = 0; i != 9; ++i) {
            for(int j = 0; j != 2; ++j) {
                Rectangle r = new Rectangle(marginL + j * cellW, y + i * cellH, cellW, cellH);
                if(i == 0) {
                    if(j == 0) {
                        drawCenteredString(g, "Loại", r, f);
                    } else {
                        drawCenteredString(g, "SL", r, f);
                    }
                } else if(j == 0) {
                    drawCenteredString(g, "" + i, r, f);
                } else {
                    drawCenteredString(g, "" + constraints[i - 1], r, f);
                }
            }
        }
        
        y += cellH * 9;
        
        y += fontHeight;
        g.drawString("Tỉ lệ lai tạo: " + crossoverProb, x, y);
        
        y += fontHeight;
        g.drawString("Tỉ lệ đột biến " + mutationProb, x, y);
        
        y += fontHeight;
        g.drawString("Tự động khởi tạo: " + (autoReset ? "Bật" : "Tắt"), x, y);
        
        y += fontHeight;
        g.drawString("Điểm khởi tạo: " + resetPoint, x, y);
        
        y += fontHeight;
        g.drawString("Số lần khởi tạo: " + resetCount, x, y);
        
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        super.paintComponent(g);
    }

    public void setInfor(boolean running, float fitness, float averageFitness, int generationCount, int[] constraints, int resetCount) {
        this.running = running;
        this.fitness = fitness;
        this.averageFitness = averageFitness;
        this.generationCount = generationCount;
        this.constraints = constraints;
        this.constraint = 0;
        for(int i = 0; i != 8; ++i) {
            this.constraint += constraints[i];
        }
        this.resetCount = resetCount;
        repaint();
    }
    public void setConfig(int crossoverProb, int mutationProb, boolean autoReset, float resetPoint) {
        this.crossoverProb = crossoverProb;
        this.mutationProb = mutationProb;
        this.autoReset = autoReset;
        this.resetPoint = resetPoint;
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
