
package com.mio.GUI.swing;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRender extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com =  super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        PanelAction action = new PanelAction();
            if (isSelected) {
                if (row % 2 == 0) {
                    action.setBackground(new Color(33, 103, 153));
                } else {
                    action.setBackground(new Color(29, 86, 127));
                }
            } else {
                if (row % 2 == 0) {
                    action.setBackground(new Color(20, 66, 114));
                } else {
                    action.setBackground(new Color(10, 38, 71));
                }
            }
        
        return action;
    }
    
}
