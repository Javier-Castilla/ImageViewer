package software.ulpgc.imageviewer.apps.windows.io;

import software.ulpgc.imageviewer.io.ImageDeserializer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SwingImageDeserializer implements ImageDeserializer {
    private final Map<Integer, BufferedImage> memoize;

    public SwingImageDeserializer() {
        this.memoize = new HashMap<>();
    }

    @Override
    public Object deserialize(byte[] imageContent) {
        return memoize.computeIfAbsent(Arrays.hashCode(imageContent), i -> read(imageContent));
    }

    private BufferedImage read(byte[] imageContent) {
        try {
            return ImageIO.read(new ByteArrayInputStream(imageContent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
