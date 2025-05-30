
public class SelectState extends State {
	StateManager stateManager;
	int x1, y1;
	
	public SelectState(StateManager stateManager) {
		this.stateManager = stateManager;
	}

	@Override
	public void mouseDown(int x, int y) {
		x1 = x;
		y1 = y;
		
	}

	@Override
	public void mouseDrag(int x, int y) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseUp(int x, int y) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
