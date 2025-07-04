import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;

public class MyDrawing implements Cloneable, Serializable{
	private int x, y, w, h;	// X座標，Y座標，幅，高さ
	private Color lineColor, fillColor;	// 線の色，塗り色
	private int lineWidth;	// 線の太さ
	private boolean isShadow = false; // 影を付けるかどうか
	private boolean isDashed = false; // 破線であるかどうか
	transient boolean isSelected;
	 Shape region;
	private int SIZE;
	
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
			if (region != null) {
				Rectangle bounds = region.getBounds();
				
				SIZE = lineWidth * 2;
				g.setColor(Color.black);
				g.fillRect(bounds.x+bounds.width/2-SIZE/2, bounds.y-SIZE/2, SIZE, SIZE);
				g.fillRect(bounds.x-SIZE/2, bounds.y+bounds.height/2-SIZE/2, SIZE, SIZE);
				g.fillRect(bounds.x+bounds.width/2-SIZE/2, bounds.y+bounds.height-SIZE/2, SIZE, SIZE);
				g.fillRect(bounds.x+bounds.width-SIZE/2, bounds.y+bounds.height/2-SIZE/2, SIZE, SIZE);
				g.fillRect(bounds.x-SIZE/2, bounds.y-SIZE/2, SIZE, SIZE);
				g.fillRect(bounds.x+bounds.width-SIZE/2, bounds.y-SIZE/2, SIZE, SIZE);
				g.fillRect(bounds.x-SIZE/2, bounds.y+bounds.height-SIZE/2, SIZE, SIZE);
				g.fillRect(bounds.x+bounds.width-SIZE/2, bounds.y+bounds.height-SIZE/2, SIZE, SIZE);
			}
		}
		
		if (isDashed)
			g2.setStroke(new MyDashStroke(getLineWidth()));
		else
			g2.setStroke(new BasicStroke(getLineWidth()));
	}

	public void move(int dx, int dy) {
		// オブジェクトを移動する処理を書く
		this.x += dx;
		this.y += dy;
		setRegion();
	}
	
	@Override
	public MyDrawing clone() {
		MyDrawing clone = null;
		
		try {
			clone = (MyDrawing)super.clone();
		}catch (Exception e) {
			e.printStackTrace();
		}
		clone.isSelected = false;
		return clone;
	}
	
	// setter ---------------------------------------
	public void setX(int x) {
		this.x = x;
		setRegion();
	}

	public void setY(int y) {
		this.y = y;
		setRegion();
	}

	public void setW(int w) {
		this.w = w;
		setRegion();
	}

	public void setH(int h) {
		this.h = h;
		setRegion();
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
		setRegion();
	}
	
	public void setSize(int w, int h) {
		// オブジェクトのサイズを変更する処理を書く
		this.w = w;
		this.h = h;
		setRegion();
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
		// 高さや横幅が負の時のための処理
		int regionX = x;
		int regionY = y;
		int regionW = w;
		int regionH = h;
		
		if (regionW < 0) {
			regionX += regionW;
			regionW *= -1;
		}
		if (regionH < 0) {
			regionY += regionH;
			regionH *= -1;
		}
		// MyDrawingを継承する子クラス内でそれぞれ定義する．
		// 包含判定図形が矩形ならば，例えば，
		region = new Rectangle(regionX, regionY, regionW, regionH);
	}
}
