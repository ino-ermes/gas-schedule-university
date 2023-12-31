/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mio.GUI.form;

import com.mio.BLL.DepartmentBLL;
import com.mio.BLL.EduProgramBLL;
import com.mio.BLL.StudentGroupBLL;
import com.mio.DTO.Department;
import com.mio.DTO.SO;
import com.mio.DTO.StudentGroup;
import com.mio.GUI.event.TableActionEvent;
import com.mio.GUI.main.Main;
import com.mio.GUI.swing.TableActionCellEditor;
import com.mio.GUI.swing.TableActionCellRender;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mio
 */
public class AdminFormStudentGroup extends javax.swing.JPanel {

    /**
     * Creates new form AdminFormStudentGroup
     */
    public AdminFormStudentGroup() {
        initComponents();
        
        cbxDep.setModel(new DefaultComboBoxModel(DepartmentBLL.getInstance().getDepartmentList().toArray()));
        
        table.fixTable(scroll);
        table.setModel(new MyModel());
        
        
        AdminFormStudentGroup me = this;
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                Main.getInstance().showForm(new AdminFormStudent(me, list.get(row)));
            }
        };
        table.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                    tfName.setText(list.get(table.getSelectedRow()).getName());
                    cbxEduProgram.setSelectedItem(new SO(list.get(table.getSelectedRow()).getEduProgramId()));
                }
            }
        });
        
        menuSplit1.setText("Khoa");
        menuSplit2.setText("Nhóm lớp");
    }

    private class MyModel extends AbstractTableModel {
        private List<StudentGroup> list;
        private HashMap<Integer, SO> eduList;
        MyModel() {
            this.list = new ArrayList<>();
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0 -> {
                    return "Mã số";
                }
                case 1 -> {
                    return "Tên nhóm";
                }
                case 2 -> {
                    return "CTĐT";
                }
                case 3 -> {
                    return "Số lượng SV";
                }
                default -> {
                    return "Sinh viên";
                }
            }
        }
        
        
        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            StudentGroup s = list.get(rowIndex);
            switch(columnIndex) {
                case 0 -> {
                    return s.getId();
                }
                case 1 -> {
                    return s.getName();
                }
                case 2 -> {
                    return eduList.get(s.getEduProgramId()).getName();
                }
                case 3 -> {
                    return s.getSize();
                }
                default -> {
                    return null;
                }
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 4;
        }
        
        public void setList(List<StudentGroup> list, List<SO> eduList) {
            this.list = list;
            this.eduList = new HashMap<>();
            for(SO s: eduList) {
                this.eduList.put(s.getId(), s);
            }
            
            this.fireTableDataChanged();
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuSplit1 = new com.mio.GUI.swing.MenuSplit();
        cbxDep = new com.mio.GUI.swing.ComboBox();
        btnShow = new com.mio.GUI.swing.Button();
        menuSplit2 = new com.mio.GUI.swing.MenuSplit();
        label1 = new com.mio.GUI.swing.Label();
        tfName = new com.mio.GUI.swing.TextField();
        label2 = new com.mio.GUI.swing.Label();
        cbxEduProgram = new com.mio.GUI.swing.ComboBox();
        btnAdd = new com.mio.GUI.swing.Button();
        btnEdit = new com.mio.GUI.swing.Button();
        btnDel = new com.mio.GUI.swing.Button();
        scroll = new javax.swing.JScrollPane();
        table = new com.mio.GUI.swing.MyTable();

        setOpaque(false);

        btnShow.setText("Xem");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        label1.setText("Tên nhóm:");

        label2.setText("CTĐT:");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDel.setText("Xóa");
        btnDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelActionPerformed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scroll.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuSplit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuSplit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(label2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxEduProgram, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbxDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(100, 100, 100)
                        .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuSplit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(menuSplit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEduProgram, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private List<StudentGroup> list;
    private int currentDep = -1;
    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        if(cbxDep.getSelectedIndex() != -1) {
            int selectedDepId = ((Department)cbxDep.getSelectedItem()).getId();
            if(selectedDepId != currentDep) {
                currentDep = selectedDepId;
                tfName.setText("");
                list = StudentGroupBLL.getInstance().getStudentGroupListByDepId(selectedDepId);
                List<SO> eduList = EduProgramBLL.getInstance().getSEduProgramByDepId(selectedDepId);
                cbxEduProgram.setModel(new DefaultComboBoxModel(eduList.toArray()));
                ((MyModel)table.getModel()).setList(list, eduList);
            }
        }
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if(currentDep != -1 && cbxEduProgram.getSelectedIndex() != -1) {
            StudentGroup s = new StudentGroup(-1, tfName.getText(), currentDep, ((SO)cbxEduProgram.getSelectedItem()).getId(), 0);
            StudentGroupBLL.getInstance().addStudentGroup(s);
            if(s.getId() != -1) {
                list.add(s);
                ((AbstractTableModel)table.getModel()).fireTableRowsInserted(list.size() - 1, list.size() - 1);
            } else {
                Main.getInstance().showNotification(Main.NotificationType.ADD_FAILED);
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int r = table.getSelectedRow();
        if(r != -1 && cbxEduProgram.getSelectedIndex() != -1) {
            StudentGroup s = new StudentGroup(list.get(r).getId(), tfName.getText(), currentDep, ((SO)cbxEduProgram.getSelectedItem()).getId(), list.get(r).getSize());
            if(StudentGroupBLL.getInstance().updateStudentGroup(s)) {
                list.get(r).copy(s);
                ((AbstractTableModel)table.getModel()).fireTableRowsUpdated(r, r);
            } else {
                Main.getInstance().showNotification(Main.NotificationType.UPDATE_FAILED);
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int r = table.getSelectedRow();
        if(r != -1) {
            if(StudentGroupBLL.getInstance().delStudentGroup(list.get(r).getId())) {
                list.remove(r);
                ((AbstractTableModel)table.getModel()).fireTableRowsDeleted(r, r);
            } else {
                Main.getInstance().showNotification(Main.NotificationType.DELETE_FAILED);
            }
        }
    }//GEN-LAST:event_btnDelActionPerformed

    public void refeshRow(StudentGroup s) {
        int i = list.size() - 1;
        for(; i >= 0; --i) {
            if(list.get(i).getId() == s.getId())
                break;
        }
        if(i != -1)
            ((AbstractTableModel)table.getModel()).fireTableRowsUpdated(i, i);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mio.GUI.swing.Button btnAdd;
    private com.mio.GUI.swing.Button btnDel;
    private com.mio.GUI.swing.Button btnEdit;
    private com.mio.GUI.swing.Button btnShow;
    private com.mio.GUI.swing.ComboBox cbxDep;
    private com.mio.GUI.swing.ComboBox cbxEduProgram;
    private com.mio.GUI.swing.Label label1;
    private com.mio.GUI.swing.Label label2;
    private com.mio.GUI.swing.MenuSplit menuSplit1;
    private com.mio.GUI.swing.MenuSplit menuSplit2;
    private javax.swing.JScrollPane scroll;
    private com.mio.GUI.swing.MyTable table;
    private com.mio.GUI.swing.TextField tfName;
    // End of variables declaration//GEN-END:variables
}
