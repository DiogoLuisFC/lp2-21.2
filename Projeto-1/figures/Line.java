package figures;
import java.awt.*;

public class Line extends Figure {
    public Line (int x, int y, int w, int h) {
        super(x,y, w,h);
    }

    public void print () {
        System.out.format("Linha de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g, boolean focused) {
        Graphics2D g2d = (Graphics2D) g;

        if(colorBG==null){
            colorBG = Color.black;
        }
        g2d.setColor(colorBG);
        g2d.drawLine(this.x, this.y, this.w + this.x, this.h + this.y);
   
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
