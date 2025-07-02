import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class MyOval extends MyDrawing {
	public MyOval(int xpt, int ypt) {
		this(xpt, ypt, 80, 80);
	}
	
	public MyOval(int xpt, int ypt, int width, int height) {
		this(xpt, ypt, width, height, Color.black, Color.white);
	}
	
	public MyOval(int xpt, int ypt, int width, int height, Color lineColor, Color fillColor) {
		this(xpt, ypt, width, height, lineColor, fillColor, 2);
	}
	
	public MyOval(int xpt, int ypt, int width, int height, Color lineColor, Color fillColor, int lineWidth) {
		super(xpt, ypt, width, height, lineColor, fillColor, lineWidth);
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		
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
		
		if (getShadow()) {
			g2.setColor(Color.black);
			g2.fillOval(x+5, y+5, w, h);
		}
		
		g2.setColor(getFillColor());
		g2.fillOval(x, y, w, h);
		g2.setColor(getLineColor());
		g2.drawOval(x, y, w, h);
	}
	
	@Override
	public void setRegion() {
		// 包含領域を、自身の形である楕円（Ellipse2D）に設定する
		int regionX = getX();
		int regionY = getY();
		int regionW = getW();
		int regionH = getH();
		
		if (regionW < 0) {
			regionX += regionW;
			regionW *= -1;
		}
		if (regionH < 0) {
			regionY += regionH;
			regionH *= -1;
		}
		region = new Ellipse2D.Double(regionX, regionY, regionW, regionH);
	}
}
