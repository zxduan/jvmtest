package edu_edu.test4;

import javax.swing.*;
import java.awt.*;

/**
 * 以下程序创建了一个窗口，然后在窗口内显示Hello，World
 */
public class Test4_4_5 extends JFrame {

    public static void main(String[] args) throws Exception {
        new Test4_4_5();
    }

    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    public Test4_4_5() {
        setTitle("Hello World");
        setSize(WIDTH, HEIGHT);
        Container container = getContentPane();
        container.add(new TextPanel());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    class TextPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString("Hello,World", 100, 100);
        }
    }
}
