/**
 * Reference
 *      RGB Color Codes Chart | https://www.rapidtables.com/web/color/RGB_Color.html
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * An array list consist of all the tiles that will be displayed on the board.
 */
public class TileSet extends ArrayList<Tile> {

    private final ActionListener listener;
    private final ButtonGroup tileGroup;

    public TileSet(ActionListener listener) {
        this.listener = listener;
        tileGroup = new ButtonGroup();
        _initTile();
        _initChessPiece();
    }

    /**
     * Initialize and add tiles to this tile set.
     */
    private void _initTile() {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                Color color;
                if ((i+j)%2 == 0) { color = new Color(114, 144, 69); }      // set to green for even tile
                else { color = new Color(255, 255, 238); }          // set to white for odd tile
                Tile tile = new Tile(i, j, 60, color);
                tile.addActionListener(listener);
                tileGroup.add(tile);
                add(tile);
            }
        }
    }

    /**
     * Put chess pieces to their initial positions.
     */
    private void _initChessPiece() {
        // Pawn
        for (int i = 0; i < 8; i++) {
            findTile(1, i).put(new Pawn(true));
            findTile(6, i).put(new Pawn(false));
        }
        // Rook
        findTile(0, 0).put(new Rook(true));
        findTile(0, 7).put(new Rook(true));
        findTile(7, 0).put(new Rook(false));
        findTile(7, 7).put(new Rook(false));
        // Knight
        findTile(0, 1).put(new Knight(true));
        findTile(0, 6).put(new Knight(true));
        findTile(7, 1).put(new Knight(false));
        findTile(7, 6).put(new Knight(false));
        // Bishop
        findTile(0, 2).put(new Bishop(true));
        findTile(0, 5).put(new Bishop(true));
        findTile(7, 2).put(new Bishop(false));
        findTile(7, 5).put(new Bishop(false));
        // Queen
        findTile(0, 3).put(new Queen(true));
        findTile(7, 3).put(new Queen(false));
        // King
        findTile(0, 4).put(new King(true));
        findTile(7, 4).put(new King(false));
    }

    /**
     * Finds the tile at the specific rank and file.
     * @return tile at the specific rank and file, null if none.
     */
    public Tile findTile(int rank, int file) {
        for (Tile tile : this) {
            if (tile.isAt(rank, file))
                return tile;
        }
        return null;
    }

    /**
     * Reset all the tile.
     */
    public void resetTile() {
        for (Tile tile : this) {
            tile.reset();
        }
    }

    /**
     * Check if any chess piece can take it if a chess piece is on the tile.
     * @param target: the tile to check.
     * @param isBlack: the color of the chess piece.
     * @return whether any chess piece can take it if a chess piece is on the tile.
     */
    public boolean nothingInCheckFor(Tile target, boolean isBlack) {
        for (Tile tile : this) {
            if(!tile.isEmpty() && tile.getChessPiece().canTake(tile, target, this)
                && (tile.getChessPiece().isBlack() != isBlack)) {
                return false;
            }
        }
        return true;
    }

    /**
     * See if there is any movable tile in the set. (use after set suggest move of a chess piece)
     * (mainly checking for checkmate).
     * @return whether there is any movable tile in the set.
     */
    public boolean noAvailableMove() {
        for (Tile tile : this) {
            if (tile.isMovable()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Find the tile of the king (black or white) is on and return it.
     * @param isBlack: color of the king to find.
     * @return the tile of the king (black or white) is on.
     */
    public Tile getKing(boolean isBlack) {
        for (Tile tile : this) {
            if(tile.getChessPiece() instanceof King && tile.getChessPiece().isBlack() == isBlack) {
                return tile;
            }
        }
        return null;
    }

    /**
     * Check if king is the last piece standing.
     * @return whether king is the last piece standing.
     */
    public boolean lastStanding() {
        for (Tile tile : this) {
            if (!tile.isEmpty() && !(tile.getChessPiece() instanceof King))
                return false;
        }
        return true;
    }
}
