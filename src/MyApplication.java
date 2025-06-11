import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent; // ActionListenerのためにインポート
import java.awt.event.ActionListener; // ActionListenerのためにインポート
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


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
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mediator.deleteSelectedDrawing(); // 選択されている図形を削除
            }
        });
        editMenu.add(deleteItem);
        
        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		mediator.copy();
        		mediator.repaint();
        	}
        });
        editMenu.add(copyItem);
        
        JMenuItem cutItem = new JMenuItem("Cut");
        cutItem.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		mediator.cut();
        		mediator.repaint();
        	}
        });
        editMenu.add(cutItem);
        menuBar.add(editMenu); // メニューバーに「Edit」メニューを追加
        
        setJMenuBar(menuBar);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(jp, BorderLayout.NORTH);
        getContentPane().add(canvas, BorderLayout.CENTER);

        // --- イベントリスナー設定 ---
        // Canvasのフォーカス設定
        canvas.setFocusable(true); 

        canvas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
            	if (e.getButton() == MouseEvent.BUTTON3) { // 右クリックの時はペースト
            		mediator.paste(e.getX(), e.getY());
            	}
            	else {
            		stateManager.mouseDown(e.getX(), e.getY());
            	}
                canvas.requestFocusInWindow(); // マウスプレス時にキャンバスにフォーカスを要求
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
                int lineWidthVal = (Integer) lineWidthSpinner.getValue();
                stateManager.setLineWidth(lineWidthVal);
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public Dimension getPreferredSize(){
        return new Dimension(800, 600);
    }

    public static void main(String[] args){
        MyApplication app = new MyApplication();
        app.pack();
        app.setVisible(true);
    }
}