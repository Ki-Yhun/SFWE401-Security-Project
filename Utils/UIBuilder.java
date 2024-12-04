package Utils;
import javax.swing.*;

public class UIBuilder {
    private JPanel panel;
    private int labelWidth;
    private int fieldWidth;
    private int height;
    public int labelX;
    private int fieldX;
    public int ySpacing;
    private int startY;
    public int multY;

    public UIBuilder(JPanel panel, int labelWidth, int fieldWidth, int height, int labelX, int fieldX, int ySpacing, int startY) {
        this.panel = panel;
        this.labelWidth = labelWidth;
        this.fieldWidth = fieldWidth;
        this.height = height;
        this.labelX = labelX;
        this.fieldX = fieldX;
        this.ySpacing = ySpacing;

        this.startY = startY;
        this.multY = 1;
    }

    public JTextField addTextField( boolean addY){
        JTextField textField = new JTextField();
        textField.setBounds(fieldX, startY + ySpacing * multY, fieldWidth, height);
        panel.add(textField);
        if(addY){
            multY++;
        }
        return textField;
    }
    public JLabel addLabel(String text, boolean addY){
        JLabel label = new JLabel(text);
        label.setBounds(labelX, startY + ySpacing * multY, fieldWidth, height);
        panel.add(label);
        if(addY){
            multY++;
        }
        return label;
    }

    public JButton addButton(String text, boolean addY, int x){
        JButton button = new JButton(text);
        button.setBounds(x, startY + ySpacing * multY, fieldWidth, height);
        panel.add(button);
        if(addY){
            multY++;
        }
        return button;
    }

    public JCheckBox addCheckBox(String text, boolean addY){
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setBounds(fieldX, startY + ySpacing * multY, fieldWidth, height);
        panel.add(checkBox);
        if(addY){
            multY++;
        }
        return checkBox;
    }

    public int getySpacing(){
        return this.ySpacing;
    }
    public int getMultY(){
        return this.multY;
    }
    public int getLabelX(){
        return this.labelX;
    }


}
