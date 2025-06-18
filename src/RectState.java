public class RectState extends State {
	private StateManager stateManager;
	private int x1, y1;
	private MyRectangle currentRect;
	
	public RectState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	@Override
	public void mouseDown(int x, int y) {
		x1 = x;
		y1 = y;
		currentRect = new MyRectangle(x1, y1, 0, 0);
		stateManager.addDrawing(currentRect);
	}
	
	@Override
	public void mouseDrag(int x, int y) {
		currentRect.setSize(x - x1,  y - y1);
	}

	@Override
	public void mouseUp(int x, int y) {

	}
}