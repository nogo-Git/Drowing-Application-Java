import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyApplication2 extends JFrame {
	public MyApplication2() {
		super("My Painter");
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		MyCanvas canvas = new MyCanvas();
		
		jp.add(BorderLayout.CENTER, canvas);
		getContentPane().add(jp);
		
		MyMouseAdapter ma = new MyMouseAdapter(canvas);
		canvas.addMouseListener(ma);
		
		addWindowListener(
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(1);
					}
				});
		
	}

	public static void main(String[] args) {
		MyApplication2 ma = new MyApplication2();
		ma.setSize(400, 300);
		ma.setVisible(true);
	}
}

// マウスイベントを扱うクラス
class MyMouseAdapter extends MouseAdapter {
	private MyCanvas canvas;
	
	public MyMouseAdapter(MyCanvas canvas) {
		this.canvas = canvas;
	}
	
	// マウスがクリックされた時の処理をここに記述
	public void mousePressed(MouseEvent e) {
		canvas.addDrawing(new MyRectangle(e.getX(), e.getY()));
		
		// キャンバスの再描画命令
		canvas.repaint();
	}
}