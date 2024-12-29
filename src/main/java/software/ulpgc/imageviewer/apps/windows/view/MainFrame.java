package software.ulpgc.imageviewer.apps.windows.view;

import software.ulpgc.imageviewer.apps.windows.Main;
import software.ulpgc.imageviewer.architecture.control.Command;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainFrame extends JFrame {
    private Map<String, Command> commands;

    public MainFrame() throws HeadlessException {
        setTitle("ImageViewer");
        setSize(800, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, createImageDisplay());
        add(BorderLayout.SOUTH, createToolbar());
    }

    private SwingImageDisplay createImageDisplay() {
        return new SwingImageDisplay();
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton previousButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");
        previousButton.addActionListener(e -> commands.get("previous").execute());
        nextButton.addActionListener(e -> commands.get("next").execute());
        return panel;
    }

    public MainFrame add(String name, Command command) {
        commands.put(name, command);
        return this;
    }
}
