import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CopyActionListener implements ActionListener {
    private Mediator mediator;

    public CopyActionListener(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mediator.copy(); //
        mediator.repaint(); //
    }
}