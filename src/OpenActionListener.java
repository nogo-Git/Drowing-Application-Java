import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class OpenActionListener implements ActionListener{
	private Mediator mediator;
	private JFrame parentFrame;
	
	public OpenActionListener(Mediator mediator, JFrame parentFrame) {
		this.mediator = mediator;
		this.parentFrame = parentFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
			try {
				FileInputStream fin = new FileInputStream(fileChooser.getSelectedFile());
				ObjectInputStream in = new ObjectInputStream(fin);
				
				Vector<MyDrawing> loadedDrawings = (Vector<MyDrawing>) in.readObject();
				mediator.setDrawings(loadedDrawings);
				
				in.close();
				fin.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
