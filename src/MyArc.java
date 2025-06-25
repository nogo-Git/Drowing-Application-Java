import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MyArc extends MyDrawing {
	private int startAngle = 0;
	private int arcAngle = 180;
	
	public MyArc(int xpt, int ypt) {
		this(xpt, ypt, 80, 80);
	}
	
	public MyArc(int xpt, int ypt, int width, int height) {
		this(xpt, ypt, width, height, 0, 180);
	}

	public MyArc(int xpt, int ypt, int width, int height, int startAngle, int arcAngle) {
		this(xpt, ypt, width, height, startAngle, arcAngle, Color.black, Color.white);
	}
	
	public MyArc(int xpt, int ypt, int width, int height, int startAngle, int arcAngle, Color lineColor, Color fillColor) {
		this(xpt, ypt, width, height, startAngle, arcAngle, lineColor, fillColor, 2);
	}
	
	public MyArc(int xpt, int ypt, int width, int height, int startAngle, int arcAngle, Color lineColor, Color fillColor, int lineWidth) {
		super(xpt, ypt, width, height, lineColor, fillColor, lineWidth);
		this.startAngle  = startAngle;
		this.arcAngle = arcAngle;
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		
		int x = getX();
		int y = getY();
		int w = getW();
		int h = getH();
		
		// 高さや横幅が負の時のための処理
		if (w < 0) {
			x += w;
			w *= -1;
		}
		if (h < 0) {
			y += h;
			h *= -1;
		}
		
		Graphics2D g2 = (Graphics2D) g;
		
		if(getShadow()) {
			g2.setColor(Color.black);
			g2.fillArc(x+5, y+5, w, h, startAngle, arcAngle);
		}
		
		g2.setColor(getFillColor());
		g2.fillArc(x, y, w, h, startAngle, arcAngle);
		g2.setColor(getLineColor());
		g2.drawArc(x, y, w, h, startAngle, arcAngle);
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        // オブジェクトの全フィールド（親クラスのものも含む）を復元
        in.defaultReadObject();
        // 復元された値を元に、当たり判定領域を再設定する
        setRegion();
    }
	
	@Override
	public void setRegion() {
		// 包含領域を、自身の形である円弧（Arc2D）に設定する
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
		// fillArcで描画しているので、当たり判定もパイ（扇形）にする
		region = new Arc2D.Double(regionX, regionY, regionW, regionH, startAngle, arcAngle, Arc2D.PIE);
	}
}
