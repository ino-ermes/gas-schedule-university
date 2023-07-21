
package com.mio.GUI.form;

import com.mio.BLL.AdminBLL;
import com.mio.BLL.DepartmentBLL;
import com.mio.BLL.StudentBLL;
import com.mio.BLL.StudentGroupBLL;
import com.mio.BLL.TeacherBLL;
import com.mio.BLL.UserBLL;
import com.mio.DTO.Admin;
import com.mio.DTO.Student;
import com.mio.DTO.StudentGroup;
import com.mio.DTO.Teacher;
import com.mio.DTO.User;
import com.mio.GUI.main.Main;
import com.mio.GUI.notification.Notification;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import jnafilechooser.api.JnaFileChooser;

/**
 *
 * @author Mio
 */
public class FormHome extends javax.swing.JPanel {

    User user;
    public FormHome(User user) {
        initComponents();
        this.user = user;
        menuSplit1.setText("Thông tin cá nhân");
        menuSplit2.setText("Mật khẩu tài khoản");
        init();
    }
    private void init() {
        switch(user.getRole()) {
            case ADMIN -> {
                Admin admin = AdminBLL.getInstance().getAdmin(user.getId());
                if(!admin.getImagePath().isBlank()) {
                    imgAvt.setImage(admin.getImagePath());
                }
                tfId.setText(Integer.toString(user.getId()));
                tfName.setText(admin.getName());
                tfPhoneNumber.setText(admin.getPhoneNumber());
                tfGender.setText(admin.isMale() ? "Nam" : " Nữ");
                tfBirthday.setText(admin.getBirthday());
                lblGr.setVisible(false);
                tfGr.setVisible(false);
                lblDep.setVisible(false);
                tfDep.setVisible(false);
                btnChagneInfor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(curImgPath != null) {
                            String[] path = curImgPath.toString().split("_");
                            admin.setImagePath(path[0] + path[1]);
                            try {
                                Files.copy(curImgPath, Paths.get(admin.getImagePath()), StandardCopyOption.REPLACE_EXISTING);
                                Files.delete(curImgPath);
                            } catch(IOException ex) {
                                
                            }
                        }
                        admin.setPhoneNumber(tfPhoneNumber.getText());
                        if(!AdminBLL.getInstance().updateAdmin(admin)) {
                            new Notification(Main.getInstance(), Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Đổi thất bại!").showNotification();
                        } else {
                            new Notification(Main.getInstance(), Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Đổi thành công!").showNotification();
                        }
                    }
                });
            }
            case TEACHER -> {
                Teacher teacher = TeacherBLL.getInstance().getTeacher(user.getId());
                if(!teacher.getImagePath().isBlank()) {
                    imgAvt.setImage(teacher.getImagePath());
                }
                tfId.setText(Integer.toString(user.getId()));
                tfName.setText(teacher.getName());
                tfPhoneNumber.setText(teacher.getPhoneNumber());
                tfGender.setText(teacher.isMale() ? "Nam" : " Nữ");
                tfBirthday.setText(teacher.getBirthday());
                lblDep.setText("Khoa:");
                tfDep.setText(DepartmentBLL.getInstance().getNameById(teacher.getDepartmentId()));
                lblGr.setVisible(false);
                tfGr.setVisible(false);
                btnChagneInfor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(curImgPath != null) {
                            String[] path = curImgPath.toString().split("_");
                            teacher.setImagePath(path[0] + path[1]);
                            try {
                                Files.copy(curImgPath, Paths.get(teacher.getImagePath()), StandardCopyOption.REPLACE_EXISTING);
                                Files.delete(curImgPath);
                            } catch(IOException ex) {
                                
                            }
                        }
                        teacher.setPhoneNumber(tfPhoneNumber.getText());
                        if(!TeacherBLL.getInstance().updateTeacher(teacher)) {
                            new Notification(Main.getInstance(), Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Đổi thất bại!").showNotification();
                        } else {
                            new Notification(Main.getInstance(), Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Đổi thành công!").showNotification();
                        }
                    }
                });
            }
            case STUDENT -> {
                Student student = StudentBLL.getInstance().getStudent(user.getId());
                if(!student.getImagePath().isBlank()) {
                    imgAvt.setImage(student.getImagePath());
                }
                tfId.setText(Integer.toString(user.getId()));
                tfName.setText(student.getName());
                tfPhoneNumber.setText(student.getPhoneNumber());
                tfGender.setText(student.isMale() ? "Nam" : " Nữ");
                tfBirthday.setText(student.getBirthday());
                StudentGroup gr = StudentGroupBLL.getInstance().getStudentGroup(student.getStudentGroupId());
                lblDep.setText("Khoa:");
                tfDep.setText(DepartmentBLL.getInstance().getNameById(gr.getDepartmentId()));
                lblGr.setText("Nhóm lớp:");
                tfGr.setText(gr.getName());
                btnChagneInfor.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(curImgPath != null) {
                            String[] path = curImgPath.toString().split("_");
                            student.setImagePath(path[0] + path[1]);
                            try {
                                Files.copy(curImgPath, Paths.get(student.getImagePath()), StandardCopyOption.REPLACE_EXISTING);
                                Files.delete(curImgPath);
                            } catch(IOException ex) {
                                
                            }
                        }
                        student.setPhoneNumber(tfPhoneNumber.getText());
                        if(!StudentBLL.getInstance().updateStudent(student)) {
                            new Notification(Main.getInstance(), Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Đổi thất bại!").showNotification();
                        } else {
                            new Notification(Main.getInstance(), Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Đổi thành công!").showNotification();
                        }
                    }
                });
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
        imgAvt = new com.mio.GUI.swing.ImageAvatar();
        btnChangeAvt = new com.mio.GUI.swing.Button();
        label1 = new com.mio.GUI.swing.Label();
        label2 = new com.mio.GUI.swing.Label();
        label3 = new com.mio.GUI.swing.Label();
        label4 = new com.mio.GUI.swing.Label();
        lblDep = new com.mio.GUI.swing.Label();
        label6 = new com.mio.GUI.swing.Label();
        tfName = new com.mio.GUI.swing.TextField();
        tfPhoneNumber = new com.mio.GUI.swing.TextField();
        tfId = new com.mio.GUI.swing.TextField();
        tfDep = new com.mio.GUI.swing.TextField();
        tfGender = new com.mio.GUI.swing.TextField();
        tfBirthday = new com.mio.GUI.swing.TextField();
        btnChagneInfor = new com.mio.GUI.swing.Button();
        menuSplit2 = new com.mio.GUI.swing.MenuSplit();
        label8 = new com.mio.GUI.swing.Label();
        label9 = new com.mio.GUI.swing.Label();
        label10 = new com.mio.GUI.swing.Label();
        pfOldPassword = new com.mio.GUI.swing.PasswordField();
        pfRePassword = new com.mio.GUI.swing.PasswordField();
        pfNewPassword = new com.mio.GUI.swing.PasswordField();
        btnChangePassword = new com.mio.GUI.swing.Button();
        lblGr = new com.mio.GUI.swing.Label();
        tfGr = new com.mio.GUI.swing.TextField();

