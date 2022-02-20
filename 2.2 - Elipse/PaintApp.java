import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;

class PaintApp {
    public static void main (String[] args) {
        PaintFrame frame = new PaintFrame();
        frame.setVisible(true);
    }
}

class PaintFrame extends JFrame {
    Ellipse  e1,e2,e3;

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
        this.e1 = new Ellipse(50,50, 100,30,255,0,0,255,0,0);
        this.e2 = new Ellipse(125,125, 100,30,0,255,0,0,255,0);
        this.e3 = new Ellipse(200,200, 100,30,0,0,255,0,0,255);    

    }

    public void paint (Graphics g) {
        super.paint(g);
        this.e1.paint(g);
        this.e2.paint(g);
        this.e3.paint(g);
    }
}

class Ellipse {
    int x, y;
    int w, h;
    int rc,gc,bc;
    int rf,gf,bf;

    Ellipse (int x, int y, int w, int h, int rc, int gc , int bc , int rf , int gf , int bf) {
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

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(this.rc,this.gc,this.bc));
        g2d.draw(new Ellipse2D.Double(this.x , this.y , this.w, this.h ));
        g2d.setColor(new Color(this.rf,this.gf,this.bf));
        g2d.fill( new Ellipse2D.Double(this.x,this.y, this.w,this.h));
    }
}
