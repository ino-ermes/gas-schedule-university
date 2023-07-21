
package com.mio.GUI.main;

import com.mio.BLL.StudentBLL;
import com.mio.BLL.StudentGroupBLL;
import com.mio.DTO.StudentGroup;
import com.mio.DTO.User;
import com.mio.GUI.event.MenuEvent;
import com.mio.GUI.form.AdminFormDepartment;
import com.mio.GUI.form.AdminFormEduProgram;
import com.mio.GUI.form.AdminFormMakeSchedule;
import com.mio.GUI.form.AdminFormRoom;
import com.mio.GUI.form.AdminFormShowSchedule;
import com.mio.GUI.form.AdminFormStudentGroup;
import com.mio.GUI.form.AdminFormSubject;
import com.mio.GUI.form.FormHome;
import com.mio.GUI.form.STFormShowSchedule;
import com.mio.GUI.form.StudentFormEduProgram;
import com.mio.GUI.notification.Notification;
import com.mio.GUI.swing.scroll.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;

public class Main extends javax.swing.JFrame {

    final private LoginMain loginMain;
    private static Main instance;
    public static Main getInstance() {
        return instance;
    }
    
    public Main(LoginMain loginMain, User user) {
        initComponents();
        
        setBackground(new Color(0, 0, 0, 0));
        setCustomScrollBar();
        
        instance = this;
        this.loginMain = loginMain;
        
        MenuEvent event = null;
        switch(user.getRole()) {
            case ADMIN -> {
                event = new MenuEvent() {
                    @Override
                    public void menuSelected(int index) {
                        switch (index) {
                            case 0 -> showForm(new FormHome(user));
                            case 1 -> showForm(new AdminFormDepartment());
                            case 2 -> showForm(new AdminFormSubject());
                            case 3 -> showForm(new AdminFormEduProgram());
                            case 4 -> showForm(new AdminFormStudentGroup());
                            case 5 -> showForm(new AdminFormRoom());
                            case 6 -> showForm(new AdminFormShowSchedule());
                            case 7 -> showForm(new AdminFormMakeSchedule());
                            default -> {
                            }
                        }
                    }
                };
            }
            case TEACHER -> {
                event = new MenuEvent() {
                    @Override
                    public void menuSelected(int index) {
                        switch (index) {
                            case 0 -> showForm(new FormHome(user));
                            case 1 -> showForm(new STFormShowSchedule(user.getId(), user.getRole()));
                        }
                    }
                };
            }
            case STUDENT -> {
                event = new MenuEvent() {
                    private StudentGroup gr = StudentGroupBLL.getInstance().getStudentGroup(StudentBLL.getInstance().getStudentGroupIdByStudent(user.getId()));
                    @Override
                    public void menuSelected(int index) {
                        switch (index) {
                            case 0 -> showForm(new FormHome(user));
                            case 1 -> showForm(new STFormShowSchedule(gr.getId(), user.getRole()));
                            case 2 -> showForm(new StudentFormEduProgram(gr.getEduProgramId()));
                        }
                    }
                };
            }
        }
        menu.initMenu(event, user.getRole());
        header.initMoving(instance, menu.getWidth());
    }

    @Override
    public void dispose() {
        loginMain.setVisible(true);
        instance = null;
        super.dispose();
    }
    
    private void setCustomScrollBar() {
        scroll.setViewportBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        scroll.setHorizontalScrollBar(new ScrollBarCustom());
    }
    
    public void showForm(Component form) {
        scroll.setViewportView(form);
    }
    
    public static enum NotificationType {
        ADD_FAILED, UPDATE_FAILED, DELETE_FAILED
    }
    
    public void showNotification(NotificationType t) {
        switch (t) {
            case ADD_FAILED -> {
                new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Thêm thất bại").showNotification();
            }
            case UPDATE_FAILED -> {
                new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Sửa thất bại").showNotification();
            }
            case DELETE_FAILED -> {
                new Notification(this, Notification.Type.WARNING, Notification.Location.TOP_CENTER, "Xoá thất bại").showNotification();
            }
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder = new com.mio.GUI.swing.PanelBorder();
        menu = new com.mio.GUI.component.Menu();
        header = new com.mio.GUI.component.Header();
        scroll = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        panelBorder.setBackground(new java.awt.Color(44, 116, 179));

        header.setBackground(new java.awt.Color(44, 116, 179));

        javax.swing.GroupLayout panelBorderLayout = new javax.swing.GroupLayout(panelBorder);
        panelBorder.setLayout(panelBorderLayout);
        panelBorderLayout.setHorizontalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorderLayout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                    .addGroup(panelBorderLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(scroll)
                        .addContainerGap())))
        );
        panelBorderLayout.setVerticalGroup(
            panelBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorderLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll)
                .addContainerGap())
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mio.GUI.component.Header header;
    private com.mio.GUI.component.Menu menu;
    private com.mio.GUI.swing.PanelBorder panelBorder;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
