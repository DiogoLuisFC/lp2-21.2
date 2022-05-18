package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
//import java.awt.geom.Ellipse2D.Double;

public class Ellipse extends Figure {
    public Ellipse (int x, int y, int w, int h) {
        super(x,y, w,h);
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;
        // GradientPaint paint = new GradientPaint(200,100,Color.PINK,100,0,Color.BLUE);
        
        // g2d.setPaint(paint);
        if(colorBG==null){
            colorBG = Color.lightGray;
        }
        g2d.setColor(colorBG);
        g2d.fill(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
        g2d.setColor(Color.black);
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
   
        // if(color==Color.red){
        //     g2d.setColor(color);
        //     g2d.drawRect(this.x-1, this.y-1, this.w+2, this.h+2);
        // }
        if(focused){
            g2d.setColor(Color.red);
            g2d.drawRect(this.x-1, this.y-1, this.w+2, this.h+2);
        }
    }
}
