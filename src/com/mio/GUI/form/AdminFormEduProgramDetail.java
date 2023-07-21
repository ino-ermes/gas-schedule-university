
package com.mio.GUI.form;

import com.mio.BLL.EduProgramBLL;
import com.mio.BLL.SubjectBLL;
import com.mio.DTO.EduProgram;
import com.mio.DTO.EduProgramDetail;
import com.mio.DTO.SO;
import com.mio.DTO.Subject;
import com.mio.GUI.main.Main;
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
public class AdminFormEduProgramDetail extends javax.swing.JPanel {

    private EduProgram eduProgram;
    private AdminFormEduProgram parent;
    
    public AdminFormEduProgramDetail(AdminFormEduProgram parent, EduProgram eduProgram) {
        initComponents();
        
        this.eduProgram = eduProgram;
        this.parent = parent;
        
        menuSplit1.setText(eduProgram.getName());

        for(int i = 1; i <= eduProgram.getSemesterCount(); ++i) {
            cbxSemester.addItem("Kỳ " + i);
        }
     
        cbxSubject.setModel(new DefaultComboBoxModel(SubjectBLL.getInstance().getSSubjectList().toArray()));
        
        table.fixTable(scroll);
        list = EduProgramBLL.getInstance().getEduProgDetail(eduProgram.getId());
        subjects = new HashMap<>();
        Subject s;
        for(EduProgramDetail e: list) {
            s = SubjectBLL.getInstance().getSubject(e.getSubjectId());
            subjects.put(s.getId(), s);
        }
        table.setModel(new MyModel(list, subjects));
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {

                    SO s = new SO(list.get(table.getSelectedRow()).getSubjectId()); 
                    cbxSubject.setSelectedItem(s);
                    cbxSemester.setSelectedIndex(list.get(table.getSelectedRow()).getSemester() - 1);
                }
            }
        });
    }
    
    List<EduProgramDetail> list;
    HashMap<Integer, Subject> subjects;
    private class MyModel extends AbstractTableModel {
        List<EduProgramDetail> list;
        HashMap<Integer, Subject> subjects;
        
        MyModel(List<EduProgramDetail> list, HashMap<Integer, Subject> subjects) {
            this.list = list;
            this.subjects = subjects;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0 -> {
                    return "Học kỳ";
                }
                case 1 -> {
                    return "Mã môn học";
                }
                case 2 -> {
                    return "Tên môn học";
                }
                case 3 -> {
                    return "Loại môn";
                }
                default -> {
                    return "Tín chỉ";
                }
            }
        }
        
        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public int getRowCount() {
            return list.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            EduProgramDetail e = list.get(rowIndex);
            Subject s = subjects.get(e.getSubjectId());
            switch(columnIndex) {
                case 0 -> {
                    return e.getSemester();
                }
                case 1 -> {
                    return e.getSubjectId();
                }
                case 2 -> {
                    return s.getName();
                }
                case 3 -> {
                    return s.isRequireLab() ? "Thực hành" : "Lý thuyết";
                }
                default -> {
                    return s.getCredit();
                }
            }
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

        btnBack = new com.mio.GUI.swing.ActionButton();
        menuSplit1 = new com.mio.GUI.swing.MenuSplit();
        label1 = new com.mio.GUI.swing.Label();
        label2 = new com.mio.GUI.swing.Label();
        cbxSubject = new com.mio.GUI.swing.ComboBox();
        cbxSemester = new com.mio.GUI.swing.ComboBox();
        btnAdd = new com.mio.GUI.swing.Button();
        btnEdit = new com.mio.GUI.swing.Button();
        btnDel = new com.mio.GUI.swing.Button();
        scroll = new javax.swing.JScrollPane();
        table = new com.mio.GUI.swing.MyTable();

        setOpaque(false);

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mio/GUI/icon/back_16.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        label1.setText("Môn học:");

        label2.setText("Học kỳ:");

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
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuSplit1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scroll)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxSemester, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbxSubject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menuSplit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnDel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if(cbxSubject.getSelectedIndex() != -1 && cbxSemester.getSelectedIndex() != -1) {
            EduProgramDetail edu = new EduProgramDetail(-1, eduProgram.getId(), ((SO)cbxSubject.getSelectedItem()).getId(), cbxSemester.getSelectedIndex() + 1);
            EduProgramBLL.getInstance().addEduProgDetail(edu);
            if(edu.getId() != -1) {
                list.add(edu);
                Subject s = SubjectBLL.getInstance().getSubject(edu.getSubjectId());
                subjects.put(s.getId(), s);
                ((AbstractTableModel)table.getModel()).fireTableRowsInserted(list.size() - 1, list.size() - 1);

            } else {
                Main.getInstance().showNotification(Main.NotificationType.ADD_FAILED);
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int r = table.getSelectedRow();
        if(r != -1) {
            EduProgramDetail edu = list.get(r);
            EduProgramDetail edit = new EduProgramDetail(edu.getId(), edu.getEduProgramId(), ((SO)cbxSubject.getSelectedItem()).getId(), cbxSemester.getSelectedIndex() + 1);
            if(EduProgramBLL.getInstance().updateEduProgDetail(edit)) {
                
                if(edu.getSubjectId() != edit.getSubjectId()) {
                    subjects.remove(edu.getSubjectId());
                    Subject s = SubjectBLL.getInstance().getSubject(edu.getSubjectId());
                    subjects.put(s.getId(), s);
                }
                
                edu.copy(edit);
                ((AbstractTableModel)table.getModel()).fireTableRowsUpdated(r, r);
            } else {
                Main.getInstance().showNotification(Main.NotificationType.UPDATE_FAILED);
            }
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelActionPerformed
        int r = table.getSelectedRow();
        if(r != -1) {
            if(EduProgramBLL.getInstance().delEduProgDetail(list.get(r).getId())) {
                EduProgramDetail e = list.remove(r);
                subjects.remove(e.getSubjectId());
                ((AbstractTableModel)table.getModel()).fireTableRowsDeleted(r, r);

            } else {
                Main.getInstance().showNotification(Main.NotificationType.DELETE_FAILED);
            }
        }
    }//GEN-LAST:event_btnDelActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        Main.getInstance().showForm(parent);
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mio.GUI.swing.Button btnAdd;
    private com.mio.GUI.swing.ActionButton btnBack;
    private com.mio.GUI.swing.Button btnDel;
    private com.mio.GUI.swing.Button btnEdit;
    private com.mio.GUI.swing.ComboBox cbxSemester;
    private com.mio.GUI.swing.ComboBox cbxSubject;
    private com.mio.GUI.swing.Label label1;
    private com.mio.GUI.swing.Label label2;
    private com.mio.GUI.swing.MenuSplit menuSplit1;
    private javax.swing.JScrollPane scroll;
    private com.mio.GUI.swing.MyTable table;
    // End of variables declaration//GEN-END:variables
}