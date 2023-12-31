
package com.mio.GUI.component;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

public class Header extends javax.swing.JPanel {

    public Header() {
        initComponents();
        closeLbl.setBackground(new Color(0, 0, 0, 0));
        minimizeLbl.setBackground(new Color(0, 0, 0, 0));
        closeLbl.setIsMostRight(true);
    }
    
    private int x, y;
    JFrame parentFrame;
    public void initMoving(JFrame frame, int startLeft) {
        parentFrame = frame;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX() + startLeft;
                y = e.getY();
            }
            
        });
        
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }
            
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeLbl = new com.mio.GUI.swing.Navigator();
        minimizeLbl = new com.mio.GUI.swing.Navigator();

        setBackground(new java.awt.Color(50, 50, 50));
        setOpaque(false);

        closeLbl.setForeground(new java.awt.Color(255, 255, 255));
        closeLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        closeLbl.setText("✕");
        closeLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeLblMouseExited(evt);
            }
        });

        minimizeLbl.setForeground(new java.awt.Color(255, 255, 255));
        minimizeLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimizeLbl.setText("ー");
        minimizeLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minimizeLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                minimizeLblMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(340, Short.MAX_VALUE)
                .addComponent(minimizeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(closeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(closeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(minimizeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void closeLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLblMouseClicked
        parentFrame.dispose();
    }//GEN-LAST:event_closeLblMouseClicked

    private void closeLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLblMouseEntered
        closeLbl.setBackground(Color.RED);
    }//GEN-LAST:event_closeLblMouseEntered

    private void closeLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeLblMouseExited
        closeLbl.setBackground(getBackground());
    }//GEN-LAST:event_closeLblMouseExited

    private void minimizeLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeLblMouseClicked
        parentFrame.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_minimizeLblMouseClicked

    private void minimizeLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeLblMouseEntered
        minimizeLbl.setBackground(new Color(32, 82, 149));
    }//GEN-LAST:event_minimizeLblMouseEntered

    private void minimizeLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeLblMouseExited
        minimizeLbl.setBackground(getBackground());
    }//GEN-LAST:event_minimizeLblMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mio.GUI.swing.Navigator closeLbl;
    private com.mio.GUI.swing.Navigator minimizeLbl;
    // End of variables declaration//GEN-END:variables
}
