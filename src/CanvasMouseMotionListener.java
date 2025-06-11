import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CanvasMouseMotionListener extends MouseMotionAdapter {
    private StateManager stateManager;

    public CanvasMouseMotionListener(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        stateManager.mouseDrag(e.getX(), e.getY()); //
    }
}