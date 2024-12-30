package software.ulpgc.imageviewer.apps.windows.view;

import software.ulpgc.imageviewer.architecture.view.LoadingDisplay;

import javax.swing.*;
import java.awt.*;

public class SwingLoadingDisplay extends JDialog implements LoadingDisplay {

    public SwingLoadingDisplay(Window owner, String title, String message, ModalityType modalityType) {
        super(owner, title, modalityType);
        setSize(300, 100);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        add(BorderLayout.CENTER, createMessageLabel(message));
        add(BorderLayout.SOUTH, createProgressBar());
    }

    private static JLabel createMessageLabel(String message) {
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private JProgressBar createProgressBar() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        return progressBar;
    }

    @Override
    public void showDisplay() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

    @Override
    public void disposeDisplay() {
        SwingUtilities.invokeLater(() -> setVisible(false));
    }
}
