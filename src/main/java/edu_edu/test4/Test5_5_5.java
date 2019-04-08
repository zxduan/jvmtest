package edu_edu.test4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 程序运行时，呈现的界面中菜单条有哪些菜单：体育，娱乐，学习
 */
public class Test5_5_5 {

    public static void main(String[] args) throws Exception {
        String[][] menuList = {
                {"体育", "跑步", "打篮球", "打乒乓球"},
                {"娱乐", "唱歌", "跳舞"},
                {"学习", "数学", "语文"}
        };
        MenuWindow menuWindow = new MenuWindow("菜单实例程序", menuList);
        menuWindow.setVisible(true);
        menuWindow.pack();
    }

    static class MenuWindow extends JFrame implements ActionListener {
        JTextField textField;

        public MenuWindow(String s, String[][] menuList) {
            setTitle(s);
            Container container = getContentPane();
            container.setLayout(new BorderLayout());
            setLocation(100, 100);
            setSize(300, 100);
            JMenuBar menuBar = new JMenuBar();
            for (int i = 0; i < menuList.length; i++) {
                JMenu menu = new JMenu(menuList[i][0]);
                for (int j = 1; j < menuList[i].length; j++) {
                    JMenuItem item = new JMenuItem(menuList[i][j]);
                    item.addActionListener(this);
                    menu.add(item);
                }
                menuBar.add(menu);
            }
            textField = new JTextField();
            setJMenuBar(menuBar);
            container.add(textField, BorderLayout.SOUTH);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            textField.setText("<" + e.getActionCommand() + ">菜单项被选择！");
        }
    }
}
