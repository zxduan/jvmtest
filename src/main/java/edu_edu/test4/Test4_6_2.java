package edu_edu.test4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 程序包含两个文本框sourceText和targetText;2个按钮, Clean , Copy
 * 实现简单的控制，单击Clean按钮清空两个文本框的内容
 * 单击copy，复制sourceText中的内容到targetText中
 */
public class Test4_6_2 extends JFrame implements ActionListener {

    public static void main(String[] args) throws Exception {
        new Test4_6_2();
    }

    private JTextField sourceText, targetText;
    private JButton clean, copy;

    public Test4_6_2() {
        sourceText = new JTextField(5);
        targetText = new JTextField(5);
        clean = new JButton("Clean");
        copy = new JButton("Copy");
        clean.addActionListener(this);
        copy.addActionListener(this);
        Container container = getContentPane();
        container.setLayout(new GridLayout(4, 1));
        container.add(sourceText);
        container.add(targetText);
        container.add(clean);
        container.add(copy);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clean) {
            sourceText.setText("");
            targetText.setText("");
        }
        if (e.getSource() == copy) {
            targetText.setText(sourceText.getText());
        }
    }
}
