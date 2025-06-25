import java.util.Enumeration;
import java.util.Vector;

public class Mediator {
	private Vector<MyDrawing> drawings;
	private MyCanvas canvas;
	private Vector<MyDrawing> selectedDrawings;
	private Vector<MyDrawing> buffers; // Cut, Copyバッファ
	
	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
		selectedDrawings = new Vector<MyDrawing>();
		buffers = new Vector<MyDrawing>();
	}
	
	public Vector<MyDrawing> getDrawings() {
		return drawings;
	}
	
	public void setDrawings(Vector<MyDrawing> drawings) {
		this.drawings = drawings;
		this.selectedDrawings.clear();
		repaint();
	}
	
	public Enumeration<MyDrawing> drawingsElements() {
		return drawings.elements();
	}
	
	public void addDrawing(MyDrawing d) {
		drawings.add(d);
	}
	
	public void clearBuffer()
    {
		buffers.clear();
    }

    public void copy()
    {
        clearBuffer();
        for (MyDrawing d : selectedDrawings) {
        	buffers.add(d.clone());
        }
    }

    public void cut()
    {
    	copy();
    	for(MyDrawing d : selectedDrawings) {
    		drawings.remove(d);
    	}
    	selectedDrawings.clear();
    	repaint();
    }
    
    public void paste(int x, int y)
    {
    	if (buffers != null && !buffers.isEmpty()) {
    		// 貼り付け位置の基準となる左上の座標を計算
    		int minX = Integer.MAX_VALUE;
    		int minY = Integer.MAX_VALUE;
    		for (MyDrawing d : buffers) {
    			if (d.getX() < minX) minX = d.getX();
    			if (d.getY() < minY) minY = d.getY();
    		}
    		
    		// 相対位置を保ったまま貼り付け
    		for (MyDrawing d : buffers) {
    			MyDrawing clone = d.clone();
    			clone.setLocation(x + (d.getX() - minX), y + (d.getY() - minY));
    			addDrawing(clone);
    		}
    		repaint();
    	}
    }

	
	public void removeDrawing(MyDrawing d) {
		if (d != null) {
			drawings.remove(d);
			if(selectedDrawings != null) {
				selectedDrawings.remove(d);
			}
		}
		repaint();
	}

	public Vector<MyDrawing> getSelectedDrawing() {
		return selectedDrawings;
	}
	
	public void repaint() {
		canvas.repaint();
	}
	
	public void setSelectedDrawing(MyDrawing selectedDrawing) {
		selectedDrawings.add(selectedDrawing);
	}
	
	public void setSelected(int x, int y) {
		for (MyDrawing d : selectedDrawings) {
			d.setSelected(false);
		}
		selectedDrawings.clear();
		
		for (int i = drawings.size() - 1; i >= 0; i--) {
			MyDrawing d = drawings.get(i);
			if (d.contains(x, y)) {
				setSelectedDrawing(d);
				d.setSelected(true);
				break;
			}
		}
	}
}
