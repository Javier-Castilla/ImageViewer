package software.ulpgc.imageviewer.architecture.model;

public class ImageHolder {
    private final Image[] image = new Image[]{null};

    public void set(Image image) {
        this.image[0] = image;
    }

    public Image get() {
        return image[0];
    }
}
