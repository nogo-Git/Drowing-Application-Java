import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChangeListener implements ActionListener {
    private StateManager stateManager;
    private Mediator mediator;
    private Color fillColorToSet;
    private Color lineColorToSet;

    public ColorChangeListener(StateManager sm, Mediator m, Color color) {
        this(sm, m, color, color); // デフォルト：塗りつぶしと線に同じ色を使用
    }

    public ColorChangeListener(StateManager sm, Mediator m, Color fillColor, Color lineColor) {
        this.stateManager = sm;
        this.mediator = m;
        this.fillColorToSet = fillColor;
        this.lineColorToSet = lineColor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 1. StateManagerのデフォルトを更新して、将来描画される図形の色を設定
        stateManager.setFillColor(fillColorToSet);
        stateManager.setLineColor(lineColorToSet);

        // 2. 現在選択されている図形があれば、その色を設定
        MyDrawing selected = mediator.getSelectedDrawing(); //
        if (selected != null) {
            selected.setFillColor(fillColorToSet); //
            selected.setLineColor(lineColorToSet); //
            mediator.repaint(); // 選択された図形の変更を表示するために再描画
        }
        // 図形が選択されていない場合でも、新しい図形のデフォルト色は更新される。
        // 現在のデフォルト色を表示するUI要素がなければ、ここでの再描画は必ずしも必要ないが、
        // 今回は選択された図形が変更された場合のみ再描画する。
    }
}