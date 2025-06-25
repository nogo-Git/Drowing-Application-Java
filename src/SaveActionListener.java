import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class SaveActionListener implements ActionListener {
	private Mediator mediator;
	private JFrame parentFrame;
	
	public SaveActionListener(Mediator mediator, JFrame parentFrame) {
		this.mediator = mediator;
		this.parentFrame = parentFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fout = new FileOutputStream(fileChooser.getSelectedFile());
                ObjectOutputStream out = new ObjectOutputStream(fout);

                out.writeObject(mediator.getDrawings());
                out.flush();

                out.close();
                fout.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
	}
}
