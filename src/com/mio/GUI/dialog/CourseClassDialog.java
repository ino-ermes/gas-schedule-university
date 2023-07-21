
package com.mio.GUI.dialog;

import com.mio.BLL.DepartmentBLL;
import com.mio.BLL.RoomBLL;
import com.mio.BLL.StudentGroupBLL;
import com.mio.BLL.SubjectBLL;
import com.mio.BLL.TeacherBLL;
import com.mio.DTO.CourseClass;
import com.mio.DTO.Department;
import com.mio.DTO.SO;
import com.mio.DTO.StudentGroup;
import com.mio.DTO.Subject;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
public class CourseClassDialog extends JDialog {
    private int x, y;
    public CourseClassDialog(java.awt.Frame parent, CourseClass cc, boolean isIncludeSchedule) {
        super(parent, true);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
            
        });
        
        CourseClassDialog me = this;
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                me.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
        });
        tfId.setEditable(false);
        tfGroup.setEditable(false);
        cbxDep.setModel(new DefaultComboBoxModel(DepartmentBLL.getInstance().getDepartmentList().toArray()));
        cbxDep.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    Department dep = (Department)e.getItem();
                    cbxSubject.setModel(new DefaultComboBoxModel(SubjectBLL.getInstance().getSubjectListByDepId(dep.getId()).toArray()));
                    cbxTeacher.setModel(new DefaultComboBoxModel(TeacherBLL.getInstance().getSTeacherList(dep.getId()).toArray()));
                    if(cbxSubject.getItemCount() != 0) {
                        cbxSubject.setSelectedIndex(-1);
                        cbxSubject.setSelectedIndex(0);
                    }
                    if(cbxTeacher.getItemCount() != 0) {
                        cbxTeacher.setSelectedIndex(-1);
                        cbxTeacher.setSelectedIndex(0);
                    }
                }
            }
            
        });
        cbxSubject.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                    Subject s = (Subject)e.getItem();
                    spnDuration.setValue((int)s.getCredit());
                    rdbRequireLab.setSelected(s.isRequireLab());
                }
            }
        });
        rdbRequireLab.addItemListener(new ItemListener() {
            Object[] rooms = RoomBLL.getInstance().getSRoomNormalList().toArray();
            Object[] roomsLab = RoomBLL.getInstance().getSRoomLabList().toArray();
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    cbxRoom.setModel(new DefaultComboBoxModel(roomsLab));

                }
                else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    cbxRoom.setModel(new DefaultComboBoxModel(rooms));
                }
            }
        });
        if(cbxDep.getItemCount() != 0) {
            cbxDep.setSelectedIndex(-1);
            cbxDep.setSelectedIndex(0);
        }
        String[] day = {"Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy"};
        String[] time = {"Tiết 1", "Tiết 2", "Tiết 3", "Tiết 4", "Tiết 5", "Tiết 6", "Tiết 7", "Tiết 8", "Tiết 9", "Tiết 10"};
        cbxDay.setModel(new DefaultComboBoxModel(day));
        cbxDay.setSelectedIndex(0);
        cbxTime.setModel(new DefaultComboBoxModel(time));
        cbxTime.setSelectedIndex(0);
        groups = new ArrayList<>();
        if(cc != null) {
            tfId.setText("" + cc.getId());
            Subject s = SubjectBLL.getInstance().getSubject(cc.getSubjectId());
            Department dep = new Department(s.getDepartmentId(), "");
            cbxDep.setSelectedItem(dep);
            cbxSubject.setSelectedItem(s);
            cbxTeacher.setSelectedItem(new SO(cc.getTeacherId()));
            spnDuration.setValue(cc.getDuration());
            for(int id: cc.getStudentGroupId()) {
                groups.add(StudentGroupBLL.getInstance().getStudentGroup(id));
                tfGroup.setText(tfGroup.getText() + ", " + groups.get(groups.size() - 1).getName());
            }
            tfGroup.setText(tfGroup.getText().substring(2));
            spnSize.setValue(cc.getSize());
            rdbRequireLab.setSelected(cc.isRequireLab());
            cbxRoom.setSelectedItem(new SO(cc.getRoomId()));
            cbxDay.setSelectedIndex((cc.getTimeSlot() == -1 ? -1 : cc.getTimeSlot() % 6));
            cbxTime.setSelectedIndex((cc.getTimeSlot() == -1 ? -1 : cc.getTimeSlot() / 6));
            spnWeekStart.setValue(cc.getWeekStart());
            spnWeekEnd.setValue(cc.getWeekEnd());
        } else {
            tfId.setText("Chưa có");
        }
        btnOkWithoutSchedule.setVisible(!isIncludeSchedule);
    }


    
    private CourseClass rtn;
    public CourseClass showDialog() {
        setVisible(true);
        return rtn;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.mio.GUI.swing.PanelBorder();
        panelBorder2 = new com.mio.GUI.swing.PanelBorder();
        btnOk = new com.mio.GUI.swing.Button();
        btnCancel = new com.mio.GUI.swing.Button();
        label1 = new com.mio.GUI.swing.Label();
        tfId = new com.mio.GUI.swing.TextField();
        label2 = new com.mio.GUI.swing.Label();
        cbxDep = new com.mio.GUI.swing.ComboBox();
        label3 = new com.mio.GUI.swing.Label();
        cbxSubject = new com.mio.GUI.swing.ComboBox();
        label4 = new com.mio.GUI.swing.Label();
        cbxTeacher = new com.mio.GUI.swing.ComboBox();
        label5 = new com.mio.GUI.swing.Label();
        spnDuration = new com.mio.GUI.swing.spinner.Spinner();
        label6 = new com.mio.GUI.swing.Label();
        tfGroup = new com.mio.GUI.swing.TextField();
        btnGroup = new com.mio.GUI.swing.Button();
        label7 = new com.mio.GUI.swing.Label();
        spnSize = new com.mio.GUI.swing.spinner.Spinner();
        rdbRequireLab = new com.mio.GUI.swing.RadioButton();
        lblRoom = new com.mio.GUI.swing.Label();
        cbxRoom = new com.mio.GUI.swing.ComboBox();
        lblTime = new com.mio.GUI.swing.Label();
        cbxDay = new com.mio.GUI.swing.ComboBox();
        cbxTime = new com.mio.GUI.swing.ComboBox();
        lblWeek = new com.mio.GUI.swing.Label();
        spnWeekStart = new com.mio.GUI.swing.spinner.Spinner();
        spnWeekEnd = new com.mio.GUI.swing.spinner.Spinner();
        btnOkWithoutSchedule = new com.mio.GUI.swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        panelBorder2.setBackground(new java.awt.Color(44, 116, 179));

        btnOk.setText("Ok");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        label1.setText("Mã học phần:");

        label2.setText("Khoa giảng dạy:");

        label3.setText("Môn học:");

        label4.setText("Giáo viên:");

        label5.setText("Thời lượng:");

        label6.setText("Nhóm lớp:");

        btnGroup.setText("Nhóm lớp");
        btnGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGroupActionPerformed(evt);
            }
        });

        label7.setText("Số lượng:");

        rdbRequireLab.setText("Yêu cầu phòng thực hành");

        lblRoom.setText("Phòng:");

        lblTime.setText("Thời gian:");

        lblWeek.setText("Tuần học:");

        btnOkWithoutSchedule.setText("Ok(Không lịch)");
        btnOkWithoutSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkWithoutScheduleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addContainerGap(149, Short.MAX_VALUE)
                        .addComponent(btnOkWithoutSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxSubject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spnDuration, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfGroup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnSize, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(rdbRequireLab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBorder2Layout.createSequentialGroup()
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(lblWeek, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelBorder2Layout.createSequentialGroup()
                                        .addComponent(cbxDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(8, 8, 8)
                                        .addComponent(cbxTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelBorder2Layout.createSequentialGroup()
                                        .addComponent(spnWeekStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(8, 8, 8)
                                        .addComponent(spnWeekEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addGap(20, 20, 20))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxDep, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbRequireLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxDay, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxTime, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnWeekStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnWeekEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOkWithoutSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        boolean good = false;
        rtn = new CourseClass();
        Subject s = (Subject)cbxSubject.getSelectedItem();
        SO t = (SO)cbxTeacher.getSelectedItem();
        if(s != null && t != null && !groups.isEmpty()) {
            int duration = (int)spnDuration.getValue();
            int minSize = 0;
            for(StudentGroup sg: groups) {
                minSize += sg.getSize();
            }
            int size = (int)spnSize.getValue();
            
            SO r = (SO)cbxRoom.getSelectedItem();
            int a = cbxDay.getSelectedIndex();
            int b = cbxTime.getSelectedIndex();
            int ws = (int)spnWeekStart.getValue();
            int we = (int)spnWeekEnd.getValue();
            good = duration > 0 && size >= minSize && r != null && a != -1 && b != -1&& (b + duration <= 10) && ws <= we;
            if(good) {
                rtn.setSubjectId(s.getId());
                rtn.setTeacherId(t.getId());
                rtn.setDuration(duration);
                rtn.setSize(size);
                rtn.setRequireLab(rdbRequireLab.isSelected());
                for(StudentGroup gr: groups) {
                    rtn.addStudentGroupId(gr.getId());
                }
                rtn.setRoomId(r.getId());
                rtn.setTimeSlot(b * 6 + a);
                rtn.setWeekStart(ws);
                rtn.setWeekEnd(we);
            }
        }
        if(good)
            this.dispose();
        else
            new MessageDialog(this, "Chưa thỏa một số điều kiện", false).showDialog();
    }//GEN-LAST:event_btnOkActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        rtn = null;
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed
    private ArrayList<StudentGroup> groups;
    private void btnGroupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGroupActionPerformed
        ArrayList<StudentGroup> grs = new StudentGroupChooserDialog(this, groups).showDialog();
        if(grs != null) {
            int total = 0;
            tfGroup.setText("");
            for(StudentGroup gr: grs) {
                tfGroup.setText(tfGroup.getText() + ", " + gr.getName());
                total += gr.getSize();
            }
            tfGroup.setText(tfGroup.getText().substring(2));
            spnSize.setValue(total);
            groups = grs;
        }
    }//GEN-LAST:event_btnGroupActionPerformed

    private void btnOkWithoutScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkWithoutScheduleActionPerformed
        boolean good = false;
        rtn = new CourseClass();
        Subject s = (Subject)cbxSubject.getSelectedItem();
        SO t = (SO)cbxTeacher.getSelectedItem();
        if(s != null && t != null && !groups.isEmpty()) {
            int duration = (int)spnDuration.getValue();
            int minSize = 0;
            for(StudentGroup sg: groups) {
                minSize += sg.getSize();
            }
            int size = (int)spnSize.getValue();
            
            good = duration > 0 && size >= minSize;
            if(good) {
                rtn.setSubjectId(s.getId());
                rtn.setTeacherId(t.getId());
                rtn.setDuration(duration);
                rtn.setSize(size);
                rtn.setRequireLab(rdbRequireLab.isSelected());
                for(StudentGroup gr: groups) {
                    rtn.addStudentGroupId(gr.getId());
                }
            }
        }
        if(good)
            this.dispose();
        else
            new MessageDialog(this, "Chưa thỏa một số điều kiện", false).showDialog();
    }//GEN-LAST:event_btnOkWithoutScheduleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mio.GUI.swing.Button btnCancel;
    private com.mio.GUI.swing.Button btnGroup;
    private com.mio.GUI.swing.Button btnOk;
    private com.mio.GUI.swing.Button btnOkWithoutSchedule;
    private com.mio.GUI.swing.ComboBox cbxDay;
    private com.mio.GUI.swing.ComboBox cbxDep;
    private com.mio.GUI.swing.ComboBox cbxRoom;
    private com.mio.GUI.swing.ComboBox cbxSubject;
    private com.mio.GUI.swing.ComboBox cbxTeacher;
    private com.mio.GUI.swing.ComboBox cbxTime;
    private com.mio.GUI.swing.Label label1;
    private com.mio.GUI.swing.Label label2;
    private com.mio.GUI.swing.Label label3;
    private com.mio.GUI.swing.Label label4;
    private com.mio.GUI.swing.Label label5;
    private com.mio.GUI.swing.Label label6;
    private com.mio.GUI.swing.Label label7;
    private com.mio.GUI.swing.Label lblRoom;
    private com.mio.GUI.swing.Label lblTime;
    private com.mio.GUI.swing.Label lblWeek;
    private com.mio.GUI.swing.PanelBorder panelBorder1;
    private com.mio.GUI.swing.PanelBorder panelBorder2;
    private com.mio.GUI.swing.RadioButton rdbRequireLab;
    private com.mio.GUI.swing.spinner.Spinner spnDuration;
    private com.mio.GUI.swing.spinner.Spinner spnSize;
    private com.mio.GUI.swing.spinner.Spinner spnWeekEnd;
    private com.mio.GUI.swing.spinner.Spinner spnWeekStart;
    private com.mio.GUI.swing.TextField tfGroup;
    private com.mio.GUI.swing.TextField tfId;
    // End of variables declaration//GEN-END:variables
}
