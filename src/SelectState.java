import java.awt.Color;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.util.Vector;

public class SelectState extends State {
	private StateManager stateManager;
	private int x1, y1;
	private MyRectangle selectionRect;
	private boolean isDraggingShape;
	
	public SelectState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	@Override
	public void mouseDown(int x, int y) {
		x1 = x;
		y1 = y;
		this.selectionRect = null;
		this.isDraggingShape = false;
		
		Mediator mediator = stateManager.getMediator();
		Vector<MyDrawing> selectedDrawings = mediator.getSelectedDrawing();
		
		boolean onSelectedDrawing = false;
		for (MyDrawing d : selectedDrawings) {
			if (d.contains(x, y)) {
				onSelectedDrawing = true;
				break;
			}
		}
		
		if (onSelectedDrawing) {
			this.isDraggingShape = true;
		} else {
			mediator.setSelected(x, y);
			// 図形が選択されたかチェック
			if (!mediator.getSelectedDrawing().isEmpty()) {
				this.isDraggingShape = true;
			} else {
				selectionRect = new MyRectangle(x, y, 0, 0);
				selectionRect.setColor(Color.black, new Color(0, 0, 0, 0));
				selectionRect.setDashed(true);
				mediator.addDrawing(selectionRect);
			}
		}
	}

	@Override
	public void mouseDrag(int x, int y) {
		Mediator mediator = stateManager.getMediator();
		Vector<MyDrawing> selectedDrawings = stateManager.getMediator().getSelectedDrawing();
		
		if (isDraggingShape) {
			if (selectedDrawings != null && !selectedDrawings.isEmpty()) {
				int dx = x - x1;
				int dy = y - y1;
				
				for (MyDrawing d : selectedDrawings) {
					d.move(dx, dy);
				}
				x1 = x;
				y1 = y;
			}
		} else if (selectionRect != null) {
			selectionRect.setSize(x - x1, y - y1);
			Rectangle selectionBounds = selectionRect.region.getBounds();
			
			for (MyDrawing d : (Vector<MyDrawing>) selectedDrawings.clone()) {
				d.setSelected(false);
			}
			selectedDrawings.clear();
			
			Enumeration<MyDrawing> e = mediator.drawingsElements();
			while (e.hasMoreElements()) {
				MyDrawing d = e.nextElement();
				if (d == selectionRect) {
					continue;
				}
				
				if (d.region != null && selectionBounds.intersects(d.region.getBounds2D())) {
					d.setSelected(true);
					selectedDrawings.add(d);
				}
			}
		}
	}

	@Override
	public void mouseUp(int x, int y) {
		if (selectionRect != null) {
			stateManager.getMediator().removeDrawing(selectionRect);
			selectionRect = null;
		}
		
		isDraggingShape = false;

	}

}
