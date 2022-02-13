import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DAppNovo {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
        
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D - Hello World!");
        this.setSize(350, 350);
        this.setVisible(true);
    }

    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.gray);
        //int w = getWidth();
        //int h = getHeight();
        // g2d.drawLine(0,0, w,h);
        //  g2d.drawLine(0,h, w,0);
        g2d.drawRect(130, 120, 100, 100);
        g2d.fillRect(130, 120, 100, 100); //
        g2d.setPaint(Color.black);
        g2d.drawRect(100, 150, 100, 100);
        g2d.drawLine(100, 150, 130, 120);
        g2d.drawLine(200, 150, 230, 120);
        g2d.drawLine(100, 250, 130, 220);
        g2d.drawLine(200, 250, 230, 220);
       
    }
}