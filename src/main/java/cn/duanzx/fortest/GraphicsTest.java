package cn.duanzx.fortest;

import java.applet.Applet;
import java.awt.*;

public class GraphicsTest extends Applet {
    public GraphicsTest(){
        setSize(500,500);
        setBackground(Color.gray);
        setVisible(true);
    }
   public void paint(Graphics g){
       g.setColor(Color.red);
       g.setXORMode(Color.blue);
       int x = 0,y=0;
       while (true){
           g.drawArc(50,50,200,200,x,1);
           x++;
       }
   }
    public static void main(String[] args)throws Exception{
        new GraphicsTest().repaint();
    }
}
