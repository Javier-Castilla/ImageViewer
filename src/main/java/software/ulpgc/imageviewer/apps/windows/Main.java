package software.ulpgc.imageviewer.apps.windows;

import software.ulpgc.imageviewer.apps.windows.io.LoremPicsumImageLoader;
import software.ulpgc.imageviewer.apps.windows.view.MainFrame;
import software.ulpgc.imageviewer.architecture.control.ImagePresenter;
import software.ulpgc.imageviewer.architecture.control.NextCommand;
import software.ulpgc.imageviewer.architecture.control.PreviousCommand;
import software.ulpgc.imageviewer.architecture.control.ReloadCommand;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        ImagePresenter presenter = new ImagePresenter(mainFrame.imageDisplay());
        LoremPicsumImageLoader loader = LoremPicsumImageLoader.with(10);
        mainFrame.add("previous", new PreviousCommand(presenter))
                .add("next", new NextCommand(presenter))
                .add("reload", new ReloadCommand(presenter, loader, mainFrame.sizeDialog(), mainFrame.loadingDisplay()))
                .setVisible(true);
    }
}
