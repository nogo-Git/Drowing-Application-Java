public class HendecagonalState extends State {
	StateManager stateManager;
	int x1, y1;
	MyHendecagonal currentHendecagonal;
	
	public HendecagonalState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	@Override
	public void mouseDown(int x, int y) {
		x1 = x;
		y1 = y;
		currentHendecagonal = new MyHendecagonal(x1, y1, 0, 0);
		stateManager.addDrawing(currentHendecagonal);
	}
	
	@Override
	public void mouseDrag(int x, int y) {
		currentHendecagonal.setSize(x - x1, y - y1);
	}

	@Override
	public void mouseUp(int x, int y) {
		// TODO 自動生成されたメソッド・スタブ

	}
}