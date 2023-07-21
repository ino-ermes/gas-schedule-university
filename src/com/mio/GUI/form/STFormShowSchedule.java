/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mio.GUI.form;

import com.mio.BLL.ClassBLL;
import com.mio.BLL.RoomBLL;
import com.mio.BLL.SectionBLL;
import com.mio.BLL.StudentGroupBLL;
import com.mio.BLL.SubjectBLL;
import com.mio.BLL.TeacherBLL;
import com.mio.DTO.CourseClass;
import com.mio.DTO.Section;
import com.mio.DTO.User;
import com.mio.GUI.component.ScheduleTableModel;
import com.mio.GUI.dialog.BusyDialog;
import com.mio.GUI.dialog.MessageDialog;
import com.mio.GUI.main.Main;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class STFormShowSchedule extends javax.swing.JPanel {

    /**
     * Creates new form STFormShowSchedule
     */
    public STFormShowSchedule(int userId, User.UserRole role) {
        initComponents();
        menuSplit1.setText("Xem lịch");
        if(role != User.UserRole.TEACHER) {
            btnBusy.setVisible(false);
        }
        List<Section> sections = SectionBLL.getInstance().getSectionList();
        if(SectionBLL.getInstance().getCurSectionStatus() != Section.SectionStatus.NORMAL) {
            Section s = SectionBLL.getInstance().getCurSection();
            sections.remove(s);
        }
        cbxSection.setModel(new DefaultComboBoxModel(sections.toArray()));
        
        scheduleTable.setModel(new ScheduleTableModel() {
            @Override
            public int getScheduleCount() {
                return courseClasses.size();
            }

            @Override
            public String getScheduleText(int i, int j) {
                switch (role) {
                    case TEACHER -> {
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
                                for(Integer id: courseClasses.get(i).getStudentGroupId()) {
                                    grs += " " + groups.get(id);
                                }
                                grs = grs.substring(1);
                                return grs;
                            }
                        }
                    }
                    case STUDENT -> {
                        switch(j) {
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
        
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Section s = (Section)cbxSection.getSelectedItem();
                if(s != null) switch(role) {
                    case TEACHER -> {
                        courseClasses = ClassBLL.getInstance().getClassByTeacher(userId, s.getId());
                        init();
                        scheduleTable.repaint();
                    }
                    case STUDENT -> {
                        courseClasses = ClassBLL.getInstance().getClassByStudentGroup(userId, s.getId());
                        init();
                        scheduleTable.repaint();
                    }
                }
            }
        });
        
        btnBusy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = new BusyDialog(Main.getInstance(), TeacherBLL.getInstance().getBusy(userId)).showDialog();
                if(n != null) {
                    TeacherBLL.getInstance().updateBusy(userId, n);
                }
            }
        });
    }
    
    private HashMap<Integer, String> rooms = new HashMap<>();
    private HashMap<Integer, String> teachers = new HashMap<>();
    private HashMap<Integer, String> groups = new HashMap<>();
    private HashMap<Integer, String> subjects = new HashMap<>();
    private List<CourseClass> courseClasses = new ArrayList<>();
    
    private void init() {
        for(CourseClass cc: courseClasses) {
            if(!rooms.containsKey(cc.getRoomId())) {
                rooms.put(cc.getRoomId(), RoomBLL.getInstance().getSRoom(cc.getRoomId()).getName());
            }
            if(!teachers.containsKey(cc.getTeacherId())) {
                teachers.put(cc.getTeacherId(), TeacherBLL.getInstance().getSTeacher(cc.getTeacherId()).getName());
            }
            for(Integer id: cc.getStudentGroupId()) {
                if(!groups.containsKey(id)) {
                    groups.put(id, StudentGroupBLL.getInstance().getNameById(id));
                }
            }
            if(!subjects.containsKey(cc.getSubjectId())) {
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
        label1 = new com.mio.GUI.swing.Label();
        cbxSection = new com.mio.GUI.swing.ComboBox();
        btnShow = new com.mio.GUI.swing.Button();
        btnBusy = new com.mio.GUI.swing.Button();
        scheduleTable = new com.mio.GUI.component.ScheduleTable();
        btnExport = new com.mio.GUI.swing.Button();

        setOpaque(false);

        label1.setText("Học kỳ:");

        btnShow.setText("Xem");

        btnBusy.setText("Lịch bận");

        javax.swing.GroupLayout scheduleTableLayout = new javax.swing.GroupLayout(scheduleTable);
        scheduleTable.setLayout(scheduleTableLayout);
        scheduleTableLayout.setHorizontalGroup(
            scheduleTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 631, Short.MAX_VALUE)
        );
        scheduleTableLayout.setVerticalGroup(
            scheduleTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scheduleTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBusy, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuSplit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBusy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scheduleTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

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
    private com.mio.GUI.swing.ComboBox cbxSection;
    private com.mio.GUI.swing.Label label1;
    private com.mio.GUI.swing.MenuSplit menuSplit1;
    private com.mio.GUI.component.ScheduleTable scheduleTable;
    // End of variables declaration//GEN-END:variables
}
