

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{
	
	Line(int x1, int y1, int x2, int y2 , boolean fill , Color c) {
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.c=c;
	}

	@Override
	public void drawOrFill(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(c);
		g.drawLine(x1, y1, x2, y2);
		
	}

}
