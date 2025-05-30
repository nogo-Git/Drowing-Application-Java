import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public class MyDrawing {
	private int x, y, w, h;	// X座標，Y座標，幅，高さ
	private Color lineColor, fillColor;	// 線の色，塗り色
	private int lineWidth;	// 線の太さ
	private boolean isShadow = false; // 影を付けるかどうか
	private boolean isDashed = false; // 破線であるかどうか
	boolean isSelected;
	Shape region;
	final int SIZE = 7;
	
	public MyDrawing(int x, int y, int w, int h, Color lineColor, Color fillColor, int lineWidth) {
		setLocation(x, y);
		setSize(w, h);
		setColor(lineColor, fillColor);
		setLineWidth(lineWidth);
		setRegion();
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		// 選択状態を表す四角形を描く
		if (isSelected) {
			g.setColor(Color.black);
			g.fillRect(x+w/2-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w/2-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y+h/2-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y-SIZE/2, SIZE, SIZE);
			g.fillRect(x-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
			g.fillRect(x+w-SIZE/2, y+h-SIZE/2, SIZE, SIZE);
		}
		
		if (isDashed)
			g2.setStroke(new MyDashStroke(getLineWidth()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
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
	
	public void setShadow(boolean b) {
		isShadow = b;
	}
	
	public void setDashed(boolean b) {
		// 破線状態の切り替え
		isDashed = b;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
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
	
	public boolean getShadow() {
		return isShadow;
	}
	
	public boolean getDashed() {
		return isDashed;
	}
	
	public boolean getSelected() {
		return isSelected;
	}
	
	// 包含判定用のメソッド
	public boolean contains(int x, int y) {
		// MyDrawingsを継承する子クラス内でそれぞれ定義する．
		// 包含判定図形が矩形ならば，例えば，
		return region.contains(x, y);
	}
	
	public void setRegion() {
		// MyDrawingを継承する子クラス内でそれぞれ定義する．
		// 包含判定図形が矩形ならば，例えば，
		region = new Rectangle(x,y,w,h);
	}
}
