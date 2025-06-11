import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LineWidthSpinnerListener implements ChangeListener {
    private StateManager stateManager;
    private JSpinner spinner;

    public LineWidthSpinnerListener(StateManager stateManager, JSpinner spinner) {
        this.stateManager = stateManager;
        this.spinner = spinner;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int lineWidthVal = (Integer) spinner.getValue(); //
        stateManager.setLineWidth(lineWidthVal); //
    }
}