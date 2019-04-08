package edu_edu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Test_4_4_1 extends JFrame implements ItemListener {

    /**
     * 以下程序界面中有若干可以多选的选择框，当某个选择框的选择状态有所改变时，程序在文本区中显示个选择框的选择状态
     */
    public static void main(String[] args) throws Exception {
        new Test_4_4_1("请选择项目");
    }

    JTextArea jTextArea;
    JCheckBox[] box;
    String[] boxNmae = {"选择框1", "选择框2", "选择框3", "选择框4"};

    public Test_4_4_1(String title) {
        super(title);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(1, 2));//1行2列
        setLocation(100, 100);
        JPanel jPanel = new JPanel();
        int length = boxNmae.length;
        jPanel.setLayout(new GridLayout(length, 1));
        box = new JCheckBox[length];
        for (int i = 0; i < length; i++) {
            box[i] = new JCheckBox(boxNmae[i], false);//默认不被选中
            box[i].addItemListener(this);
            jPanel.add(box[i]);
        }
        jTextArea = new JTextArea(4, 10);
        setSize(300, 400);
        container.add(jPanel);
        container.add(jTextArea);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        jTextArea.setText("");
        for (int i = 0; i < boxNmae.length; i++) {
            if (box[i].isSelected()) {
                jTextArea.append(boxNmae[i] + "被选中\n");
                continue;
            }
            jTextArea.append(boxNmae[i] + "没有被选中\n");
        }
    }
}
