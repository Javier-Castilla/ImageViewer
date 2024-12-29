package software.ulpgc.imageviewer.architecture.view;

import software.ulpgc.imageviewer.architecture.model.Image;

public interface ImageDisplay {
    int width();

    int height();

    void paint(PaintOrder... paintOrders);

    void on(Shift shift);

    void on(Released release);

    interface Shift {
        Shift NULL = offset -> {};

        void offset(int offset);
    }

    interface Released {
        Released NULL = offset -> {};

        void offset(int offset);
    }

    record PaintOrder(byte[] content, int offset) {}
}
