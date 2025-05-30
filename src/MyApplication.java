import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MyApplication extends JFrame{
    StateManager stateManager;
    MyCanvas canvas;

    public MyApplication() {
        super("My Paint Application");

        canvas = new MyCanvas();
        canvas.setBackground(Color.white);

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());

        stateManager = new StateManager(canvas.getMediator());

        SelectButton selectButton = new SelectButton(stateManager);
        jp.add(selectButton);
        RectButton rectButton = new RectButton(stateManager);
        jp.add(rectButton);
        OvalButton ovalButton = new OvalButton(stateManager);
        jp.add(ovalButton);
        HendecagonalButton hendecagonalButton = new HendecagonalButton(stateManager);
        jp.add(hendecagonalButton);
        ArcButton arcButton = new ArcButton(stateManager);
        jp.add(arcButton);
        JCheckBox shadowCheck = new JCheckBox("shadow");
        jp.add(shadowCheck);        
        JCheckBox dashCheck = new JCheckBox("dash line");
        jp.add(dashCheck);
        
        jp.add(new JLabel("Line Width:")); // ラベル追加
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(2, 1, 20, 1); // 初期値2, 最小1, 最大20, ステップ1
        JSpinner lineWidthSpinner = new JSpinner(spinnerModel);
        jp.add(lineWidthSpinner);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jp, BorderLayout.NORTH);
        getContentPane().add(canvas, BorderLayout.CENTER);

        canvas.addMouseListener(new MouseAdapter() {
            // 現在の状態の mouseDown 処理の呼び出し
            public void mousePressed(MouseEvent e) {
                stateManager.mouseDown(e.getX(), e.getY());
            }
        });
        
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
        	public void mouseDragged(MouseEvent e) {
        		stateManager.mouseDrag(e.getX(), e.getY());
        	}
        });
        
        shadowCheck.addItemListener(e -> {
            boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
            stateManager.setShadow(selected);
        });
        
        dashCheck.addItemListener(e -> {
            boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
            stateManager.setDashed(selected);
        });
        
        lineWidthSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int lineWidth = (Integer) lineWidthSpinner.getValue();
                stateManager.setLineWidth(lineWidth);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            // ウィンドウを閉じたら終了
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public Dimension getPreferredSize(){
        return new Dimension(500, 600);
    }

    public static void main(String[] args){
        MyApplication app = new MyApplication();
        app.pack();
        app.setVisible(true);
    }
}
