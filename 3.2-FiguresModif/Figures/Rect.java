package figures;

import java.awt.*;

public class Rect {
    int x, y;
    int w, h;

    public Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint paint = new GradientPaint(0,0,Color.ORANGE,420,0,Color.CYAN);
        
        g2d.setPaint(paint);
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.fillRect(this.x,this.y, this.w,this.h);
        g2d.setPaint(Color.black);

    }
}
