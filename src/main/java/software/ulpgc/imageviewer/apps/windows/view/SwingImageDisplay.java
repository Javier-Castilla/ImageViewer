package software.ulpgc.imageviewer.apps.windows.view;

import software.ulpgc.imageviewer.architecture.io.ImageDeserializer;
import software.ulpgc.imageviewer.architecture.view.ImageDisplay;
import software.ulpgc.imageviewer.architecture.view.ViewPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private final ImageDeserializer deserializer;
    private Shift shift;
    private Released released;
    private int initX;
    private final List<PaintOrder> paintOrders;

    public SwingImageDisplay(ImageDeserializer deserializer) {
        this.deserializer = deserializer;
        this.shift = Shift.NULL;
        this.released = Released.NULL;
        paintOrders = new ArrayList<>();
        addMouseListener(createMouseListener());
        addMouseMotionListener(createMouseMotionListener());
    }

    private MouseMotionListener createMouseMotionListener() {
        return new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                shift.offset(e.getX() - initX);
            }
        };
    }

    private MouseListener createMouseListener() {
        return new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initX = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                released.offset(e.getX() - initX);
            }
        };
    }

    @Override
    public int width() {
        return getWidth();
    }

    @Override
    public int height() {
        return getHeight();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width(), height());
        paintOrders.forEach(o -> {
            Image image = deserialize(o.content());
            ViewPort viewPort = ViewPort.ofSize(width(), height())
                    .fit(image.getWidth(null), image.getHeight(null));
            g.drawImage(image, viewPort.x() + o.offset(), viewPort.y(), viewPort.width(), viewPort.height(), null);
        });
    }

    private Image deserialize(byte[] content) {
        return (Image) deserializer.deserialize(content);
    }

    @Override
    public void paint(PaintOrder... paintOrders) {
        this.paintOrders.clear();
        Collections.addAll(this.paintOrders, paintOrders);
        repaint();
    }

    @Override
    public void on(Shift shift) {
        this.shift = shift;
    }

    @Override
    public void on(Released released) {
        this.released = released;
    }
}
