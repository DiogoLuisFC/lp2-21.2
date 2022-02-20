import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Rect r1,r2,r3;

    PaintFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Painting Figures");
        this.setSize(350, 350);
        this.r1 = new Rect(50,50, 100,30,255,0,0,255,0,0);
        this.r2 = new Rect(125,125, 100,30,0,255,0,0,255,0);
        this.r3 = new Rect(200,200, 100,30,0,0,255,0,0,255);    

    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.r2.paint(g);
        this.r3.paint(g);
    }
}

class Rect {
    int x, y;
    int w, h;
    int rc,gc,bc;
    int rf,gf,bf;

    Rect (int x, int y, int w, int h, int rc, int gc , int bc , int rf , int gf , int bf) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        
        this.rc = rc;
        this.gc = gc;
        this.bc = bc;

        this.rf = rf;
        this.gf = gf;
        this.bf = bf;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rc,this.gc,this.bc));
        g2d.drawRect(this.x,this.y, this.w,this.h);
        g2d.setColor(new Color(this.rf,this.gf,this.bf));
        g2d.fillRect(this.x,this.y, this.w,this.h);
    }
}
