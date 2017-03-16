package view;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.text.JTextComponent;

public class HintedInputListener implements FocusListener {

    private JTextComponent source;
    private String hint;

    public HintedInputListener(JTextComponent source, String hint) {
        this.source = source;
        this.hint = hint;
    }
    
    public HintedInputListener(JTextComponent source) {
        this.source = source;
        this.hint = source.getText();
    }

    @Override
    public void focusGained(FocusEvent e) {
    	if (!source.getText().equals("")) {
    		this.hint = source.getText();
    	}
        emptyField();
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (source.getText().equals("")) {
            setHint();
        }
    }

    private void setHint() {
        source.setText(hint);
    }

    private void emptyField() {
        source.setText("");
    }
}
