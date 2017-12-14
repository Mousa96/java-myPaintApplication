

import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {

	 int x1,x2,y1,y2;
	 boolean fill;
	Color c;
	
public Color getC() {
	return c;
}
public int getX1() {
	return x1;
}
public int getX2() {
	return x2;
}
public int getY1() {
	return y1;
}
public int getY2() {
	return y2;
}
public void setC(Color c) {
	this.c = c;
}
public boolean isFill() {
	return fill;
}
public void setFill(boolean fill) {
	this.fill = fill;
}
public void setX1(int x1) {
	this.x1 = x1;
}
public void setX2(int x2) {
	this.x2 = x2;
}
public void setY1(int y1) {
	this.y1 = y1;
}
public void setY2(int y2) {
	this.y2 = y2;
}
public abstract void drawOrFill(Graphics g );
	
	
}
