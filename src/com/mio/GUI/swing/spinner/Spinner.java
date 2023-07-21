
package com.mio.GUI.swing.spinner;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Spinner extends JSpinner {

    public Spinner() {
        setOpaque(false);
        setUI(new SpinnerUI());
        setModel(new SpinnerNumberModel(0, 0, null, 1));
    }
}