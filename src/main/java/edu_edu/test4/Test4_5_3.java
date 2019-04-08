package edu_edu.test4;

import javax.swing.*;
import java.awt.*;

/**
 * 添加两个文本框，设置字体以及颜色
 * 启动两个线程，每200毫秒给文本框赋一个随机字符串的值
 */
public class Test4_5_3 extends JFrame {

    public static void main(String[] args) throws Exception {
        new Test4_5_3();
    }

    private JTextField text1, text2;
    private MyThread th1, th2;

    public Test4_5_3() {
        setLayout(null);
        text1 = new JTextField();
        text2 = new JTextField();
        text1.setBounds(20, 20, 150, 45);
        text2.setBounds(20, 75, 150, 45);
        text1.setFont(new Font("", 1, 40));
        text2.setFont(new Font("", 1, 40));
        text1.setForeground(Color.red);
        text2.setForeground(Color.blue);
        add(text1);
        add(text2);
        th1 = new MyThread(text1);
        th2 = new MyThread(text2);
        th1.start();
        th2.start();
        setSize(200, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    class MyThread extends Thread {
        protected JTextField textField;

        public MyThread(JTextField textField) {
            this.textField = textField;
        }

        @Override
        public void run() {
            while (true) {
                int a = (int) (Math.random() * 1000000);
                textField.setText(Integer.toString(a, 16));
                try {
                    sleep(200);

                } catch (Exception e) {

                }
            }
        }
    }
}
