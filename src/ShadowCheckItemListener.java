import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ShadowCheckItemListener implements ItemListener {
    private StateManager stateManager;

    public ShadowCheckItemListener(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        boolean selected = (e.getStateChange() == ItemEvent.SELECTED); //
        stateManager.setShadow(selected); //
    }
}