public class OvalState extends State {
	private StateManager stateManager;
	private int x1, y1;
	private MyOval currentOval;
	
	public OvalState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	@Override
	public void mouseDown(int x, int y) {
		x1 = x;
		y1 = y;
		currentOval = new MyOval(x1, y1, 0, 0);
		stateManager.addDrawing(currentOval);
	}
	
	@Override
	public void mouseDrag(int x, int y) {
		currentOval.setSize(x - x1,  y - y1);
	}

	@Override
	public void mouseUp(int x, int y) {
		// TODO 自動生成されたメソッド・スタブ

	}
}