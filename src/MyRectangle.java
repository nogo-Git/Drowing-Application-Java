import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MyRectangle extends MyDrawing {
	public MyRectangle(int xpt, int ypt) {
		this(xpt, ypt, 80, 80);
	}
	
	public MyRectangle(int xpt, int ypt, int width, int height) {
		this(xpt, ypt, width, height, Color.black, Color.white);
	}
	
	public MyRectangle(int xpt, int ypt, int width, int height, Color lineColor, Color fillColor) {
		this(xpt, ypt, width, height, lineColor, fillColor, 2);
	}
	
	public MyRectangle(int xpt, int ypt, int width, int height, Color lineColor, Color fillColor, int lineWidth) {
		super(xpt, ypt, width, height, lineColor, fillColor, lineWidth);
	}
	
	public void draw(Graphics g) {
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
		// 高さや横幅が負の時のための処理
		if(w < 0) {
			x += w;
			w *= -1;
		}
		if(h < 0) {
			y += h;
			h *= -1;
		}
		
		Graphics2D g2 = (Graphics2D) g;
		
		// 破線か否かでストロークを変更
		if (getDashed())
			g2.setStroke(new MyDashStroke(getLineWidth()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
		g2.setColor(getFillColor());
		g2.fillRect(x, y, w, h);
		g2.setColor(getLineColor());
		g2.drawRect(x, y, w, h);
	}
}
