
package com.mio.GUI.dialog;

import com.mio.BLL.algorithm.Configuration;
import com.mio.BLL.algorithm.GACourseClass;
import com.mio.BLL.algorithm.GARoom;
import com.mio.BLL.algorithm.GASchedule;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;

/**
 *
 * @author Mio
 */
public class SchedulePanel extends JPanel{

    private final int DAYS_NUM = GASchedule.DAYS_NUM;
    private final int DAY_HOURS = GASchedule.DAY_HOURS;
    
    private final int ROOM_CELL_WIDTH = 85;
    private final int ROOM_CELL_HEIGHT = 51;
    
    private final int ROOM_MARGIN_WIDTH = 50;
    private final int ROOM_MARGIN_HEIGHT = 50;
    
    private final int  ROOM_COLUMN_NUMBER = DAYS_NUM + 1;
    private final int  ROOM_ROW_NUMBER = DAY_HOURS + 1;
    
    private final int ROOM_TABLE_WIDTH = ROOM_CELL_WIDTH * ROOM_COLUMN_NUMBER + ROOM_MARGIN_WIDTH;
    private final int ROOM_TABLE_HEIGHT = ROOM_CELL_HEIGHT * ROOM_ROW_NUMBER + ROOM_MARGIN_HEIGHT;
    
    private Rectangle toRect(int room, int day, int hour, int duration) {
        return new Rectangle(ROOM_MARGIN_WIDTH + day * ROOM_CELL_WIDTH, ROOM_MARGIN_HEIGHT + hour * ROOM_CELL_HEIGHT + ROOM_TABLE_HEIGHT * room, ROOM_CELL_WIDTH, ROOM_CELL_HEIGHT * duration);
    }
    private final String[] days;
    private final String[] hours;
    private final String[] criter;
    
    private final Font headerFont;
    private final Font roomInforFont;
    private final Font classInforFont;
    private final Font criterInforFont;
    private final Color criterFalse;
    private final Color criterTrue;
    private final Color courseClass;
    private final Color roomClash;
    private final Color roomColor;
    private final Color roomHeader;
    
    private GASchedule schedule;
    
    public void setSchedule(GASchedule schedule) {
        this.schedule = schedule.makeCopy();
        repaint();
    }
    
    private static SchedulePanel instance;
    
    public static SchedulePanel getInstance() {
        return instance;
    }
    
    private void drawRoom(Graphics g, int index) {
        
        g.setColor(Color.WHITE);
        for(int i = 0; i != days.length; ++i) {
            Rectangle r = toRect(index, i + 1, 0, 1);
            g.setColor(roomColor);
            g.fillRect(r.x, r.y, r.width, r.height);
            g.setColor(Color.BLACK);
            g.drawRect(r.x, r.y, r.width, r.height);
            g.setColor(Color.WHITE);
            drawCenteredString(g, days[i], r, headerFont);
        }
        for(int i = 0; i != hours.length; ++i) {
            Rectangle r = toRect(index, 0, i + 1, 1);
            g.setColor(roomColor);
            g.fillRect(r.x, r.y, r.width, r.height);
            g.setColor(Color.BLACK);
            g.drawRect(r.x, r.y, r.width, r.height);
            g.setColor(Color.WHITE);
            drawCenteredString(g, hours[i], r, headerFont);
        }
        GARoom rm = Configuration.getInstance().getRooms().get(index);
        //String infor = "Phòng " + rm.getName() + "\nSức chứa: " + rm.getSize() + "\n" + (rm.isLab() ? "Phòng CLC" : "Phòng thường");
        
        Rectangle r = toRect(index, 0, 0, 1);
        g.setColor(roomHeader);
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        g.drawRect(r.x, r.y, r.width, r.height);
        int h3 = ROOM_CELL_HEIGHT / 3;
        g.drawLine(r.x, r.y + h3, r.x + r.width, r.y + h3);
        g.drawLine(r.x, r.y + h3 * 2, r.x + r.width, r.y + h3 * 2);
        g.setColor(Color.WHITE);
        g.setFont(roomInforFont);
        g.drawString("Phòng " + rm.getName(), r.x + 3, r.y + h3 - 3);
        g.drawString("Sức chứa: " + rm.getSize(), r.x + 3, r.y + h3 * 2 - 3);
        g.drawString(rm.isLab() ? "Phòng máy" : "Phòng thường", r.x + 3, r.y + r.height - 3);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(ROOM_TABLE_WIDTH + ROOM_MARGIN_WIDTH, ROOM_TABLE_HEIGHT * Configuration.getInstance().getRooms().size());
    }
    
    
    
