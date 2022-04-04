package figures;

import java.awt.*;

public class Rect extends Figure {
    
    public Rect (int x, int y, int w, int h) {
        super(x,y, w,h);
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // GradientPaint paint = new GradientPaint(0,0,Color.ORANGE,420,0,Color.CYAN);
        // g2d.setPaint(paint);
        g2d.setColor(Color.gray);
        g2d.fillRect(this.x,this.y, this.w,this.h);
        g2d.setColor(Color.black);
        g2d.drawRect(this.x,this.y, this.w,this.h);
       
        if(color==Color.red){
            g2d.setColor(color);
            g2d.drawRect(this.x-1, this.y-1, this.w+2, this.h+2);
        }

    }
}
