import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ColorComboBox extends JComboBox<String> {
	private StateManager stateManager;
	private Mediator mediator;
	private JFrame parentFrame; // JColorChooserの親ウィンドウとして使用
	private Map<String, Color> colorMap = new LinkedHashMap<>();
	
	public ColorComboBox(StateManager stateManager, Mediator mediator, JFrame parentFrame) {
		super();
		
		this.stateManager = stateManager;
		this.mediator = mediator;
		this.parentFrame = parentFrame;
		
		colorMap.put("Black", Color.black);
		colorMap.put("White", Color.white);
		colorMap.put("Red", Color.red);
		colorMap.put("Green", Color.green);
		colorMap.put("Blue", Color.blue);
		colorMap.put("Yellow", Color.yellow);
		
		for (String colorName : colorMap.keySet()) {
			addItem(colorName);
		}
		addItem("Other Colors...");
		
		addActionListener(new ColorSelectionListener());
	}
	
	private void updateColor(Color color) {
		if (color == null) {
			return;
		}
		
		stateManager.setFillColor(color);
		stateManager.setLineColor(color);
		
		MyDrawing selected = mediator.getSelectedDrawing(); //
        if (selected != null) {
            selected.setFillColor(color); //
            selected.setLineColor(color); //
            mediator.repaint(); // 選択された図形の変更を表示するために再描画
        }
	}
		
	class ColorSelectionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedName = (String) getSelectedItem();
			Color selectedColor;
			
			if (selectedName.equals("Other Colors...")) {
				selectedColor = JColorChooser.showDialog(
					parentFrame,
					"Choose a Color",
					stateManager.getFillColor()
				);
			} else {
				selectedColor = colorMap.get(selectedName);
			}
			
			updateColor(selectedColor);
		}
	}
}
