
package com.mio.GUI.swing;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel{
    
    public Label() {
        super();
        setFont(new Font("Arial", Font.PLAIN, 13));
        //setForeground(new Color(189, 189, 189));
        setForeground(Color.WHITE);
    }
    
    public Label(String text) {
        super(text);
        setFont(new Font("Arial", Font.PLAIN, 13));
        setForeground(new Color(189, 189, 189));
    }
}
