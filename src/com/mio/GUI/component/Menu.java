
package com.mio.GUI.component;

import com.mio.DTO.User;
import com.mio.GUI.event.MenuEvent;
import com.mio.GUI.swing.MenuButton;
import com.mio.GUI.swing.MenuSplit;
import com.mio.GUI.swing.scroll.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Menu extends javax.swing.JPanel {

    private Animator animator;
    private MenuButton selectedMenu;
    private MenuButton unselectedMenu;
    MenuEvent event;
    
    public Menu() {
        initComponents();
        setOpaque(false);
        scroll.setViewportBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        scroll.setHorizontalScrollBar(null);
        panelMenu.setLayout(new MigLayout("wrap, fillx, inset 0", "[fill]"));
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                selectedMenu.setAnimate(fraction);
                if(unselectedMenu != null) {
                    unselectedMenu.setAnimate(1f - fraction);
                }
            }
        };
        animator = new Animator(300, target);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
        animator.setResolution(0);
    }
    
    public void initMenu(MenuEvent event, User.UserRole userRole) {
        this.event = event;
        switch (userRole) {
            case ADMIN -> {
                addMenu("icons8-security-pass-16", "Cá nhân", 0);
                addMenu("icons8-department-16", "Khoa", 1);
                addMenu("icons8-physics-book-16", "Môn học", 2);
                addMenu("icons8-program-16", "CTĐT", 3);
                addMenu("icons8-group-16", "Nhóm lớp", 4);
                addMenu("icons8-room-16", "Phòng", 5);
                addMenu("icons8-schedule-16", "Xem lịch", 6);
                addMenu("icons8-scheduling-16", "Lập lịch", 7);
            }
            case TEACHER -> {
                addMenu("back_16", "Cá nhân", 0);
                addMenu("back_16", "Lịch dạy", 1);
            }
            case STUDENT -> {
                addMenu("back_16", "Cá nhân", 0);
                addMenu("back_16", "Lịch học", 1);
                addMenu("back_16", "CTĐT", 2);
            }
        }
    }

    private void addMenu(String icon, String text, int index) {
        MenuButton menu = new MenuButton(index);
        setFont(menu.getFont().deriveFont(Font.PLAIN, 14));
        menu.setIcon(new ImageIcon(getClass().getResource("/com/mio/GUI/icon/" + icon + ".png")));
        menu.setText("  " + text);
        
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!animator.isRunning()) {
                    if(menu != selectedMenu) {
                        event.menuSelected(menu.getIndex());
                        unselectedMenu = selectedMenu;
                        selectedMenu = menu;
                        animator.start();
                    }
                }
            }
        });
        
        panelMenu.add(menu);
    }
    
    private void addSplit(String name) {
        panelMenu.add(new MenuSplit(name), "h 30");
    }
    
    public void setSelected(int index) {
        for(Component it: panelMenu.getComponents()) {
            MenuButton menu = (MenuButton)it;
            if(menu.getIndex() == index) {
                if(!animator.isRunning()) {
                    if(menu != selectedMenu) {
                        event.menuSelected(menu.getIndex());
                        unselectedMenu = selectedMenu;
                        selectedMenu = menu;
                        animator.start();
                    }
                }                
            }
        }
    }
    
    private void addVerticalSpring() {
        panelMenu.add(new JLabel(), "push");
    }
    
    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        GradientPaint grd = new GradientPaint(0, 0, Color.decode("#2C74B3"), 0, getHeight(), Color.decode("#144272"));
        g2.setPaint(grd);
        
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(g);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        panelMenu = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mio/GUI/icon/icons8-university-48.png"))); // NOI18N
        jLabel1.setText("BKĐN");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 30, 1, 1));

        panelMenu.setOpaque(false);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );

        scroll.setViewportView(panelMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scroll)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
