import javax.swing.*;
import java.awt.*;

/**
 * A dot icon to indicate suggestion moves of the chess pieces.
 */
public class Dot implements Icon {
    private final int w, h;
    private final Color color;

    /**
     * Configure the size and color of the suggestion dot.
     */
    public Dot() {
        this.w = 10;
        this.h = 10;
        this.color = Color.BLACK;
    }

    /**
     * Draw a dot as the icon.
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(color);
        g2d.fillOval(x, y, 10, 10);
    }

    @Override
    public int getIconWidth() {
        return w;
    }

    @Override
    public int getIconHeight() {
        return h;
    }
}
