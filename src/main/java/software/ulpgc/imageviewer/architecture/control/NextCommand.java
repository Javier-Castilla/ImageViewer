package software.ulpgc.imageviewer.architecture.control;

public class NextCommand implements Command {
    private final ImagePresenter presenter;

    public NextCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.show(presenter.currentImage().next());
    }
}
