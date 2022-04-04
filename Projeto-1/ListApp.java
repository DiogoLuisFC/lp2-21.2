import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

// import java.util.Random;
import figures.*;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    // Random rand = new Random();
    Figure focus = null;
    int x_cursor;
    int y_cursor;
    int xf;
    int yf;
    boolean r = false;

    ListFrame () {
        this.setFocusTraversalKeysEnabled(false);
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
                            xf =click.getX() - fig.x;
                            yf =click.getY() - fig.y;
                            // setCursor(new Cursor(Cursor.MOVE_CURSOR));
                        }else{
                            fig.color= Color.black;
                        }
                    }
                     if (focus!= null) {
                        figs.remove(focus);
                        figs.add(focus);
                    }
                    if(focus.focusToRize(click.getX(), click.getY())){
                        r = true;
                    }else{
                        r = false;
                    }
                    // focus.focusToRize(click.getX(), click.getY())!=0
                    repaint();
                }
            }
        );
        this.addMouseMotionListener(
        	new MouseMotionAdapter() {
                public void mouseMoved(MouseEvent cursor) {
                    x_cursor =cursor.getX();
                    y_cursor=cursor.getY();
                    if(focus!= null && focus.clicked(x_cursor,y_cursor)){
                        
                        if(focus.focusToRize(x_cursor,y_cursor)==true){
                            setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                        }else{
                            setCursor(new Cursor(Cursor.MOVE_CURSOR));
                        }  

                    }else{
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    } 
                }
                public void mouseDragged(MouseEvent evt) {
                    if (focus != null && r == false) {    
                        focus.drag(evt.getX(),evt.getY(),xf,yf);    
                    }
                    else{
                        focus.resize(evt.getX(),evt.getY());
                    }
                    // focus.focusToRize(evt.getX(), evt.getY())==0
                    // focus.resize(evt.getX(),evt.getY());
                    repaint();
                }    
            }
            
        );
        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    int w = 70;
                    int h = 60;
                    int x = x_cursor - w/2;
                    int y = y_cursor - h/2;
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
                    else if(evt.getKeyCode() == KeyEvent.VK_UP){
                        focus.y = focus.y - 2;
                    } 
                    else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
                        focus.y = focus.y + 2;
                    } 
                    else if(evt.getKeyCode() == KeyEvent.VK_LEFT){
                        focus.x = focus.x - 2;
                    } 
                    else if(evt.getKeyCode() == KeyEvent.VK_RIGHT){
                        focus.x = focus.x + 2;
                    }
                    else if(evt.getKeyCode() == KeyEvent.VK_TAB){
                        if(focus != null){
                            int i;
                            if(figs.indexOf(focus)==figs.size()-1){
                                i = 0;
                            }else{
                                i = figs.indexOf(focus) + 1;
                            }
                            focus.color= Color.black;
                            focus = figs.get(i);
                            focus.color= Color.red;
                        }
                    }
                    repaint();

                }
            }
        );

        this.setTitle("Projeto-1");
        this.setSize(500, 500);
        this.setLocation(450, 100);
    }
    public void paint (Graphics g) {
        super.paint(g);
        for (Figure f: this.figs) {
            f.paint(g);
        }
    }
}
