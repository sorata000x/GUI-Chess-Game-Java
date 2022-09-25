import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Pawn extends ChessPiece {

    private BufferedImage image;

    public Pawn (boolean isBlack) {
        super(isBlack, ChessPiece.PAWN);
        try {_initIcon();}
        catch (IOException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Check if a tile is takable by the pawn.
     * @param tile: the tile this chess piece is on.
     * @param checkTile: tile to check.
     * @return whether if the check tile is takable or not.
     */
    public boolean canTake(Tile tile, Tile checkTile, TileSet tileSet) {
        // up right
        int dr = this.isBlack() ? 1 : -1;
        int df = this.isBlack() ? -1 : 1;
        if (checkTile.isAt(tile.getRank()+dr, tile.getFile()+df))
            return true;
        // up left
        dr = this.isBlack() ? 1 : -1;
        df = this.isBlack() ? 1 : -1;
        if (checkTile.isAt(tile.getRank()+dr, tile.getFile()+df))
            return true;
        return false;
    }

    // -------- GRAPHICS ----------

    /**
     * Get and sets the pawn icon.
     * @throws IOException if chess piece image (chess_piece.png) not found.
     */
    private void _initIcon() throws IOException {
        image = ImageIO.read(new File("chess_piece.png"));      // Get chess piece image by type and color
        int row = isBlack() ? 1 : 0;
        image = image.getSubimage(315, row * 62, 35, 50);
        //icon = new ImageIcon(image);
        icon = new ChessIcon(image, false);
    }

    /**
     * Change icon to be surrounded by a circle to indicate the pawn is takable.
     */
    public void setTakable() {
        icon = new ChessIcon(image, true);
    }

    /**
     * Reset the icon to be only the pawn.
     */
    public void reset() { icon = new ChessIcon(image, false); }

    /**
     * Mark for possible moves and possible takes of the pawn.
     * @param tile: tile the pawn is on.
     * @param tileSet: the set contain all the tiles.
     */
    public void showSuggest (Tile tile, TileSet tileSet) {
        // ------ Suggest Move --------
        // vertically forward one square
        int dr = this.isBlack() ? 1 : -1;
        Tile target = tileSet.findTile(tile.getRank()+dr, tile.getFile());
        if (target != null && target.isEmpty()) {
            target.setMovable(true);
            // second square if not yet moved
            dr = this.isBlack() ? 2 : -2;
            target = tileSet.findTile(tile.getRank()+dr, tile.getFile());
            boolean moved = this.isBlack() ? tile.getRank() != 1 : tile.getRank() != 6;
            if (!moved && target != null && target.isEmpty()) target.setMovable(true);
        }
        // ------ Suggest Take ---------
        // up right
        dr = this.isBlack() ? 1 : -1;
        int df = this.isBlack() ? -1 : 1;
        target = tileSet.findTile(tile.getRank()+dr, tile.getFile()+df);
        if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) target.setTakable();
        // up left
        dr = this.isBlack() ? 1 : -1;
        df = this.isBlack() ? 1 : -1;
        target = tileSet.findTile(tile.getRank()+dr, tile.getFile()+df);
        if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) target.setTakable();
    }
}
