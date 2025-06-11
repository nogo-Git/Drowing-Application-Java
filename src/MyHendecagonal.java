import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class MyHendecagonal extends MyDrawing {
	// 頂点座標を保持するフィールド
	private int[] xPoints;
	private int[] yPoints;
	private final int numPoints = 11;
	
	public MyHendecagonal(int xpt, int ypt) {
		this(xpt, ypt, 80, 80);
	}
	
	public MyHendecagonal(int xpt, int ypt, int width, int height) {
		this(xpt, ypt, width, height, Color.black, Color.white);
	}
	
	public MyHendecagonal(int xpt, int ypt, int width, int height, Color lineColor, Color fillColor) {
		this(xpt, ypt, width, height, lineColor, fillColor, 2);
	}
	
	public MyHendecagonal(int xpt, int ypt, int width, int height, Color lineColor, Color fillColor, int lineWidth) {
		super(xpt, ypt, width, height, lineColor, fillColor, lineWidth);
	}
	
	// 頂点座標を計算する
	private void calculateVertices() {
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

		// 中心座標と半径
		int centerX = x + w / 2;
		int centerY = y + h / 2;
		int radius = Math.min(w, h) / 2;

		// 頂点配列が未作成であれば初期化
		if (xPoints == null || xPoints.length != numPoints) {
			xPoints = new int[numPoints];
			yPoints = new int[numPoints];
		}

		// 頂点座標の計算
		for (int i = 0; i < numPoints; i++) {
			double angle = 2 * Math.PI * i / numPoints - Math.PI / 2;  // 上を向くように調整
			xPoints[i] = centerX + (int)(radius * Math.cos(angle));
			yPoints[i] = centerY + (int)(radius * Math.sin(angle));
		}
	}
	
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		calculateVertices();
		
		Graphics2D g2 = (Graphics2D) g;
		
		if (getShadow()) {
			int[] xShadowPoints = new int[numPoints];
			int[] yShadowPoints = new int[numPoints];
			
			g2.setColor(Color.black);
			for (int i = 0; i < numPoints; i++) {
				xShadowPoints[i] = xPoints[i] + 5;
				yShadowPoints[i] = yPoints[i] + 5;
			}
			g2.fillPolygon(xShadowPoints, yShadowPoints, numPoints);
		}
		
		g2.setColor(getFillColor());
		g2.fillPolygon(xPoints, yPoints, numPoints);
		g2.setColor(getLineColor());
		g2.drawPolygon(xPoints, yPoints, numPoints);
	}
	
	@Override
	public void setRegion() {
		calculateVertices();
		
		region = new Polygon(xPoints, yPoints, numPoints);
	}
}
