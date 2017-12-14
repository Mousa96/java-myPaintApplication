

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
	Rectangle( int x1, int y1, int x2, int y2 , boolean fill , Color c) {
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.fill=fill;
		this.c=c;

	}

	@Override
	public void drawOrFill(Graphics g) {
		// TODO Auto-generated method stub
		if (this.isFill()) {
			int startingX = Math.min(x1, x2);
			int startingY = Math.min(y1, y2);
			int width = Math.abs(x1 - x2);
			int height = Math.abs(y1 - y2);
			g.setColor(c);

			g.fillRect(startingX, startingY, width, height);
		}
		if (this.isFill() == false) {
			int startingX = Math.min(x1, x2);
			int startingY = Math.min(y1, y2);
			int width = Math.abs(x1 - x2);
			int height = Math.abs(y1 - y2);
			g.setColor(c);

			g.drawRect(startingX, startingY, width, height);
		}
	}
}
