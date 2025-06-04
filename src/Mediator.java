import java.util.Enumeration;
import java.util.Vector;

public class Mediator {
	Vector<MyDrawing> drawings;
	MyCanvas canvas;
	MyDrawing selectedDrawing = null;
	
	public Mediator(MyCanvas canvas) {
		this.canvas = canvas;
		drawings = new Vector<MyDrawing>();
	}
	
	public Enumeration<MyDrawing> drawingsElements() {
		return drawings.elements();
	}
	
	public void addDrawing(MyDrawing d) {
		drawings.add(d);
		setSelectedDrawing(d);
	}
	
	public void removeDrawing(MyDrawing d) {
		drawings.remove(d);
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
	
	public void setSelected(int x, int y) {
		for (int i = 0; i < drawings.size(); i++) {
			drawings.get(i).setSelected(false);
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
