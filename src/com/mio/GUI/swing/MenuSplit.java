
package com.mio.GUI.swing;

public class MenuSplit extends javax.swing.JPanel {

    public MenuSplit(String name) {
        this();
        label.setText(name);
    }
    
    public void setText(String text) {
        label.setText(text);
    }
    
    public MenuSplit() {
        initComponents();
        setOpaque(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelSplit1 = new com.mio.GUI.swing.LabelSplit();
        label = new javax.swing.JLabel();
        labelSplit2 = new com.mio.GUI.swing.LabelSplit();

        label.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        label.setForeground(new java.awt.Color(255, 255, 255));
        label.setText("Name");

        labelSplit2.setText("labelSplit2");
        labelSplit2.setGradiet(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelSplit1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSplit2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelSplit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(labelSplit2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel label;
    private com.mio.GUI.swing.LabelSplit labelSplit1;
    private com.mio.GUI.swing.LabelSplit labelSplit2;
    // End of variables declaration//GEN-END:variables
}
