import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class DeleteActionListener implements ActionListener {
    private Mediator mediator;

    public DeleteActionListener(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	Vector<MyDrawing> selectedDrawings = mediator.getSelectedDrawing();
    	Vector<MyDrawing> drawingsToRemove = new Vector<>(selectedDrawings);
    	
    	for (MyDrawing d : drawingsToRemove) {
    		mediator.removeDrawing(d);
    	}
    }
}