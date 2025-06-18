import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CutActionListener implements ActionListener {
    private Mediator mediator;

    public CutActionListener(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mediator.cut();
    }
}