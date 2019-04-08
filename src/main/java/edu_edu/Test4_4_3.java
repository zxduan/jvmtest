package edu_edu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 以下定义的类是一个面板的子类，它能响应鼠标点击事件，当鼠标在面板的某个位置被点击时，就在该位置显示一个圆圈
 * 限制最多显示最新的m个圆，值m由创建该面板对象时指定
 */
public class Test4_4_3 extends JPanel implements MouseListener {

    public static void main(String[] args) throws Exception {
        JFrame jFrame = new JFrame("窗口");
        jFrame.setSize(500, 500);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().add(new Test4_4_3(10));
        jFrame.setVisible(true);
    }

    int maxMarks;
    int currentMarks, markCount;
    Point marks[];

    public Test4_4_3(int m) {
        maxMarks = m;
        currentMarks = markCount = 0;
        marks = new Point[maxMarks];
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int i, x, y;
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        for (i = 0; i < markCount; i++) {
            x = marks[i].x;
            y = marks[i].y;
            g.setColor(Color.red);
            g.drawOval(x, y, 50, 50);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        marks[currentMarks] = new Point(e.getX(), e.getY());//获取最新的位置
        if (markCount < maxMarks) {
            markCount++;
        }
        currentMarks = (currentMarks + 1) % maxMarks;//如果大于m,则将第一个圆圈的坐标覆盖
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
