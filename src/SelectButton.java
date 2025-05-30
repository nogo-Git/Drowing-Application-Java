import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SelectButton extends JButton {
	StateManager stateManager;
	
	public SelectButton(StateManager stateManager) {
		super("Select");
		
		addActionListener(new ArcListener());
		
		this.stateManager = stateManager;
	}
	
	class ArcListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new SelectState(stateManager));
		}
	}
}
