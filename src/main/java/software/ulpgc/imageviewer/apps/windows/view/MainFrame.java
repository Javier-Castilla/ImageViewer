package software.ulpgc.imageviewer.apps.windows.view;

import software.ulpgc.imageviewer.apps.windows.io.SwingImageDeserializer;
import software.ulpgc.imageviewer.architecture.control.Command;
import software.ulpgc.imageviewer.architecture.view.ImageDisplay;
import software.ulpgc.imageviewer.architecture.view.SizeDialog;

import javax.swing.*;
import java.awt.*;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private final Map<String, Command> commands;
    private final SwingImageDisplay imageDisplay;
    private final SwingLoadingDisplay loadingDisplay;
    private final SwingSizeDialog sizeDialog;

    public MainFrame() throws HeadlessException {
        setTitle("ImageViewer");
        setSize(800, 800);
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, createCenterPanel(this.imageDisplay = createImageDisplay(), this.sizeDialog = createSizeDialog()));
        this.loadingDisplay = createLoadingDisplay();
        this.commands = new HashMap<>();
    }

    private SwingSizeDialog createSizeDialog() {
        return new SwingSizeDialog();
    }

    private JPanel createCenterPanel(SwingImageDisplay imageDisplay, SwingSizeDialog sizeDialog) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(BorderLayout.CENTER, createDisplayPanel(imageDisplay, createToolbar()));
        panel.add( BorderLayout.EAST, createMenuBar(sizeDialog));
        return panel;
    }

    private JPanel createDisplayPanel(SwingImageDisplay imageDisplay, JPanel toolbar) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(BorderLayout.CENTER, imageDisplay);
        panel.add(BorderLayout.SOUTH, toolbar);
        return panel;
    }

    private SwingLoadingDisplay createLoadingDisplay() {
        return new SwingLoadingDisplay(
                this,
                "Cargando imágenes...",
                "Por favor, espera. Cargando imágenes...",
                Dialog.ModalityType.APPLICATION_MODAL
        );
    }

    private JPanel createMenuBar(SwingSizeDialog sizeDialog) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(5));
        panel.add(sizeDialog);
        JButton button = createButton("Reload");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(5));
        panel.add(button);
        panel.add(Box.createVerticalStrut(5));
        return panel;
    }

    private SwingImageDisplay createImageDisplay() {
        return new SwingImageDisplay(new SwingImageDeserializer());
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel(new GridLayout(1, 0));
        panel.add(createButton("Previous"));
        panel.add(createButton("Next"));
        return panel;
    }

    private JButton createButton(String name) {
        JButton button = new JButton(name);
        button.addActionListener(e -> commands.get(name.toLowerCase()).execute());
        return button;
    }

    public MainFrame add(String name, Command command) {
        commands.put(name, command);
        return this;
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }

    public SwingLoadingDisplay loadingDisplay() {
        return loadingDisplay;
    }

    public SizeDialog sizeDialog() {
        return sizeDialog;
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        commands.get("reload").execute();
    }
}
