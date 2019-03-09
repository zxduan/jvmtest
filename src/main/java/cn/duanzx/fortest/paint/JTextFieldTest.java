package cn.duanzx.fortest.paint;

import javax.swing.*;
import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 在第一个文本框中输入整数，输入回车后，在第二个文本框中输出该整数和平方
 * 1.创建两个文本框 JTestField
 * 2.将两个文本框添加到Applet中
 * 3.为其中一个文本框添加事件，addActionListener
 * */
public class JTextFieldTest extends JFrame implements ActionListener {
    JPanel jPanel ;
    JTextField text1,text2;
    JLabel text1Label,text2Label;
    public void init(){
        jPanel = new JPanel();
        jPanel.setBounds(200,200,200,200);
        text1Label = new JLabel("文本框1");
        text1 = new JTextField(10);
        text1.addActionListener(this);
        jPanel.add(text1Label);
        jPanel.add(text1);
        text2Label = new JLabel("文本框2");
        text2 = new JTextField(10);
        jPanel.add(text2Label);
        jPanel.add(text2);
        add(jPanel);
        setBounds(400,400,400,400);
        setVisible(true);
    }

    public static void main(String[] args)throws Exception{
        new JTextFieldTest().init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        long n ;
        if(e.getSource() == text1){
            n = Long.parseLong(text1.getText());
            text2.setText(String.valueOf(n * n));
        }
    }
}
