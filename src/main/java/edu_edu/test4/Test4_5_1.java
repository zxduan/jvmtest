package edu_edu.test4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * 随着滚动条滚动，给文本框赋值
 */
public class Test4_5_1 extends JFrame implements AdjustmentListener {

    public static void main(String[] args) throws Exception {
        new Test4_5_1();
    }

    private JTextField textField;
    private MyScrollBar scrollBar;

    public Test4_5_1() {
        super();
        scrollBar = new MyScrollBar(10, 10, 0, 255); // 最小值0 ， 最大值255 - 10 = 245
        Container container = getContentPane();
        container.setLayout(new GridLayout(2, 1));
        setSize(250, 100);
        setLocation(100, 100);
        scrollBar.addAdjustmentListener(this);
        textField = new JTextField("", 20);
        container.add(scrollBar);
        container.add(textField);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }


    @Override
    public void adjustmentValueChanged(AdjustmentEvent e) {
        MyScrollBar myScrollBar = (MyScrollBar) e.getSource();
        textField.setText(scrollBar.getValue() + "");
    }

    class MyScrollBar extends JScrollBar {
        public MyScrollBar(int init, int len, int low, int height) {
            super(JScrollBar.HORIZONTAL, init, len, low, height);
        }
    }
}
