package edu_edu.test4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 小应用程序有一个按钮和一个文本区，按钮作为发生键盘事件的事件源，并对键盘事件实施监视
 * 程序运行的时候，先点击按钮，让按钮获取焦点。以后输入英文字母时，在文本区显示输入的字母。
 */
public class Test4_4_4 extends JPanel implements KeyListener, ActionListener {

    public static void main(String[] args) throws Exception {
        JFrame jFrame = new JFrame();
        jFrame.setBounds(100, 100, 400, 200);
        jFrame.getContentPane().add(new Test4_4_4());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    JButton btn1 = new JButton("输入");
    JButton btn2 = new JButton("清空");
    JTextArea textArea = new JTextArea(5, 20);

    public Test4_4_4() {
        btn1.addKeyListener(this);
        btn2.addActionListener(this);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(1, 2));
        jPanel.add(btn1);
        jPanel.add(btn2);
        add(jPanel);
        add(textArea);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int t = e.getExtendedKeyCode();
        if (t >= KeyEvent.VK_A && t <= KeyEvent.VK_Z) {
            textArea.append((char) t + "");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.setText("");
    }
}
