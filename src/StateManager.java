public class StateManager {
	Mediator mediator;
	State currentState;
	boolean isShadow;
	boolean isDashed;
	int lineWidth;
	
	public StateManager(Mediator mediator) {
		this.mediator = mediator;
		currentState = new RectState(this);
		isShadow = false;
		isDashed = false;
		lineWidth = 2;
	}
	
	public void setState(State state) {
		currentState = state;
	}
	
	public void setShadow(boolean isShadow) {
		this.isShadow = isShadow;
	}
	
	public void setDashed(boolean isDashed) {
		this.isDashed = isDashed;
	}
	
	public void setLineWidth(int lineWidth) {
		this.lineWidth = lineWidth;
	}

	public void mouseDown(int x, int y) {
		currentState.mouseDown(x, y);
		mediator.repaint();
	}
	
	public void mouseDrag(int x, int y) {
		currentState.mouseDrag(x, y);
		mediator.repaint();
	}
	
	public void mouseUp(int x, int y) {
		currentState.mouseUp(x, y);
	}
	
	public boolean getShadow() {
		return isShadow;
	}
	
	public boolean getDashed() {
	    return isDashed;
	}
	
	public int getLineWidth() {
		return lineWidth;
	}

	
	public void addDrawing(MyDrawing d) {
		d.setShadow(isShadow);
		d.setDashed(isDashed);
		d.setLineWidth(lineWidth);
		mediator.addDrawing(d);
	}
}