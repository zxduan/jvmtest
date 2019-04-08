package edu_edu.test4;

import javax.swing.*;
import java.awt.*;

/**
 * 编写程序创建一个标题为MyFrame、背景为红色的Frame且课件
 */
public class Test4_6_1 extends JFrame {
    public static void main(String[] args) throws Exception {
        new Test4_6_1();
    }

    public Test4_6_1() {
        setTitle("MyFrame");
        setSize(400, 200);
        getContentPane().setBackground(Color.red);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