        setOpaque(false);

        imgAvt.setGradientColor1(new java.awt.Color(204, 204, 204));
        imgAvt.setGradientColor2(new java.awt.Color(204, 204, 204));

        btnChangeAvt.setText("Đổi");
        btnChangeAvt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeAvtActionPerformed(evt);
            }
        });

        label1.setText("Mã số:");

        label2.setText("Họ và tên:");

        label3.setText("Giới tính:");

        label4.setText("SĐT:");

        lblDep.setText("Nơi sinh:");

        label6.setText("Ngày sinh:");

        tfName.setEditable(false);

        tfId.setEditable(false);

        tfDep.setEditable(false);

        tfGender.setEditable(false);

        tfBirthday.setEditable(false);

        btnChagneInfor.setText("Xác nhận");
        btnChagneInfor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChagneInforActionPerformed(evt);
            }
        });

        label8.setText("Mật khẩu mới:");

        label9.setText("Mật khẩu cũ:");

        label10.setText("Nhập lại:");

        pfOldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfOldPasswordActionPerformed(evt);
            }
        });

        pfRePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfRePasswordActionPerformed(evt);
            }
        });

        pfNewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pfNewPasswordActionPerformed(evt);
            }
        });

        btnChangePassword.setText("Xác nhận");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });

        lblGr.setText("Nơi sinh:");

        tfGr.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuSplit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(menuSplit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pfOldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                            .addComponent(pfNewPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pfRePassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(imgAvt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnChangeAvt, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblGr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnChagneInfor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tfName, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(tfPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(tfId, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(tfDep, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(tfGender, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(tfBirthday, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(tfGr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuSplit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imgAvt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnChangeAvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfGr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnChagneInfor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuSplit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfOldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pfRePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pfOldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfOldPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pfOldPasswordActionPerformed

    private void pfRePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfRePasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pfRePasswordActionPerformed

    private void pfNewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pfNewPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pfNewPasswordActionPerformed

    private void btnChagneInforActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChagneInforActionPerformed
        
    }//GEN-LAST:event_btnChagneInforActionPerformed

    private boolean strComp(char[] a, char[] b) {
        if(a.length == b.length) {
            for(int i = 0; i != a.length; ++i) {
                if(a[i] != b[i])
                    return false;
            }
            return true;
        }
        return false;
    }
    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        if(pfOldPassword.getPassword().length != 0 || pfNewPassword.getPassword().length != 0 || pfRePassword.getPassword().length != 0) {
            if(strComp(pfNewPassword.getPassword(), pfRePassword.getPassword())) {
                if(!UserBLL.getInstance().changePassword(user.getId(), pfOldPassword.getPassword(), pfNewPassword.getPassword())) {
                    new Notification(Main.getInstance(), Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Đổi mật khẩu thất bại!").showNotification();
                } else {
                    new Notification(Main.getInstance(), Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Đổi mật khẩu thành công!").showNotification();
                }
            } else new Notification(Main.getInstance(), Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Mật khẩu không khớp!").showNotification();
        } else new Notification(Main.getInstance(), Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Không được để trống").showNotification();
    }//GEN-LAST:event_btnChangePasswordActionPerformed
    private Path curImgPath;
    private void btnChangeAvtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeAvtActionPerformed
        JnaFileChooser nFc = new JnaFileChooser();
        nFc.setCurrentDirectory("‪\\Users\\Mio\\Documents");
        nFc.setTitle("Chọn ảnh");
        nFc.setMode(JnaFileChooser.Mode.Files);
        nFc.addFilter("*.png", "png");
        nFc.addFilter("*.jpg", "jpg");
        boolean action = nFc.showOpenDialog(Main.getInstance());
        if(action && (nFc.getSelectedFile().toString().endsWith(".png") || nFc.getSelectedFile().toString().endsWith(".jpg"))) {
            try {
                Path from = nFc.getSelectedFile().toPath();
                String ext = from.toString();
                ext = ext.substring(ext.lastIndexOf("."));
                Path to = Paths.get("\\Users\\Mio\\Documents\\imgtest\\avt_" + user.getId() + ext);
                curImgPath = to;
                Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
                imgAvt.setImage(to.toString());
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } 
    }//GEN-LAST:event_btnChangeAvtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mio.GUI.swing.Button btnChagneInfor;
    private com.mio.GUI.swing.Button btnChangeAvt;
    private com.mio.GUI.swing.Button btnChangePassword;
    private com.mio.GUI.swing.ImageAvatar imgAvt;
    private com.mio.GUI.swing.Label label1;
    private com.mio.GUI.swing.Label label10;
    private com.mio.GUI.swing.Label label2;
    private com.mio.GUI.swing.Label label3;
    private com.mio.GUI.swing.Label label4;
    private com.mio.GUI.swing.Label label6;
    private com.mio.GUI.swing.Label label8;
    private com.mio.GUI.swing.Label label9;
    private com.mio.GUI.swing.Label lblDep;
    private com.mio.GUI.swing.Label lblGr;
    private com.mio.GUI.swing.MenuSplit menuSplit1;
    private com.mio.GUI.swing.MenuSplit menuSplit2;
    private com.mio.GUI.swing.PasswordField pfNewPassword;
    private com.mio.GUI.swing.PasswordField pfOldPassword;
    private com.mio.GUI.swing.PasswordField pfRePassword;
    private com.mio.GUI.swing.TextField tfBirthday;
    private com.mio.GUI.swing.TextField tfDep;
    private com.mio.GUI.swing.TextField tfGender;
    private com.mio.GUI.swing.TextField tfGr;
    private com.mio.GUI.swing.TextField tfId;
    private com.mio.GUI.swing.TextField tfName;
    private com.mio.GUI.swing.TextField tfPhoneNumber;
    // End of variables declaration//GEN-END:variables
}
