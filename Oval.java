
import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Shape {

	public Oval(int x1, int y1, int x2, int y2, boolean fill, Color c) {
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
			int width = Math.abs(x1 - x2);
			int height = Math.abs(y1 - y2);
			g.setColor(c);

			g.fillOval(x1, y1, width, height);
		}
		if (this.isFill() == false) {
			int width = Math.abs(x1 - x2);
			int height = Math.abs(y1 - y2);
			g.setColor(c);

			g.drawOval(x1, y1, width, height);
		}

	}

}
