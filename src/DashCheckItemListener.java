import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DashCheckItemListener implements ItemListener {
    private StateManager stateManager;

    public DashCheckItemListener(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        boolean selected = (e.getStateChange() == ItemEvent.SELECTED); //
        stateManager.setDashed(selected); //
    }
}