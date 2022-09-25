import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Knight extends ChessPiece{

    // Possible move patterns of knight
    int PATTERN = 8;
    int[] MOVE_ROW = new int[] {2,  2, 1,  1, -1, -1, -2, -2};
    int[] MOVE_COL = new int[] {1, -1, 2, -2,  2, -2,  1, -1};

    private BufferedImage image;

    public Knight (boolean isBlack) {
        super(isBlack, ChessPiece.KNIGHT);
        try { _initIcon(); }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Check if a tile is takable by this chess piece.
     * @param tile: the tile this chess piece is on.
     * @param checkTile: tile to check.
     * @return whether if the check tile is takable or not.
     */
    public boolean canTake(Tile tile, Tile checkTile, TileSet tileSet) {
        for (int i = 0; i < PATTERN; i++) {
            int up = MOVE_ROW[i];
            int right = MOVE_COL[i];
            if (checkTile.isAt(tile.getRank()+up, tile.getFile()+right))
                return true;
        }
        return false;
    }

    // -------- GRAPHICS ----------

    /**
     * Get and sets the knight icon.
     * @throws IOException if chess piece image (chess_piece.png) not found.
     */
    private void _initIcon() throws IOException {
        image = ImageIO.read(new File("chess_piece.png"));      // Get chess piece image by type and color
        int row = isBlack() ? 1 : 0;
        image = image.getSubimage(186, row * 62, 49, 50);
        //icon = new ImageIcon(image);
        icon = new ChessIcon(image, false);
    }

    /**
     * Change icon to be surrounded by a circle to indicate the Knight is takable.
     */
    public void setTakable() { icon = new ChessIcon(image, true); }

    /**
     * Reset the icon to be only the Knight.
     */
    public void reset() { icon = new ChessIcon(image, false); }

    /**
     * Mark for possible moves and possible takes of the knight.
     * @param tile: tile the knight is on.
     * @param tileSet: the set contain all the tiles.
     */
    public void showSuggest (Tile tile, TileSet tileSet) {
        // add to move list if each pattern is possible
        for (int i = 0; i < PATTERN; i++) {
            int up = MOVE_ROW[i];
            int right = MOVE_COL[i];
            Tile target = tileSet.findTile(tile.getRank()+up, tile.getFile()+right);
            if (target != null && target.isEmpty()) target.setMovable(true);
            else if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack()))
                {target.setTakable();}
        }
    }
}
