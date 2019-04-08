package edu_edu;

import javax.swing.*;
import java.awt.*;

/**
 * 创建一个窗口，窗口内放置一个面板，在面板中显示一张图片和一段文字
 */
public class Test_4_4_2 extends JFrame {

    public static void main(String[] args) throws Exception {
        new Test_4_4_2();
    }

    public Test_4_4_2() {
        setTitle("test53");
        setSize(400, 220);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image img = toolkit.getImage("D:\\WEB\\fromGithub\\jvmtest\\src\\main\\java\\edu_edu\\test4_4_2.png");
        Container container = getContentPane();
        container.add(new MyPanel(img));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    class MyPanel extends JPanel {
        Image image;

        public MyPanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (null != image) {
                g.drawImage(image, 100, 30, this);
                g.drawString("我是一名学生", 100, 160);
            }
        }
    }
}
