package cn.duanzx.fortest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickCountFrame extends JFrame implements ActionListener {

    JButton button ;
    JLabel label;
    ClickCountFrame(String s){
        super(s);
        Container container = getContentPane();
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,400);
        button = new JButton("OK");
        label = new JLabel("0");
        container.add(button);
        container.add(label);
        button.addActionListener(this);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int n = Integer.parseInt(label.getText());
        n++;
        label.setText(n+"");
    }
    public static void main(String[] args)throws Exception{
        new ClickCountFrame("我的窗口");
    }
}
