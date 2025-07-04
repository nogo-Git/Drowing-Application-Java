
public class ArcState extends State {
	private StateManager stateManager;
	private int x1, y1;
	private MyArc currentArc;
	
	public ArcState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	@Override
	public void mouseDown(int x, int y) {
		x1 = x;
		y1 = y;
		currentArc = new MyArc(x1, y1, 0, 0);
		stateManager.addDrawing(currentArc);
	}

	@Override
	public void mouseDrag(int x, int y) {
		currentArc.setSize(x - x1, y - y1);
	}

	@Override
	public void mouseUp(int x, int y) {
	}

}
