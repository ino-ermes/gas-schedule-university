/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mio.GUI.form;

import com.mio.BLL.ClassBLL;
import com.mio.BLL.DepartmentBLL;
import com.mio.BLL.RoomBLL;
import com.mio.BLL.SectionBLL;
import com.mio.BLL.StudentGroupBLL;
import com.mio.BLL.SubjectBLL;
import com.mio.BLL.TeacherBLL;
import com.mio.DTO.CourseClass;
import com.mio.DTO.Department;
import com.mio.DTO.SO;
import com.mio.DTO.Section;
import com.mio.GUI.component.ScheduleTableModel;
import com.mio.GUI.dialog.BusyDialog;
import com.mio.GUI.dialog.MessageDialog;
import com.mio.GUI.main.Main;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import jnafilechooser.api.JnaFileChooser;

/**
 *
 * @author Mio
 */
public class AdminFormShowSchedule extends javax.swing.JPanel {

    /**
     * Creates new form AdminFormShowSchedule
     */
    public AdminFormShowSchedule() {
        initComponents();
        menuSplit1.setText("Xem lịch");
        cbxType.setModel(new DefaultComboBoxModel(new String[]{"Giáo viên", "Nhóm lớp", "Phòng học"}));
        cbxDep.setModel(new DefaultComboBoxModel(DepartmentBLL.getInstance().getDepartmentList().toArray()));
        if (cbxDep.getSelectedItem() != null) {
            Department dep = (Department) cbxDep.getSelectedItem();
            cbxName.setModel(new DefaultComboBoxModel(TeacherBLL.getInstance().getSTeacherList(dep.getId()).toArray()));
        }

        List<Section> sections = SectionBLL.getInstance().getSectionList();
        if (SectionBLL.getInstance().getCurSectionStatus() != Section.SectionStatus.NORMAL) {
            Section s = SectionBLL.getInstance().getCurSection();
            sections.remove(s);
        }
        cbxSection.setModel(new DefaultComboBoxModel(sections.toArray()));

        cbxType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    switch (cbxType.getSelectedIndex()) {
                        case 0 -> {
                            lblDep.setVisible(true);
                            cbxDep.setVisible(true);
                            btnBusy.setVisible(true);

                            if (cbxDep.getItemCount() != 0) {
                                cbxDep.setSelectedIndex(-1);
                                cbxDep.setSelectedIndex(0);
                            }
                        }
                        case 1 -> {
                            lblDep.setVisible(true);
                            cbxDep.setVisible(true);
                            btnBusy.setVisible(false);

                            if (cbxDep.getItemCount() != 0) {
                                cbxDep.setSelectedIndex(-1);
                                cbxDep.setSelectedIndex(0);
                            }
                        }
                        case 2 -> {
                            lblDep.setVisible(false);
                            cbxDep.setVisible(false);
                            btnBusy.setVisible(true);

                            cbxName.setModel(new DefaultComboBoxModel(RoomBLL.getInstance().getSRoomList().toArray()));
                        }
                    }
                }
            }
        });

        cbxDep.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Department dep = (Department) e.getItem();
                    switch (cbxType.getSelectedIndex()) {
                        case 0 -> {
                            cbxName.setModel(new DefaultComboBoxModel(TeacherBLL.getInstance().getSTeacherList(dep.getId()).toArray()));
                        }
                        case 1 -> {
                            cbxName.setModel(new DefaultComboBoxModel(StudentGroupBLL.getInstance().getSStudentGroupListByDepId(dep.getId()).toArray()));
                        }
                    }
                }
            }
        });
        scheduleTable.setModel(new ScheduleTableModel() {
            @Override
            public int getScheduleCount() {
                return courseClasses.size();
            }

            @Override
            public String getScheduleText(int i, int j) {
                switch (selectedType) {
                    case 0 -> {
                        switch (j) {
                            case 0 -> {
                                return "" + courseClasses.get(i).getId();
                            }
                            case 1 -> {
                                return subjects.get(courseClasses.get(i).getSubjectId());
                            }
                            case 2 -> {
                                return rooms.get(courseClasses.get(i).getRoomId());
                            }
                            case 3 -> {
                                String grs = "";
                                for (Integer id : courseClasses.get(i).getStudentGroupId()) {
                                    grs += " " + groups.get(id);
                                }
                                grs = grs.substring(1);
                                return grs;
                            }
                        }
                    }
                    case 1 -> {
                        switch (j) {
                            case 0 -> {
                                return "" + courseClasses.get(i).getId();
                            }
                            case 1 -> {
                                return subjects.get(courseClasses.get(i).getSubjectId());
                            }
                            case 2 -> {
                                return rooms.get(courseClasses.get(i).getRoomId());
                            }
                            case 3 -> {
                                return teachers.get(courseClasses.get(i).getTeacherId());
                            }
                        }
                    }
                    case 2 -> {
                        switch (j) {
                            case 0 -> {
                                return "" + courseClasses.get(i).getId();
                            }
                            case 1 -> {
                                return subjects.get(courseClasses.get(i).getSubjectId());
                            }
                            case 2 -> {
                                return teachers.get(courseClasses.get(i).getTeacherId());
                            }
                            case 3 -> {
                                String grs = "";
                                for (Integer id : courseClasses.get(i).getStudentGroupId()) {
                                    grs += " " + groups.get(id);
                                }
                                grs = grs.substring(1);
                                return grs;
                            }
                        }
                    }
                }
                return "";
            }

            @Override
            public int getCountPerSchedule() {
                return 4;
            }

            @Override
            public int getDay(int i) {
                return courseClasses.get(i).getTimeSlot() % 6;
            }

            @Override
            public int getTime(int i) {
                return courseClasses.get(i).getTimeSlot() / 6;
            }

            @Override
            public int getDuration(int i) {
                return courseClasses.get(i).getDuration();
            }

            @Override
            public void setAt(int i, Object o) {
                //let it be
            }

            @Override
            public Color getColor(int i) {
                return Color.WHITE;
            }

            @Override
            public boolean getVisible(int i) {
                return true;
            }
        });
    }

    private int selectedType = -1;
    private int selectedId = -1;
    private HashMap<Integer, String> rooms = new HashMap<>();
    private HashMap<Integer, String> teachers = new HashMap<>();
    private HashMap<Integer, String> groups = new HashMap<>();
    private HashMap<Integer, String> subjects = new HashMap<>();
    private List<CourseClass> courseClasses = new ArrayList<>();

    private void init() {
        for (CourseClass cc : courseClasses) {
            if (!rooms.containsKey(cc.getRoomId())) {
                rooms.put(cc.getRoomId(), RoomBLL.getInstance().getSRoom(cc.getRoomId()).getName());
            }
            if (!teachers.containsKey(cc.getTeacherId())) {
                teachers.put(cc.getTeacherId(), TeacherBLL.getInstance().getSTeacher(cc.getTeacherId()).getName());
            }
            for (Integer id : cc.getStudentGroupId()) {
                if (!groups.containsKey(id)) {
                    groups.put(id, StudentGroupBLL.getInstance().getNameById(id));
                }
            }
            if (!subjects.containsKey(cc.getSubjectId())) {
                subjects.put(cc.getSubjectId(), SubjectBLL.getInstance().getSSubject(cc.getSubjectId()).getName());
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

        menuSplit1 = new com.mio.GUI.swing.MenuSplit();
        lblType = new com.mio.GUI.swing.Label();
        cbxType = new com.mio.GUI.swing.ComboBox();
        lblName = new com.mio.GUI.swing.Label();
        cbxName = new com.mio.GUI.swing.ComboBox();
        lblDep = new com.mio.GUI.swing.Label();
        cbxDep = new com.mio.GUI.swing.ComboBox();
        lblSection = new com.mio.GUI.swing.Label();
        cbxSection = new com.mio.GUI.swing.ComboBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnShow = new com.mio.GUI.swing.Button();
        btnBusy = new com.mio.GUI.swing.Button();
        scheduleTable = new com.mio.GUI.component.ScheduleTable();
        btnExport = new com.mio.GUI.swing.Button();

        setOpaque(false);

        lblType.setText("Đối tượng:");

        lblName.setText("Tên:");

        lblDep.setText("Khoa:");

        lblSection.setText("Học kỳ:");
        lblSection.setMinimumSize(new java.awt.Dimension(100, 16));

        cbxSection.setMinimumSize(new java.awt.Dimension(200, 24));

        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        btnShow.setText("Xem");
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        btnBusy.setText("Lịch bận");
        btnBusy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout scheduleTableLayout = new javax.swing.GroupLayout(scheduleTable);
        scheduleTable.setLayout(scheduleTableLayout);
        scheduleTableLayout.setHorizontalGroup(
            scheduleTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        scheduleTableLayout.setVerticalGroup(
            scheduleTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );

        btnExport.setText("Xuất TKB");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuSplit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblType, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxType, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(cbxName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSection, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxDep, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(cbxSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(scheduleTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBusy, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuSplit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBusy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scheduleTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        Section s = (Section) cbxSection.getSelectedItem();
        if (s != null) {
            switch (cbxType.getSelectedIndex()) {
                case 0 -> {
                    SO t = ((SO) cbxName.getSelectedItem());
                    if (t != null) {
                        selectedId = t.getId();
                        selectedType = 0;
                        courseClasses = ClassBLL.getInstance().getClassByTeacher(selectedId, s.getId());
                        init();
                        scheduleTable.repaint();
                    }
                }
                case 1 -> {
                    SO gr = ((SO) cbxName.getSelectedItem());
                    if (gr != null) {
                        selectedId = gr.getId();
                        selectedType = 1;
                        courseClasses = ClassBLL.getInstance().getClassByStudentGroup(selectedId, s.getId());
                        init();
                        scheduleTable.repaint();
                    }
                }
                case 2 -> {
                    SO r = ((SO) cbxName.getSelectedItem());
                    if (r != null) {
                        selectedId = r.getId();
                        selectedType = 2;
                        courseClasses = ClassBLL.getInstance().getClassByRoom(selectedId, s.getId());
                        init();
                        scheduleTable.repaint();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnBusyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusyActionPerformed

        SO o = (SO) cbxName.getSelectedItem();
        if(o != null) {
            switch(cbxType.getSelectedIndex()) {
                case 0 -> {
                    String n = new BusyDialog(Main.getInstance(), TeacherBLL.getInstance().getBusy(o.getId())).showDialog();
                    if (n != null) {
                        TeacherBLL.getInstance().updateBusy(o.getId(), n);
                    }
                }
                case 2 -> {
                    String n = new BusyDialog(Main.getInstance(), RoomBLL.getInstance().getBusy(o.getId())).showDialog();
                    if (n != null) {
                        RoomBLL.getInstance().updateBusy(o.getId(), n);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnBusyActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        JnaFileChooser fc = new JnaFileChooser();
        fc.setMode(JnaFileChooser.Mode.Directories);
        boolean action = fc.showSaveDialog(Main.getInstance());
        if (action) {
            BufferedImage bImg = new BufferedImage(scheduleTable.getWidth(), scheduleTable.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D cg = bImg.createGraphics();
            scheduleTable.paintAll(cg);
            try {
                String name = fc.getSelectedFile().toString() + "\\" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date()) + ".png";
                if (ImageIO.write(bImg, "png", new File(name))) {
                    new MessageDialog(Main.getInstance(), "Xuất thành công", false).showDialog();
                    Desktop.getDesktop().open(new File(name));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnExportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mio.GUI.swing.Button btnBusy;
    private com.mio.GUI.swing.Button btnExport;
    private com.mio.GUI.swing.Button btnShow;
    private com.mio.GUI.swing.ComboBox cbxDep;
    private com.mio.GUI.swing.ComboBox cbxName;
    private com.mio.GUI.swing.ComboBox cbxSection;
    private com.mio.GUI.swing.ComboBox cbxType;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.mio.GUI.swing.Label lblDep;
    private com.mio.GUI.swing.Label lblName;
    private com.mio.GUI.swing.Label lblSection;
    private com.mio.GUI.swing.Label lblType;
    private com.mio.GUI.swing.MenuSplit menuSplit1;
    private com.mio.GUI.component.ScheduleTable scheduleTable;
    // End of variables declaration//GEN-END:variables
}
