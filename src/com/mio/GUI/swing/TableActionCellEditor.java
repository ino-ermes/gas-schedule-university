
package com.mio.GUI.swing;

import com.mio.GUI.event.TableActionEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor{

    private TableActionEvent event;
    
    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelAction action = new PanelAction();
        if (row % 2 == 0) {
            action.setBackground(new Color(33, 103, 153));
        } else {
            action.setBackground(new Color(29, 86, 127));
        }
        action.initEvent(event, row);
        return action;
    }    
}
