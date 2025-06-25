import java.util.Vector;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LineWidthSpinnerListener implements ChangeListener {
    private StateManager stateManager;
    private Mediator mediator;
    private JSpinner spinner;

    public LineWidthSpinnerListener(StateManager stateManager, Mediator mediator, JSpinner spinner) {
        this.stateManager = stateManager;
        this.mediator = mediator;
        this.spinner = spinner;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int lineWidthVal = (Integer) spinner.getValue();
        stateManager.setLineWidth(lineWidthVal);
        
        // 選択している図形の線の太さを変更
        Vector<MyDrawing> selectedDrawings = mediator.getSelectedDrawing();
        if (selectedDrawings != null && !selectedDrawings.isEmpty()) {
        	for (MyDrawing d : selectedDrawings) {
        		d.setLineWidth(lineWidthVal);
        	}
        }
        mediator.repaint();
    }
}