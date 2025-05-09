import java.awt.Color;
import java.awt.Graphics;

public class MyDrawing {
	private int x, y, w, h;	// X座標，Y座標，幅，高さ
	private Color lineColor, fillColor;	// 線の色，塗り色
	private int lineWidth;	// 線の太さ
	
	private boolean isDashed = false; // 破線であるかどうか
	
	public MyDrawing(int x, int y, int w, int h, Color lineColor, Color fillColor, int lineWidth) {
		setLocation(x, y);
		setSize(w, h);
		setColor(lineColor, fillColor);
		setLineWidth(lineWidth);
	}

	public void draw(Graphics g) {
		
	}

	public void move(int dx, int dy) {
		// オブジェクトを移動する処理を書く
		x += dx;
		y += dy;
	}
	
	// setter ---------------------------------------
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setW(int w) {
		this.w = w;
	}

	public void setH(int h) {
		this.h = h;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	public void setLocation(int x, int y) {
		// オブジェクトの位置を変更する処理を書く
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int w, int h) {
		// オブジェクトのサイズを変更する処理を書く
		this.w = w;
		this.h = h;
	}
	
	public void setColor(Color lineColor, Color fillColor) {
		// オブジェクトの線の色と塗りつぶしの色を変更する処理
		this.lineColor = lineColor;
		this.fillColor = fillColor;
	}
	
	public void setDashed(boolean b) {
		// 破線状態の切り替え
		isDashed = b;
	}
	
	// getter ---------------------------------------
	public int getX() {
		// オブジェクトのX位置を取得する
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
	
	public Color getLineColor() {
		return lineColor;
	}
	
	public Color getFillColor() {
		return fillColor;
	}
	
	public int getLineWidth() {
		return lineWidth;
	}
	
	public boolean getDashed() {
		return isDashed;
	}
}
