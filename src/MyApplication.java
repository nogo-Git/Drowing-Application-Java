import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class MyApplication extends JFrame {
    StateManager stateManager;
    MyCanvas canvas;
    Mediator mediator; 

    public MyApplication() {
        super("My Paint Application");

        canvas = new MyCanvas();
        canvas.setBackground(Color.white);
        
        mediator = canvas.getMediator();

        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());

        stateManager = new StateManager(mediator);

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
        
        ColorComboBox colorComboBox = new ColorComboBox(stateManager, mediator, this);
        jp.add(colorComboBox);
        
        JCheckBox shadowCheck = new JCheckBox("shadow");
        jp.add(shadowCheck);        
        JCheckBox dashCheck = new JCheckBox("dash line");
        jp.add(dashCheck);
        
        jp.add(new JLabel("Line Width:"));
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(2, 1, 20, 1);
        JSpinner lineWidthSpinner = new JSpinner(spinnerModel);
        jp.add(lineWidthSpinner);

        // --- メニューバー、メニュー、メニューアイテムの作成 ---
        JMenuBar menuBar = new JMenuBar();
        
        // --- 「Edit」メニュー ---
        JMenu editMenu = new JMenu("Edit");
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem cutItem = new JMenuItem("Cut");
        
        editMenu.add(deleteItem);
        editMenu.add(copyItem);
        editMenu.add(cutItem);
        menuBar.add(editMenu); // メニューバーに「Edit」メニューを追加
        
        setJMenuBar(menuBar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jp, BorderLayout.NORTH);
        getContentPane().add(canvas, BorderLayout.CENTER);

        // --- イベントリスナー設定 ---
        // メニューアイテムのリスナーを設定
        deleteItem.addActionListener(new DeleteActionListener(mediator));
        copyItem.addActionListener(new CopyActionListener(mediator));
        cutItem.addActionListener(new CutActionListener(mediator));

        // Canvasのフォーカス設定
        canvas.setFocusable(true); 

        // Canvasのリスナーを設定
        canvas.addMouseListener(new CanvasMouseListener(mediator, stateManager, canvas));
        canvas.addMouseMotionListener(new CanvasMouseMotionListener(stateManager));
        
        // チェックボックスのリスナーを設定
        shadowCheck.addItemListener(new ShadowCheckItemListener(stateManager));
        dashCheck.addItemListener(new DashCheckItemListener(stateManager));
        
        // スピナーのリスナーを設定
        lineWidthSpinner.addChangeListener(new LineWidthSpinnerListener(stateManager, lineWidthSpinner));

        // ウィンドウのリスナーを設定
        this.addWindowListener(new AppWindowListener());
    }

    public Dimension getPreferredSize(){
        return new Dimension(1000, 600);
    }

    public static void main(String[] args){
        MyApplication app = new MyApplication();
        app.pack();
        app.setVisible(true);
    }
}