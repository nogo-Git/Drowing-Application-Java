import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ArcButton extends JButton {
	StateManager stateManager;
	
	public ArcButton(StateManager stateManager) {
		super("Arc");
		
		addActionListener(new ArcListener());
		
		this.stateManager = stateManager;
	}
	
	class ArcListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			stateManager.setState(new ArcState(stateManager));
		}
	}
}
