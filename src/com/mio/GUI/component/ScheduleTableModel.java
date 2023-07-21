
package com.mio.GUI.component;

import java.awt.Color;

public interface ScheduleTableModel {
    public int getScheduleCount();
    public String getScheduleText(int i, int j);
    public int getCountPerSchedule();
    public int getDay(int i);
    public int getTime(int i);
    public int getDuration(int i);
    public void setAt(int i, Object o);
    public Color getColor(int i);
    public boolean getVisible(int i);
}
