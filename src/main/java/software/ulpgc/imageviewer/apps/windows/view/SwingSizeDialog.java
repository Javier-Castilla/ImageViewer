package software.ulpgc.imageviewer.apps.windows.view;

import software.ulpgc.imageviewer.architecture.model.Size;
import software.ulpgc.imageviewer.architecture.view.SizeDialog;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SwingSizeDialog extends JPanel implements SizeDialog {

    private final JSlider widthSlider;
    private final JSlider heightSlider;

    public SwingSizeDialog() {
        setLayout(new GridLayout(1, 0));
        add(createSliderPanel("Width", this.widthSlider = createSlider()));
        add(createSliderPanel("Height", this.heightSlider = createSlider()));
        setTitledBorder();
    }

    private void setTitledBorder() {
        TitledBorder border = new TitledBorder("Maximum Size");
        border.setTitleJustification(TitledBorder.CENTER);
        setBorder(border);
    }

    private JSlider createSlider() {
        return new JSlider(JSlider.VERTICAL, 400, getLocalMaxResolution().width(), getLocalMaxResolution().width() / 2);
    }

    private JPanel createSliderPanel(String name, JSlider slider) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(name));

        slider.setMajorTickSpacing(300);
        slider.setMinorTickSpacing(100);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        panel.add(slider);

        return panel;
    }

    private Size getLocalMaxResolution() {
        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();

        GraphicsDevice device = environment.getDefaultScreenDevice();

        DisplayMode displayMode = device.getDisplayMode();
        int width = displayMode.getWidth();
        int height = displayMode.getHeight();
        return new Size(width, height);
    }

    @Override
    public Size get() {
        return new Size(widthSlider.getValue(), heightSlider.getValue());
    }

    @Override
    public SizeDialog set(Size size) {
        widthSlider.setValue(size.width());
        heightSlider.setValue(size.height());
        return this;
    }
}
