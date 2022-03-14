package figures;

import java.awt.*;

public class Triang {
    int x1, y1;
    int x2, y2;
    int x3, y3;

    public Triang (int x1, int y1, int x2, int y2, int x3, int y3 ) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }    
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint paint = new GradientPaint(420,0,Color.GREEN,0,0,Color.white);

        int[] xPoints = {this.x1,this.x2,this.x3};
        int[] yPoints = {this.y1,this.y2,this.y3};
        
        g2d.setPaint(paint);
        g2d.drawPolygon(xPoints, yPoints, 3);
        g2d.fillPolygon(xPoints, yPoints, 3);
        g2d.setPaint(Color.black);

    }
}