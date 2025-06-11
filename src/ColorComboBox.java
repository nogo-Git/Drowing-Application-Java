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
	private String colorType;
	
	public ColorComboBox(StateManager stateManager, Mediator mediator, JFrame parentFrame, String colorType) {
		super();
		
		this.stateManager = stateManager;
		this.mediator = mediator;
		this.parentFrame = parentFrame;
		this.colorType = colorType;
		
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
		
		if (colorType.equals("line")) {
			stateManager.setLineColor(color);
		} else {
			stateManager.setFillColor(color);
		}
		
		MyDrawing selected = mediator.getSelectedDrawing(); //
        if (selected != null) {
        	if (colorType.equals("line")) {
    			selected.setLineColor(color);
    		} else {
    			selected.setFillColor(color);
    		}
            mediator.repaint(); // 選択された図形の変更を表示するために再描画
        }
	}
		
	class ColorSelectionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedName = (String) getSelectedItem();
			Color selectedColor;
			
			if (selectedName.equals("Other Colors...")) {
				Color initialColor;
				if (colorType.equals("line")) {
				    initialColor = stateManager.getLineColor();
				} else {
				    initialColor = stateManager.getFillColor();
				}
				selectedColor = JColorChooser.showDialog(
				    parentFrame,
				    "Choose a Color",
				    initialColor
				);
			} else {
				selectedColor = colorMap.get(selectedName);
			}
			
			updateColor(selectedColor);
		}
	}
}
