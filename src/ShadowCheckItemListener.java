import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

public class ShadowCheckItemListener implements ItemListener {
    private StateManager stateManager;
    private Mediator mediator;

    public ShadowCheckItemListener(StateManager stateManager, Mediator mediator) {
        this.stateManager = stateManager;
        this.mediator = mediator;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        boolean selected = (e.getStateChange() == ItemEvent.SELECTED); //
        stateManager.setShadow(selected); //
        
        // 図形を選択している場合はその図形の影の有無を変更
        Vector<MyDrawing> selectedDrawings = mediator.getSelectedDrawing();
        
        if (selectedDrawings != null && !selectedDrawings.isEmpty()) {
        	for (MyDrawing d : selectedDrawings){
        		d.setShadow(selected);
        	}
        	mediator.repaint();
        }
    }
}