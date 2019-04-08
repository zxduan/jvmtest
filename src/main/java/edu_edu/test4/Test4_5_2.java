package edu_edu.test4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 在文本框JTextField中输入17，按下回车键后，在文本区中会显示多少行证书，各行有几个数
 */
public class Test4_5_2 extends JFrame implements ActionListener {

    public static void main(String[] args) throws Exception {
        new Test4_5_2();
    }

    JTextField textField;
    JTextArea textArea;

    public Test4_5_2() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        textField = new JTextField(10);
        textField.addActionListener(this);
        textArea = new JTextArea(6, 10);
        setSize(240, 200);
        container.add(textField, "North");
        container.add(textArea, "Center");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int n, d;
        if (e.getSource() == textField) {
            textArea.setText("");
            n = Integer.parseInt(textField.getText());
            for (int k = 1; k <= n; k++) {
                d = (int) (Math.random() * 1000 % 1000);
                textArea.append("" + d);
                if (k % 5 == 0) {
                    textArea.append("\n");
                }
            }
        }
    }
}
