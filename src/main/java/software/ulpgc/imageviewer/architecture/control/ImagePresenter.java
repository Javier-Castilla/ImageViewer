package software.ulpgc.imageviewer.architecture.control;

import software.ulpgc.imageviewer.architecture.model.Image;
import software.ulpgc.imageviewer.architecture.model.ImageHolder;
import software.ulpgc.imageviewer.architecture.view.ImageDisplay;

public class ImagePresenter {
    private final ImageDisplay imageDisplay;
    private final ImageHolder imageHolder;

    public ImagePresenter(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
        imageDisplay.on(shift());
        imageDisplay.on(released());
        imageHolder = new ImageHolder();
    }

    private ImageDisplay.Shift shift() {
        return offset -> imageDisplay.paint(
                paintOrderForCurrentImageWith(offset),
                isDisplayingPreviousImage(offset) ?
                        paintOrderForPreviousImageWith(offset - imageDisplay.width()) :
                        paintOrderForNextImageWith(imageDisplay.width() + offset)
        );
    }

    private ImageDisplay.PaintOrder paintOrderForCurrentImageWith(int offset) {
        return new ImageDisplay.PaintOrder(imageHolder.get().content(), offset);
    }

    private ImageDisplay.PaintOrder paintOrderForPreviousImageWith(int offset) {
        return new ImageDisplay.PaintOrder(imageHolder.get().previous().content(), offset);
    }

    private ImageDisplay.PaintOrder paintOrderForNextImageWith(int offset) {
        return new ImageDisplay.PaintOrder(imageHolder.get().next().content(), offset);
    }

    private boolean isDisplayingPreviousImage(int offset) {
        return offset > 0;
    }

    private ImageDisplay.Released released() {
        return offset -> {
            if (isDisplayingCurrentImageWith(offset))
                imageDisplay.paint(paintOrderForCurrentImageWith(offset));
        };
    }

    private boolean isDisplayingCurrentImageWith(int offset) {
        return Math.abs(offset) > imageDisplay.width() / 2;
    }

    public void show(Image image) {
        this.imageHolder.set(image);
        imageDisplay.paint(paintOrderForCurrentImageWith(0));
    }

    public Image currentImage() {
        return imageHolder.get();
    }
}
