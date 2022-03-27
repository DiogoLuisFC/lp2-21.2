import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Random rand = new Random();
    Figure focus = null;
    int x_cursor;
    int y_cursor;

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.addMouseListener(
            new MouseAdapter(){
                public void mousePressed(MouseEvent click){
                    focus = null;
        			for(Figure fig: figs) {
                        if (fig.clicked(click.getX(), click.getY())){
                            focus = fig;
                            fig.color= Color.red;
                        }else{
                            fig.color= Color.black;
                        }
                    }
                    repaint();
                }
            }
        );
        this.addMouseMotionListener(
        	new MouseMotionAdapter() {
                public void mouseMoved(MouseEvent cursor) {
                    x_cursor =cursor.getX();
                    y_cursor=cursor.getY();              
                }
                public void mouseDragged(MouseEvent evt) {
                    if (focus != null) {
                        focus.drag(evt.getX(),evt.getY());
        	            repaint();
                    }
                }    
            }
            
        );
        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int x = x_cursor;
                    int y = y_cursor;
                    int w = rand.nextInt(50);
                    int h = rand.nextInt(50);
                    if (evt.getKeyChar() == 'r') {
                        Rect r = new Rect(x,y, w,h);
                        figs.add(r);                        
                    }
                    else if (evt.getKeyChar() == 'e') {
                        Ellipse e = new Ellipse(x,y, w,h);
                        figs.add(e);                    
                    }
                    else if (evt.getKeyChar() == 't') {
                        Triang t = new Triang(x,y, w,h);
                        figs.add(t);                     
                    }
                    else if (evt.getKeyChar() == 'l') {
                        Line l = new Line(x,y, w,h);
                        figs.add(l);                  
                    }
                    else if(evt.getKeyCode() == KeyEvent.VK_DELETE){
                        figs.remove(focus);
                        focus = null;
                    } 
                    repaint();

                }
            }
        );

        this.setTitle("Projeto-1");
        this.setSize(350, 350);
    }
    public void paint (Graphics g) {
        super.paint(g);
        for (Figure f: this.figs) {
            f.paint(g);
        }
    }
}
