package software.ulpgc.imageviewer.architecture.control;

import software.ulpgc.imageviewer.architecture.io.ImageLoader;
import software.ulpgc.imageviewer.architecture.view.LoadingDisplay;
import software.ulpgc.imageviewer.architecture.view.SizeDialog;

public class ReloadCommand implements Command {
    private final ImagePresenter presenter;
    private final ImageLoader loader;
    private final SizeDialog sizeDialog;
    private final LoadingDisplay loadingDisplay;

    public ReloadCommand(ImagePresenter presenter, ImageLoader loader, SizeDialog sizeDialog, LoadingDisplay loadingDisplay) {
        this.presenter = presenter;
        this.loader = loader;
        this.sizeDialog = sizeDialog;
        this.loadingDisplay = loadingDisplay;
    }

    @Override
    public void execute() {
        loadingDisplay.showDisplay();
        new Thread(() -> {
            try {
                presenter.show(loader.loadWithMaximumSize(sizeDialog.get()));
            } finally {
                loadingDisplay.disposeDisplay();
            }
        }).start();
    }
}
