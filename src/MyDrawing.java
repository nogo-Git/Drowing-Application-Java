import java.awt.Color;
import java.awt.Graphics;

public class MyDrawing {
	private int x, y, w, h;	// X座標，Y座標，幅，高さ
	private Color lineColor, fillColor;	// 線の色，塗り色
	private int lineWidth;	// 線の太さ
		
	public MyDrawing() {
		x = y = 0;
		w = 80;
		h = 80;
		lineColor = Color.black;
		fillColor = Color.white;
		lineWidth = 1;
	}
	
	public void draw(Graphics g) {
		
	}

	public void move(int dx, int dy) {
		// オブジェクトを移動する処理を書く
		x += dx;
		y += dy;
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
}
