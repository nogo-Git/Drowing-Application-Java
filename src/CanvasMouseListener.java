import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CanvasMouseListener extends MouseAdapter {
    private Mediator mediator;
    private StateManager stateManager;
    private MyCanvas canvas;

    public CanvasMouseListener(Mediator mediator, StateManager stateManager, MyCanvas canvas) {
        this.mediator = mediator;
        this.stateManager = stateManager;
        this.canvas = canvas;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) { // 右クリックの時はペースト
            mediator.paste(e.getX(), e.getY()); //
        }
        else {
            stateManager.mouseDown(e.getX(), e.getY()); //
        }
        canvas.requestFocusInWindow(); // マウスプレス時にキャンバスにフォーカスを要求
    }
}