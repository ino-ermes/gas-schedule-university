
package com.mio.GUI.swing;

import com.mio.GUI.swing.scroll.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTable extends JTable {

    private MyTableHeader header;
    private MyTableCell cell;

    public MyTable() {
        header = new MyTableHeader();
        cell = new MyTableCell();
        getTableHeader().setDefaultRenderer(header);
        getTableHeader().setPreferredSize(new Dimension(0, 35));
        setDefaultRenderer(Object.class, cell);
        setRowHeight(30);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void setColumnAlignment(int column, int align) {
        header.setAlignment(column, align);
    }

    public void setCellAlignment(int column, int align) {
        cell.setAlignment(column, align);
    }

    public void setColumnWidth(int column, int width) {
        getColumnModel().getColumn(column).setPreferredWidth(width);
        getColumnModel().getColumn(column).setMinWidth(width);
        getColumnModel().getColumn(column).setMaxWidth(width);
        getColumnModel().getColumn(column).setMinWidth(10);
        getColumnModel().getColumn(column).setMaxWidth(10000);
    }

    public void fixTable(JScrollPane scroll) {
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, panel);
        scroll.getViewport().setBackground(new Color(44, 116, 179));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(10, 38, 71), 2));
    }

    private class MyTableHeader extends DefaultTableCellRenderer {

        private Map<Integer, Integer> alignment = new HashMap<>();

        public void setAlignment(int column, int align) {
            alignment.put(column, align);
        }

        
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            com.setBackground(new Color(10, 38, 71));
            com.setForeground(Color.WHITE);
            com.setFont(com.getFont().deriveFont(Font.BOLD, 12));
            if (alignment.containsKey(column)) {
                setHorizontalAlignment(alignment.get(column));
            } else {
                setHorizontalAlignment(JLabel.CENTER);
            }
            return com;
        }
    }

    private class MyTableCell extends DefaultTableCellRenderer {

        private Map<Integer, Integer> alignment = new HashMap<>();

        public void setAlignment(int column, int align) {
            alignment.put(column, align);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isCellSelected(row, column)) {
                if (row % 2 == 0) {
                    com.setBackground(new Color(33, 103, 153));
                } else {
                    com.setBackground(new Color(29, 86, 127));
                }
            } else {
                if (row % 2 == 0) {
                    com.setBackground(new Color(20, 66, 114));
                } else {
                    com.setBackground(new Color(10, 38, 71));
                }
            }
            com.setForeground(Color.WHITE);
            setBorder(new EmptyBorder(0, 5, 0, 5));
            if (alignment.containsKey(column)) {
                setHorizontalAlignment(alignment.get(column));
            } else {
                setHorizontalAlignment(JLabel.CENTER);
            }
            return com;
        }
    }
}
