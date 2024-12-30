package software.ulpgc.imageviewer.apps.windows.io;

import software.ulpgc.imageviewer.architecture.io.ImageLoader;
import software.ulpgc.imageviewer.architecture.model.Image;
import software.ulpgc.imageviewer.architecture.model.Size;

import java.io.*;
import java.net.URL;
import java.util.Random;

public class LoremPicsumImageLoader implements ImageLoader {
    private static final String BASE_URL = "https://picsum.photos/";
    private static final Random RANDOM = new Random();
    private final byte[][] files;

    private LoremPicsumImageLoader(int images) {
        this.files = new byte[images][];
    }

    public static LoremPicsumImageLoader with(int numberOfImages) {
        return new LoremPicsumImageLoader(numberOfImages);
    }

    private String getRandomSize(int maxSize) {
        return String.valueOf(RANDOM.nextInt(maxSize / 2, maxSize));
    }

    @Override
    public Image loadWithMaximumSize(Size size) {
        for (int i = 0; i < files.length; i++)
            try (InputStream inputStream = new URL(BASE_URL + getRandomSize(size.width()) + "/" + getRandomSize(size.height())).openStream();) {
                 files[i] = inputStream.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return imageAt(0);
    }

    private Image imageAt(int index) {
        return new Image() {
            @Override
            public String name() {
                return "";
            }

            @Override
            public byte[] content() {
                return files[index];
            }

            @Override
            public Format format() {
                return null;
            }

            @Override
            public Image next() {
                return imageAt(nextIndex());
            }

            private int nextIndex() {
                return (index + 1) % files.length;
            }

            @Override
            public Image previous() {
                return imageAt(previousIndex());
            }

            private int previousIndex() {
                return index > 0 ? index - 1 : files.length - 1;
            }
        };
    }
}
