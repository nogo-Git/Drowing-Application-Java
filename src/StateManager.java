public class StateManager {
	MyCanvas canvas;
	State currentState;
	boolean isDashed;
	
	public StateManager(MyCanvas canvas) {
		this.canvas = canvas;
		currentState = new RectState(this);
		isDashed = true;
	}
	
	public void setState(State state) {
		currentState = state;
	}
	
	public void mouseDown(int x, int y) {
		currentState.mouseDown(x, y);
		canvas.repaint();
	}
	
	public void mouseDrag(int x, int y) {
		currentState.mouseDrag(x, y);
		canvas.repaint();
	}
	
	public void mouseUp(int x, int y) {
		currentState.mouseUp(x, y);
	}
	
	public void addDrawing(MyDrawing d) {
		d.setDashed(isDashed);
		canvas.addDrawing(d);
	}
}