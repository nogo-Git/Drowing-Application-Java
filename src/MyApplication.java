import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyApplication extends JFrame {
	public MyApplication() {
		super("My Painter");
		
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		this.getContentPane().add(jp);
		
		MyCanvas canvas = new MyCanvas();
		for(int i = 0; i < 4; i++) {
			canvas.addDrawing(new MyHendecagonal(20 + i * 40, 20 + i * 30));
		}
		jp.add(BorderLayout.CENTER, canvas);
		
		// WindowEvent リスナを設定(無名クラスを利用している)
		this.addWindowListener(
			new WindowAdapter() {
				// ウィンドウが閉じたら終了する処理
				public void windowClosing(WindowEvent e) {
					System.exit(1);
				}
			}
		);
	}

	public static void main(String[] args) {
		MyApplication app = new MyApplication();
		app.setSize(400, 300);
		app.setVisible(true);
	}

}