    public SchedulePanel() {
        this.colors = new Color[]{Color.WHITE, new Color(134, 163, 184), new Color(232, 210, 166), new Color(244, 132, 132)};
        this.headerFont = new Font("Arial", Font.BOLD, 16);
        this.roomInforFont = new Font("Arial", Font.PLAIN, 10);
        this.classInforFont = new Font("Arial", Font.PLAIN, 10);
        this.criterInforFont = new Font("Arial", Font.BOLD, 10);
        this.days = new String[]{"Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy"};
        this.hours = new String[]{"Tiết 1", "Tiết 2", "Tiết 3", "Tiết 4", "Tiết 5", "Tiết 6", "Tiết 7", "Tiết 8", "Tiết 9", "Tiết 10"};
        this.criter = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
        this.criterFalse =new Color(206, 0, 0);
        this.criterTrue = new Color(49, 147, 120); 
        this.courseClass = new Color(39, 55, 77);
        this.roomClash = new Color(244, 80, 80, 100);
        this.roomColor = new Color(20, 66, 114);
        this.roomHeader = new Color(10, 38, 71);
    }
    
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        if(schedule == null) return;
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int nr = Configuration.getInstance().getRooms().size();
        for(int i = 0; i != nr; ++i) {
            drawRoom(g, i);
        }
        
        int daySize = DAY_HOURS * Configuration.getInstance().getRooms().size();
        
        int ci = 0;
        for(Map.Entry<GACourseClass, Integer> entry:  schedule.getClasses().entrySet()) {
            int p = entry.getValue();
            int day = p / daySize;
            int time = p % daySize;
            int room = time / DAY_HOURS;
            time = time % DAY_HOURS;
            Rectangle r = toRect(room, day + 1, time + 1, entry.getKey().getDuration());
            drawSchedule(g, r, entry.getKey());
            Color criterColor;
            g.setFont(criterInforFont);
            r.y +=  g.getFontMetrics().getHeight();
            r.x += 3;
            for(int i = 0; i != 8; ++i) {
                criterColor =  schedule.getCriteria()[ci + i] ? criterTrue : criterFalse;
                g.setColor(criterColor);
                g.drawString(criter[i], r.x + 8 * i, r.y);
            }
            ci += 8;
        }
        g.setColor(roomClash);
        ArrayList<ArrayList<GACourseClass>> slots = schedule.getSlots();
        int i = 0;
        for(int day = 0; day != DAYS_NUM; ++day) {
            for(int room = 0; room != nr; ++room) {
                for(int time = 0; time != DAY_HOURS; ++time) {
                    if(slots.get(i).size() > 1) {
                        Rectangle r = toRect(room, day + 1, time + 1, 1);
                        g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
                    }
                    ++i;
                }
            }
        }
    }
    
    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }
    
    private final Color[] colors;
    
    private void drawSchedule(Graphics g, Rectangle r, GACourseClass cc) {
        FontMetrics metrics = g.getFontMetrics(classInforFont);
        
        List<String> lt = new ArrayList<>();
        List<Integer> lc = new ArrayList<>();
        String groups = cc.getGroups().get(0).getName();
        for(int i = 1; i < cc.getGroups().size(); ++i) {
            groups += ", " + cc.getGroups().get(i).getName();
        }
        lc.add(splitLineFit(Integer.toString(cc.getId()), metrics, lt));
        lc.add(splitLineFit(cc.getCourse().getName(), metrics, lt));
        lc.add(splitLineFit(cc.getTeacher().getName(), metrics, lt));
        lc.add(splitLineFit(groups, metrics, lt));
        for(int j = 1; j != lc.size(); ++j) {
            lc.set(j, lc.get(j - 1) + lc.get(j));
        }
        
        int lineCount = lt.size();
        
        g.setColor(courseClass);
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        g.drawRect(r.x, r.y, r.width, r.height);
        
        int y = r.y + (r.height  - metrics.getHeight() * lineCount) / 2 + metrics.getAscent();
        
        g.setFont(classInforFont);
        g.setColor(colors[0]);
        int swapColor = 0;
        for(int j = 0; j != lineCount; ++j) {
            if(j == lc.get(swapColor)) {
                g.setColor(colors[++swapColor]);
            }
            int x = r.x + (r.width - metrics.stringWidth(lt.get(j))) / 2;
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
            while(fm.stringWidth(tmp) >= ROOM_CELL_WIDTH) {
                while(r != l && str.charAt(r - 1) != ' ') {
                    --r;
                }
                if(r == l) {
                    // ko thể xuống dòng, chấp nhận lấn ô
                    r = str.length();
                    break;
                }
                tmp = str.substring(l, --r);
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

