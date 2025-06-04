import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SelectButton extends JButton {
	StateManager stateManager;
	
	public SelectButton(StateManager stateManager) {
		super("Select");
		
		addActionListener(new SelectListener());
		
		this.stateManager = stateManager;
	}
	
	class SelectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new SelectState(stateManager));
		}
	}
}
