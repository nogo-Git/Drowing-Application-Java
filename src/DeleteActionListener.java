import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteActionListener implements ActionListener {
    private Mediator mediator;

    public DeleteActionListener(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mediator.deleteSelectedDrawing(); // 選択されている図形を削除
    }
}