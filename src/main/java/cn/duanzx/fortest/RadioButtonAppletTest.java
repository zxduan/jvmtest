package cn.duanzx.fortest;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioButtonAppletTest extends Applet implements ItemListener {
    JPanel pan = new JPanel();
    String[] s = {"足球","排球","篮球","乒乓球"};
    JRadioButton[] box = new JRadioButton[4];
    JTextField text = new JTextField(20);
    public void RadioButtonAppletTest() {
        setSize(400, 70);
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            box[i] = new JRadioButton(s[i]);
            group.add(box[i]);
            pan.add(box[i]);
            box[i].addItemListener(this);
        }
        add(pan);
        add(text);
        text.setBackground(Color.cyan);
        setVisible(true);
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        JRadioButton radioButton = (JRadioButton) e.getItemSelectable();
        text.setText("你选择来"+radioButton.getText());
    }
    public static void main(String[] args)throws Exception{
        new RadioButtonAppletTest();
    }
}
