package software.ulpgc.imageviewer.architecture.control;

public class PreviousCommand implements Command {
    private final ImagePresenter presenter;

    public PreviousCommand(ImagePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.show(presenter.currentImage().previous());
    }
}
