package edu_edu.test4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.StringTokenizer;

/**
 * 创建一个窗口，采用BorderLayout布局，中间放置一个文本区，南部放置一个按钮
 * 同时给这个按钮添加监听事件，点击按钮把文本区中的内容写入到文件中
 */

public class Test4_5_4 extends JFrame implements ActionListener {

    public static void main(String[] args) throws Exception {
        new Test4_5_4();
    }

    private JTextArea textArea;
    private JButton button;
    private BufferedWriter out;

    public Test4_5_4() {
        super("测试");
        Container container = getContentPane();
        textArea = new JTextArea(10, 20);
        textArea.setBackground(Color.CYAN);
        button = new JButton("写入");
        button.addActionListener(this);
        container.setLayout(new BorderLayout());
        container.add(textArea, "Center");
        container.add(button, "South");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            try {
                out = new BufferedWriter(new FileWriter("D:\\WEB\\fromGithub\\jvmtest\\src\\main\\java\\edu_edu\\test4\\out.txt"));
                String s = textArea.getText();
                StringTokenizer tokens = new StringTokenizer(s);
                int n = tokens.countTokens();
                for (int i = 0; i < n; i++) {
                    String temp = tokens.nextToken();
                    out.write(temp + "\r\n");
                }
                out.flush();
                textArea.setText(null);
                System.exit(0);
            } catch (Exception ex) {
                textArea.setText("文件吸入错误!" + ex.getMessage());
            }
        }
    }
}
