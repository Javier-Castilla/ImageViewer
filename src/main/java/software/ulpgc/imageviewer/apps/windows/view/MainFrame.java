package software.ulpgc.imageviewer.apps.windows.view;

import software.ulpgc.imageviewer.apps.windows.io.SwingImageDeserializer;
import software.ulpgc.imageviewer.architecture.control.Command;
import software.ulpgc.imageviewer.architecture.view.ImageDisplay;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private Map<String, Command> commands;
    private final SwingImageDisplay imageDisplay;

    public MainFrame() throws HeadlessException {
        setTitle("ImageViewer");
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, this.imageDisplay = createImageDisplay());
        add(BorderLayout.SOUTH, createToolbar());
        this.commands = new HashMap<>();
    }

    private SwingImageDisplay createImageDisplay() {
        return new SwingImageDisplay(new SwingImageDeserializer());
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(createButton("Reload"));
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
}
