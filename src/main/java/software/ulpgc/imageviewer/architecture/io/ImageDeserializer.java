package software.ulpgc.imageviewer.architecture.io;

public interface ImageDeserializer {
    Object deserialize(byte[] bytes);
}
