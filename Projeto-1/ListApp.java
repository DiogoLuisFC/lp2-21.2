import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
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
// class ButtonColor{
//     ButtonColor(){
//         JButton BtnColor = new JButton();
//         BtnColor.setBounds(100,100,100, 40);    
//     }
// }

class ChangeColorFrame extends JDialog{
    ChangeColorFrame(int x, int y){
        this.setResizable(false);
        // this.setModal(true);
        this.setTitle("Selecionar cor");
        this.setSize(150, 150);
        this.setLocation(x+300 ,y-40);

        JButton BtnColor = new JButton("Confirmar");
        this.setLayout(null);
        BtnColor.setBounds(10,75,115,25); 
        this.add(BtnColor);
       
    }
}

class ListFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    ArrayList<Button> buts = new ArrayList<Button>();

    int[] pixels = new int[16 * 16];
    Image image = Toolkit.getDefaultToolkit().createImage(
        new MemoryImageSource(16, 16, pixels, 0, 16));
    Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(
        image, new Point(0, 0), "invisibleCursor");

    Figure focus = null;
    Button focus_but = null;
    int x_cursor;
    int y_cursor;
    int xf;
    int yf;
    boolean r = false;
    boolean duplicate = false;
    Color color;
    Color tempColor;
    boolean f = false;
    String side = "n";
    
    
    @SuppressWarnings("unchecked")
    ListFrame () {
        try {
            FileInputStream f = new FileInputStream("proj.bin");
            ObjectInputStream o = new ObjectInputStream(f);
            
            this.figs = (ArrayList<Figure>) o.readObject() ;
            o.close();
        } catch (Exception e) {
            System.out.println("Erro");
        }


        this.setFocusTraversalKeysEnabled(false);
        buts.add(new Button(0,new Ellipse(0,0,0,0)));
        buts.add(new Button(1,new Line(0,0,0,0)));
        buts.add(new Button(2,new Rect(0,0,0,0)));
        buts.add(new Button(3,new Triang(0,0,0,0)));


        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    try {
                        FileOutputStream f = new FileOutputStream("proj.bin");
                        ObjectOutputStream o = new ObjectOutputStream(f);
                        o.writeObject(figs);
                        o.flush();
                        o.close();
                    } catch (Exception e2) {
                    }
                    System.exit(0);
                }
            }
        );
        this.addMouseListener(
            new MouseAdapter(){
                public void mousePressed(MouseEvent click){
                    f = false;
                    for(Button but: buts) {
                        if (but.clicked(click.getX(), click.getY())){
                            if(focus_but == but){
                                focus_but = null;
                                f = false;
                            }else{
                                focus_but = but;
                                f = true;  
                            }            
                        }       
                    }
                    
                    if(focus_but != null && f==false){
                        int w = 70;
                        int h = 60;
                        int x = x_cursor - w/2;
                        int y = y_cursor - h/2;

                        if(focus_but.idx==0){
                            Ellipse e = new Ellipse(x,y, w,h);
                            figs.add(e);    
                        }
                        if(focus_but.idx==1){
                            Line l = new Line(x,y, w,h);
                            figs.add(l);       
                        }
                        if(focus_but.idx==2){
                            Rect r = new Rect(x,y, w,h);
                            figs.add(r);  
                        }
                        if(focus_but.idx==3){
                            Triang t = new Triang(x,y, w,h);
                            figs.add(t);       
                        }
                        focus_but = null;
                    }    
                    //////////////////////////////////

                    // if(duplicate){
                        
                        
                    //     if (focus) {
                    //         Rect d = new Rect(click.getX()-focus.w/2, click.getY()-focus.h/2,focus.w,focus.h);
                    //         d.colorBG = focus.colorBG;
                        
                    //     figs.add(d);
                    //     }
                       
                    
                        
                    //     focus=null;
                    //     duplicate=false;
            
                    // }

                    if (!f) focus = null;
        			for(Figure fig: figs) {
                        if (fig.clicked(click.getX(), click.getY())){                 
                            focus = fig;
                            xf =click.getX() - fig.x;
                            yf =click.getY() - fig.y;
                        }
                    }
                    
                    if (focus!= null) {
                        // System.out.println(focus.getClass());
                        figs.remove(focus);
                        figs.add(focus);

                        r = false;
                        if(focus.focusToRize(click.getX(), click.getY()) != "n"){
                            r = true;
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
                    
                    if(focus!= null && focus.clicked(x_cursor,y_cursor)){
                        
                        side = focus.focusToRize(x_cursor,y_cursor);
                        // System.out.println(side);
                        if(side=="r" || side=="l"){
                            setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));
                        }else if(side=="t" || side=="b"){
                            setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
                        }else if(side=="lt" || side=="rb"){
                            setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
                        }else if(side=="tr" || side=="bl"){
                            setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));
                        }else{
                            setCursor(new Cursor(Cursor.MOVE_CURSOR));
                        }   

                    }else{
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    } 
                }
                public void mouseDragged(MouseEvent evt) {
                    if (focus != null && r == false && f==false) {    
                        
                        if (evt.getX()< buts.get(3).fig.x+buts.get(3).fig.w+15 && evt.getY()< buts.get(3).fig.y+buts.get(3).fig.h+15){                 
                            setCursor(transparentCursor);
                        }else{
                            setCursor(new Cursor(Cursor.MOVE_CURSOR));
                        }
                        focus.drag(evt.getX(),evt.getY(),xf,yf); 
                        repaint(); 
                        
                    }
                    else if(focus != null && f==false){
                        focus.resize(evt.getX(),evt.getY(), side);
                        repaint();
                    }                  
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
                        focus = r;
                        figs.add(r);                        
                    }
                    else if (evt.getKeyChar() == 'e') {
                        Ellipse e = new Ellipse(x,y, w,h);
                        focus = e;
                        figs.add(e);                    
                    }
                    else if (evt.getKeyChar() == 't') {
                        Triang t = new Triang(x,y, w,h);
                        focus = t;
                        figs.add(t);                     
                    }
                    else if (evt.getKeyChar() == 'l') {
                        Line l = new Line(x,y, w,h);
                        focus = l;
                        figs.add(l);                  
                    }
                    // else if (evt.getKeyChar() == 'd' && focus!=null) {
                    //     duplicate = true;
                    // }
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
                            // focus.color= Color.black;
                            focus = figs.get(i);
                            // focus.color= Color.red;
                            figs.remove(focus);
                            figs.add(focus);
                            repaint();
                        }
                    }else if(evt.getKeyCode() == KeyEvent.VK_C && focus!=null){
                        // ChangeColorFrame ColorFrame = new ChangeColorFrame(focus.x,focus.y+focus.h);
                        // ColorFrame.setVisible(true);
                        
                        tempColor = focus.colorBG;
                        color = JColorChooser.showDialog( ListFrame.this,"Selecione uma cor", focus.colorBG );
                        if(color==null){
                            focus.colorBG = tempColor;
                        }else{
                            focus.colorBG = color;
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
            f.paint(g,f==focus);
        }

        for (Button but: this.buts) {
            but.paint(g,but==focus_but);
        }
    }
}
