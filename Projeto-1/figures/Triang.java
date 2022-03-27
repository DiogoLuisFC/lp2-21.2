package figures;

import java.awt.*;

public class Triang extends Figure {

    public Triang (int x, int y, int w, int h) {
        super(x,y, w,h);
    }    
    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int[] xPoints = {this.x,this.x+this.w,this.x+(this.w/2)};
        int[] yPoints = {this.y+this.h,this.y+this.h,this.y};
        
        g2d.setColor(color);
        g2d.drawPolygon(xPoints, yPoints, 3);
        // g2d.fillPolygon(xPoints, yPoints, 3);
    }
}