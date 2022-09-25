/**
 * Reference
 *      How to add background Image to JFrame in Java | https://www.tutorialspoint.com/how-to-add-background-image-to-jframe-in-java
 *      Class JLayeredPane | https://docs.oracle.com/javase/7/docs/api/javax/swing/JLayeredPane.html
 *      Play Chess Online | https://www.chess.com/
 */

import javax.swing.*;
import java.awt.*;

public class Board extends JLayeredPane {

    public static final int MIN_ROW = 1;
    public static final int MIN_COL = 1;
    public static final int MAX_ROW = 8;
    public static final int MAX_COL = 8;

    /**
     * Display all elements of the chess board (base, labels, tiles, chess pieces, turn display, and status display).
     */
    public Board() {
        setPreferredSize(new Dimension(860, 560));
        Listener listener = new Listener();
        TileSet tileSet = new TileSet(listener);
        listener.setTileSet(tileSet);
        // add elements
        add(new BoardFrame(), JLayeredPane.DEFAULT_LAYER);
        add(tileSet, JLayeredPane.DEFAULT_LAYER);
        add(listener.getTurnDisplay(), JLayeredPane.MODAL_LAYER);
        add(listener.getGameOverDisplay(), JLayeredPane.MODAL_LAYER);
    }

    /**
     * Add each tile (JToggleButton) in the tile set to display on the board.
     * @param tileSet: set of tiles to add.
     * @param layer: the layer of the tiles going to be added on.
     */
    public void add(TileSet tileSet, int layer) {
        for (JToggleButton b : tileSet) {
            add(b, layer);
        }
    }
}
