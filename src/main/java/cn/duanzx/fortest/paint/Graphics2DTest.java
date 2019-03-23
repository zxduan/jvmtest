package cn.duanzx.fortest.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Graphics2DTest extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setColor(Color.red);
        Rectangle2D rec = new Rectangle2D.Double(10,30,50,50);
        graphics2D.draw(rec);
    }
}
