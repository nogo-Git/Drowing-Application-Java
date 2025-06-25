import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

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
		
		Vector<MyDrawing> selectedDrawings = mediator.getSelectedDrawing();
		if (selectedDrawings != null && !selectedDrawings.isEmpty()) {
			for (MyDrawing d : selectedDrawings) {
				if (colorType.equals("line")) {
					d.setLineColor(color);
				} else {
					d.setFillColor(color);
				}
			}
			
			mediator.repaint();
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
