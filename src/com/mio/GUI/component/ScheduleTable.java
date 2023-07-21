
package com.mio.GUI.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class ScheduleTable extends JPanel{

    private float cellWidth, cellHeight;
    
    private final String[] headerName;
    
    private final Font headerFont;
    
    private final Color scheduleColor;
    
    private Font scheduleFont;
    
    private final Color headerColor;
    
    private final Color gridColor;

    private ScheduleTableModel model;
    
    public ScheduleTable() {
        this.headerName = new String[] {"Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy"};
        this.headerFont = new Font("Arial", Font.BOLD, 13);
        this.scheduleFont = new Font("Arial", Font.PLAIN, 13);
        this.headerColor = new Color(32, 82, 149);
        this.scheduleColor = new Color(44, 116, 179);
        this.gridColor = new Color(10, 38, 71);
        this.setBackground(Color.WHITE);
        model = null;
    }
    
    public void setScheduleFont(Font f) {
        this.scheduleFont = f;
        repaint();
    }
    
    public void setAt(int i, Object o) {
        if(model != null) {
            model.setAt(i, o);
            repaint();
        }
    }
    
    public Point getCell(int x, int y) {
        return new Point((int)(y / cellHeight), (int)(x / cellWidth));
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(1));
        // tính toán kích thước cho 1 ô
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        cellWidth = (float)width / 7.0f;
        cellHeight = (float)height / 11.0f;

        // refresh
        g2.setColor(getBackground());
        g2.fillRect(0, 0, width, height);
        g2.setColor(headerColor);
        g2.fillRect(0, 0, (int)cellWidth, height);
        g2.fillRect(0, 0, width, (int)cellHeight);

        
        // vẽ header
        for(int i = 1; i != 7; ++i) {
            drawTextHeader(g2, headerName[i - 1], 0, i);
        }
        
        for(int i = 1; i != 11; ++i) {
            drawTextHeader(g2, "Tiết " + i, i, 0);
        }
        // vẽ grid
        g2.setColor(gridColor);
        for(int i = 0; i != 8; ++i) {
            g2.drawLine((int)(i * cellWidth), 0, (int)(i * cellWidth), height);
        }
        for(int i = 0; i != 12; ++i) {
            g2.drawLine(0, (int)(i * cellHeight), width, (int)(i * cellHeight));
        }
        
        if(model != null) {
            for(int i = 0; i != model.getScheduleCount(); ++i) {
                drawSchedule(g, i);
            }
        }
        

    }
    public void setModel(ScheduleTableModel model) {
        this.model = model;
    }
    private void drawTextHeader(Graphics g, String str, int row, int col) {
        g.setColor(Color.WHITE);
        FontMetrics metrics = g.getFontMetrics(headerFont);
        int x = (int)(col * cellWidth + (cellWidth - metrics.stringWidth(str)) / 2f);
        int y = (int)(row * cellHeight + (cellHeight - metrics.getHeight()) / 2f + metrics.getAscent());
        g.setFont(headerFont);
        g.drawString(str, x, y);
    }
    private void drawSchedule(Graphics g, int i) {
        
        if(!model.getVisible(i))
            return;
        FontMetrics metrics = g.getFontMetrics(scheduleFont);
        
        int col = model.getDay(i) + 1;
        int row = model.getTime(i) + 1;
        
        List<String> lt = new ArrayList<>();
        List<Integer> lc = new ArrayList<>();
        for(int j = 0; j != model.getCountPerSchedule(); ++j) {
            lc.add(splitLineFit(model.getScheduleText(i, j), metrics, lt));
        }
        for(int j = 1; j != model.getCountPerSchedule(); ++j) {
            lc.set(j, lc.get(j - 1) + lc.get(j));
        }
        
        int lineCount = lt.size();
        int duration = model.getDuration(i);
        
        g.setColor(scheduleColor);
        g.fillRect((int)(col * cellWidth), (int)(row * cellHeight), (int)(cellWidth + 1), (int)(cellHeight * duration + 1));
        g.setColor(gridColor);
        g.drawRect((int)(col * cellWidth), (int)(row * cellHeight), (int)(cellWidth + 1), (int)(cellHeight * duration + 1));
        
        int y = (int)(row * cellHeight + (cellHeight * duration  - metrics.getHeight() * lineCount) / 2f + metrics.getAscent());
        
        g.setFont(scheduleFont);
        g.setColor(model.getColor(0));
        int swapColor = 0;
        for(int j = 0; j != lineCount; ++j) {
            if(j == lc.get(swapColor)) {
                g.setColor(model.getColor(++swapColor));
            }
            int x = (int)(col * cellWidth + (cellWidth - metrics.stringWidth(lt.get(j))) / 2f);
            g.drawString(lt.get(j), x, y);
            y += metrics.getHeight();
        }
    }
    
    // trả về số dòng đc chia, add vào lt
    private int splitLineFit(String str, FontMetrics fm, List<String> lt) {
        int l = 0, r = str.length();
        String tmp = str.substring(0, r);
        int i = 0;
        while(true) {
            while(fm.stringWidth(tmp) >= cellWidth) {
                while(r != l && str.charAt(r - 1) != ' ') {
                    --r;
                }
                if(r == l) {
                    // ko thể xuống dòng, chấp nhận lấn ô
                    r = str.length();
                    break;
                }
                tmp = tmp.substring(l, --r);
            }
            l = r + 1;
            r = str.length();
            lt.add(tmp);
            ++i;
            if(l > r) {
                break;
            }
            tmp = str.substring(l, r);
        }
        return i;
    }
}
