/**
 * Reference
 *      How to actually select a JButton | https://stackoverflow.com/questions/8187766/how-to-actually-select-a-jbutton
 */

import javax.swing.*;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;
import java.io.IOException;

/**
 * Square button that serve as tile which can have chess piece or dot on it.
 */
public class Tile extends JToggleButton {
    private final int rank;
    private final int file;
    private boolean movable;
    private ChessPiece chessPiece;
    private boolean empty;

    public Tile(int rank, int file, int side, Color color) {
        this.rank = rank;
        this.file = file;
        movable = false;
        empty = true;
        // Set the display of the tile
        setBackground(color);
        setPreferredSize(new Dimension(side, side));        // Set size
        setBounds(40+file*side, 40+rank*side, side, side);      // Set position and size
        setEnabled(false);      // empty tile is disabled
        setUI(new MetalToggleButtonUI() {           // Change color when selected
            @Override
            protected Color getSelectColor() {
                return color.darker();
            }
        });
    }

    /* MUTATOR */

    /**
     * Put a chess piece on the tile.
     * @param chessPiece: chess piece to put on the tile.
     */
    public void put(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
        setIcon(chessPiece.icon);
        empty = false;
        setEnabled(true);   // set selectable
    }

    /**
     * Take the chess piece on the tile.
     * @return the chess piece that was on the tile.
     */
    public ChessPiece take() {
        ChessPiece takePiece = chessPiece;
        setIcon(null);
        chessPiece = null;
        empty = true;
        setEnabled(false);   // set not selectable
        return takePiece;
    }

    /**
     * Set other chess piece can be moved onto this tile or not.
     * @param movable: other chess piece can be moved onto this tile or not.
     */
    public void setMovable(boolean movable) {
        this.movable = movable;
        if (movable)
            setIcon(new Dot());
        setEnabled(true);   // set button selectable
    }

    /**
     * Set other chess piece can take the chess piece on this tile or not.
     */
    public void setTakable() {
        this.getChessPiece().setTakable();
        movable = true;
        setIcon(chessPiece.icon);
        setEnabled(true);   // set button selectable
    }

    public boolean isMovable() { return movable; }

    /**
     * Reset the chess piece, icon, and to not movable.
     */
    public void reset() {
        if (chessPiece != null) {
            this.getChessPiece().reset();
            setIcon(chessPiece.icon);
        }
        else
            setIcon(null);
        movable = false;
        setEnabled(!empty);
    }

    /* ACCESSOR */

    /**
     * If the tile is at the position or not.
     * @return whether the tile is at the position or not.
     */
    public boolean isAt(int rank, int file) { return this.rank == rank && this.file == file; }

    /**
     * If the tile is empty or not.
     * @return whether the tile is empty or not.
     */
    public boolean isEmpty() { return empty; }

    /**
     * Get the chess piece that is on the tile.
     * @return the chess piece that is on the tile.
     */
    public ChessPiece getChessPiece() { return chessPiece; }

    public int getRank() { return rank; }

    public int getFile() { return file; }

    public String toString() { return "Tile: " + "[rank: " + rank + "]" + "[file: " + file + "]" + "[chess piece: " + chessPiece + "]"; }
}
