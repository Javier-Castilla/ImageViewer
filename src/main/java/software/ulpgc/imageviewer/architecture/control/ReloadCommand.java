package software.ulpgc.imageviewer.architecture.control;

import software.ulpgc.imageviewer.architecture.io.ImageLoader;

public class ReloadCommand implements Command {
    private final ImagePresenter presenter;
    private final ImageLoader loader;

    public ReloadCommand(ImagePresenter presenter, ImageLoader loader) {
        this.presenter = presenter;
        this.loader = loader;
    }

    @Override
    public void execute() {
            presenter.show(loader.load());
    }
}
