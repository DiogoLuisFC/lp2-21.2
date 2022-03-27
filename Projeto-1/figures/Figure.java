package figures;

import java.awt.Graphics;
import java.awt.Color;


public abstract class Figure {
    public int x, y;
    public int w, h;
    public Color color = Color.black;


    public Figure (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void drag (int dx, int dy) {
        this.x = dx - (this.w/2);
        this.y = dy - (this.h/2);
    }

    public abstract void paint (Graphics g);

    public boolean clicked (int x, int y) {
        return (this.x <= x && (this.x + this.w) >= x && this.y <= y && (this.y + this.h) >= y );       				
    }
}