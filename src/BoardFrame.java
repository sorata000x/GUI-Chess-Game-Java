/**
 * Reference
 *      Draw text with graphics object on JFrame
 *          https://stackoverflow.com/questions/8802320/draw-text-with-graphics-object-on-jframe#:~:text=To%20draw%20text%20on%20the,where%20this%20text%20will%20start.
 *      How to Change Font Size in drawString Java
 *          https://stackoverflow.com/questions/18249592/how-to-change-font-size-in-drawstring-java
 */

import javax.swing.*;
import java.awt.*;

/**
 * A component to draw the base of chess board to indicate grid and labels.
 */
public class BoardFrame extends JComponent {

    /**
     * Set the position and dimension of the plane.
     */
    public BoardFrame() {
        setBounds(0, 0, 860, 560);      // Set position and dimension
    }

    /**
     * Draws a dark green plane with grid labels, player turn label, and status label on it.
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        char[] rankChars = new char[] {'8', '7', '6', '5', '4', '3', '2', '1'};
        char[] fileChars = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        // base
        Color darkGreen = new Color(80, 100, 50);
        g2.setColor(darkGreen);
        g2.fillRect(0, 0, 860, 560);
        // grid label
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 22));
        for (int i=0; i<Board.MAX_ROW; i++) {
            g.drawString(String.valueOf(rankChars[i]), 13, 80+60*i);
            g.drawString(String.valueOf(fileChars[i]), 64+60*i, 27);
            g.drawString(String.valueOf(rankChars[i]), 535, 80+60*i);
            g.drawString(String.valueOf(fileChars[i]), 64+60*i, 545);
        }
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        // player turn label
        g.drawString("Player turn", 603, 45);
        // status label
        g.drawString("Status", 603, 145);
    }
}
