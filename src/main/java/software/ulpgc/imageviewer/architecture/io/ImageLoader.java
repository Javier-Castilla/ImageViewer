package software.ulpgc.imageviewer.architecture.io;

import software.ulpgc.imageviewer.architecture.model.Image;
import software.ulpgc.imageviewer.architecture.model.Size;

public interface ImageLoader {
    Image loadWithMaximumSize(Size size);
}
