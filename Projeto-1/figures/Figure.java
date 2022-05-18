package figures;
import java.io.Serializable;
import java.awt.Color;
import ivisible.IVisible;

public abstract class Figure implements IVisible, Serializable  {
    public int x, y;
    public int w, h;
    // public Color color = Color.black;
    public Color colorBG;
    public boolean rotate;


    protected Figure (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void drag (int dx, int dy, int xf, int yf) {
        
        this.x = (dx - xf);
        this.y = (dy - yf) ;
    }

    // public abstract void paint (Graphics g);//

    public boolean clicked (int x, int y) {
        return (this.x <= x && (this.x + this.w) >= x && this.y <= y && (this.y + this.h) >= y );       				
    }

    public boolean focusToRize (int x, int y) {
                
        // if ( (x == this.x || x == this.x + this.w) && (y >= this.y && y<= (this.y + this.h)) ){
        //     return 1;
        // }else if((y == this.y || y == this.y + this.h) && (x >= this.x && x<= (this.x + this.w))){
        //     return 2;
        // }else{
		// return 0;	
        // }
    //    return(x == this.x + this.w && y == this.y + this.h);
    return(x >= this.x + this.w - 5 && x <= this.x + this.w && y >= this.y + this.h - 5 && y <= this.y + this.h);

    }

    public void resize (int dx, int dy){
        this.w = dx - this.x;
        this.h = dy - this.y;
    }
    // public void rotate(Graphics g){
    //     Graphics2D g2d = (Graphics2D) g;
        
     
    // }

}
