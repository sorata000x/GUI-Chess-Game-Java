import javax.swing.*;

public abstract class ChessPiece {
    public static final int QUEEN = 0;
    public static final int KING = 1;
    public static final int BISHOP = 2;
    public static final int KNIGHT = 3;
    public static final int ROOK = 4;
    public static final int PAWN = 5;

    private final boolean isBlack;
    private final int type;
    public Icon icon;

    public ChessPiece(boolean isBlack, int type) {
        this.isBlack = isBlack;
        this.type = type;
    }

    /**
     * Check if a tile is takable by the chess piece.
     * @param tile: the tile this chess piece is on.
     * @param checkTile: tile to check.
     * @return whether if the check tile is takable or not.
     */
    public abstract boolean canTake (Tile tile, Tile checkTile, TileSet tileSet);

    /**
     * Display suggestion of the chess piece can move from the tile.
     * @param tile: the tile this chess piece is on.
     * @param tileSet: the set containing all the tiles.
     */
    public abstract void showSuggest(Tile tile, TileSet tileSet);

    /**
     * Change icon to be surrounded by a circle to indicate the bishop is takable.
     */
    public abstract void setTakable();

    /**
     * Reset the icon to be only the chess piece.
     */
    public abstract void reset();

    /* ACCESSOR */

    public boolean isBlack() { return isBlack; }
}
