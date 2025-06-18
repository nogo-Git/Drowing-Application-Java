import java.awt.Color;

public class StateManager {
	private Mediator mediator;
	private State currentState;
	private boolean isShadow;
	private boolean isDashed;
	private int lineWidth;
	private Color fillColor = Color.white;
	private Color lineColor = Color.black;
	
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
	
	public void setFillColor(Color color) {
		fillColor = color;
	}
	
	public void setLineColor(Color color) {
		lineColor = color;
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

	public Mediator getMediator() {
		return mediator;
	}
	
	public Color getFillColor() {
		return fillColor;
	}
	
	public Color getLineColor() {
		return lineColor;
	}
	
	public void addDrawing(MyDrawing d) {
		d.setShadow(isShadow);
		d.setDashed(isDashed);
		d.setLineWidth(lineWidth);
		d.setFillColor(fillColor);
		d.setLineColor(lineColor);
		mediator.addDrawing(d);
	}
}