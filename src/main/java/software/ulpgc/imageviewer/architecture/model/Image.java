package software.ulpgc.imageviewer.architecture.model;

public interface Image {
    String name();

    byte[] content();

    Format format();

    Image next();

    Image previous();

    enum Format {
        JPG, JPEG, PNG, GIF
    }
}
