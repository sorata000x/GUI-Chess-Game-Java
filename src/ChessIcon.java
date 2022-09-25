/**
 * Reference
 *      Java Graphics How to - Draw your own Icon with paintIcon() method
 *          http://www.java2s.com/Tutorials/Java/Graphics_How_to/Draw/Draw_your_own_Icon_with_paintIcon_method.htm
 *      Drawing an image
 *          https://docs.oracle.com/javase/tutorial/2d/images/drawimage.html
 *      Can you increase line thickness when using Java Graphics for an applet? I don't believe that BasicStroke works
 *          https://stackoverflow.com/questions/16995308/can-you-increase-line-thickness-when-using-java-graphics-for-an-applet-i-dont
 */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * An icon of chess piece and circle to indicate takable.
 */
public class ChessIcon implements Icon {

    private final int w, h;
    private final BufferedImage image;
    private final boolean circle;

    /**
     * Configure the size and image of the icon, and whether it includes a circle or not.
     * @param image: image of the icon.
     * @param circle: if the icon going to include a circle behind the image or not.
     */
    public ChessIcon(BufferedImage image, boolean circle) {
        this.w = 50;
        this.h = 50;
        this.image = image;
        this.circle = circle;
    }

    /**
     * Draws image and circle on it (if circle is true).
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g;
        if (circle) {
            g2d.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(5));
            g2d.drawOval(x, y, 50, 50);
        }
        if (image != null) {
            int ix = (w - image.getWidth()) / 2 + 6;
            int iy = (h - image.getHeight()) / 2 + 3;
            g2d.drawImage(image, ix, iy, null);
        }
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
