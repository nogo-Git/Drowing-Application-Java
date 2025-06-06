import java.awt.Color;
import java.util.Enumeration;
import java.util.Vector;

public class Mediator {
	Vector<MyDrawing> drawings;
	MyCanvas canvas;
	MyDrawing selectedDrawing = null;
	Color drawColor;
	MyDrawing buffer = null; // Cut, Copyバッファ
	
	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
	}
	
	public Enumeration<MyDrawing> drawingsElements() {
		return drawings.elements();
	}
	
	public void addDrawing(MyDrawing d) {
		drawings.add(d);
	}
	
	public void clearBuffer()
    {
        buffer = null;
    }

    public void copy()
    {
        // バッファをクリアする
        clearBuffer();
        buffer = selectedDrawing.clone();
    }

    public void cut()
    {
        // バッファをクリアする
        clearBuffer();
        buffer = selectedDrawing.clone();
        removeDrawing(selectedDrawing); // drawingsからselectedDrawingを削除。
    }
    
    public void paste(int x, int y)
    {
        MyDrawing clone = (MyDrawing)buffer.clone();
        clone.setLocation(x, y);
        addDrawing(clone);
        repaint();
    }

	
	public void removeDrawing(MyDrawing d) {
		if (d == selectedDrawing) {
			selectedDrawing = null;
		}
		drawings.remove(d);
	}
	
	public void deleteSelectedDrawing() {
		if (selectedDrawing != null) {
			// removeDrawingメソッドは、選択された図形が削除された場合に
			// selectedDrawingをnullにする処理を updateSelection(null) を通じて行う。
			MyDrawing drawingToDelete = selectedDrawing; // 先に参照を保持
			removeDrawing(drawingToDelete); // drawingsからの削除とselectedDrawingの更新
			repaint(); // 画面の再描画を指示
		}
	}
	
	
	public MyDrawing getSelectedDrawing() {
		return selectedDrawing;
	}
	
	public void repaint() {
		canvas.repaint();
	}
	
	public void setSelectedDrawing(MyDrawing selectedDrawing) {
		this.selectedDrawing = selectedDrawing;
	}
	
	public void setColor(Color color) {
		drawColor = color;
	}
	
	public void setSelected(int x, int y) {
		for (int i = 0; i < drawings.size(); i++) {
			drawings.get(i).setSelected(false);
			selectedDrawing = null;
		}
		MyDrawing tmpDrawing = null;
		for (int i = drawings.size() - 1; i >= 0; i--) {
			tmpDrawing = drawings.get(i);
			if (tmpDrawing.contains(x, y)) {
				setSelectedDrawing(tmpDrawing);
				tmpDrawing.setSelected(true);
				break;
			}
		}
	}
}
