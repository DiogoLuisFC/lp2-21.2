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

    public String focusToRize (int x, int y) {
                
        // if ( (x == this.x || x == this.x + this.w) && (y >= this.y && y<= (this.y + this.h)) ){
        //     return 1;
        // }else if((y == this.y || y == this.y + this.h) && (x >= this.x && x<= (this.x + this.w))){
        //     return 2;
        // }else{
		// return 0;	
        // }
    //    return(x == this.x + this.w && y == this.y + this.h);
    // return(x >= this.x + this.w - 5 && x <= this.x + this.w && y >= this.y + this.h - 5 && y <= this.y + this.h);
    if(x> this.x+this.w-5 && x< this.x+this.w+5 && y>this.y+5 && y<this.y + this.h - 5){
        return "r";

    }else if(x>this.x+5 && x<this.x+this.w-5 && y>this.y+this.h-5 && y<this.y+this.h+5){
        return "b";

    }else if(x>this.x-5 && x<this.x+5 && y>this.y+5 && y<this.y+this.h-5 ){
        return "l";

    }else if(x>this.x+5 && x<this.x+this.w-5 && y>this.y-5 && y<this.y+5){
        return "t";

    }else if(x >= this.x + this.w - 5 && x <= this.x + this.w && y >= this.y + this.h - 5 && y <= this.y + this.h){
        return "rb";

    }else if(x>=this.x && x<=this.x+5 && y>=this.y && y<=this.y+5){
        return "lt";

    }else if(x >= this.x + this.w - 5 && x <= this.x + this.w && y>=this.y && y<=this.y+5){
        return "tr";

    }else if(x>=this.x && x<=this.x+5 && y>=this.y+this.h-5 && y<=this.y+this.h){
        return "bl";
    }
    else{
        return "n";
    }

    }

    public void resize (int dx, int dy, String side){
        // this.w = dx - this.x;
        // this.h = dy - this.y;
        if(side=="r"){
            if(dx>=this.x)this.w = dx-this.x;
        }else if(side=="b"){
            if(dy>=this.y)this.h = dy - this.y;
        }else if(side=="l"){
            if(dx<=this.x+this.w){
                this.w= this.w+(this.x-dx);
                this.x=dx;
            }
        }else if(side=="t"){
            if(dy<=this.y+this.h){
                this.h= this.h+(this.y-dy);
                this.y=dy;
            } 
        }else if(side=="rb"){
            this.resize(dx, dy, "r");
            this.resize(dx, dy, "b");
        }else if(side=="lt"){
            this.resize(dx, dy, "l");
            this.resize(dx, dy, "t");
        }else if(side=="tr"){
            this.resize(dx, dy, "t");
            this.resize(dx, dy, "r");
        }else if(side=="bl"){
            this.resize(dx, dy, "b");
            this.resize(dx, dy, "l");
        }
    }
    // public void rotate(Graphics g){
    //     Graphics2D g2d = (Graphics2D) g;
        
     
    // }

}
