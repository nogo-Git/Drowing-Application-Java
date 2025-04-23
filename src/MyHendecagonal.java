import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class MyHendecagonal extends MyDrawing {
	public MyHendecagonal(int xpt, int ypt) {
		super();
		setLocation(xpt, ypt);
	}
	
	public void draw(Graphics g) {
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

		// 頂点の数
		int numPoints = 11;
		int[] xPoints = new int[numPoints];
		int[] yPoints = new int[numPoints];

		// 頂点座標の計算
		for (int i = 0; i < numPoints; i++) {
			double angle = 2 * Math.PI * i / numPoints - Math.PI / 2;  // 上を向くように調整
			xPoints[i] = centerX + (int)(radius * Math.cos(angle));
			yPoints[i] = centerY + (int)(radius * Math.sin(angle));
		}

		Graphics2D g2 = (Graphics2D) g;
		Polygon hendecagon = new Polygon(xPoints, yPoints, numPoints);

		// 塗りつぶし
		g2.setColor(getFillColor());
		g2.fillPolygon(hendecagon);
		
		// 線の描画
		g2.setColor(getLineColor());
		g2.setStroke(new BasicStroke(getLineWidth()));
		g2.drawPolygon(hendecagon);
	}
}
