import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Rook extends ChessPiece{

    private BufferedImage image;

    public Rook (boolean isBlack) {
        super(isBlack, ChessPiece.ROOK);
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
        // move up
        for (int i = 1; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()-i, tile.getFile()-i);
            if (checkTile.isAt(tile.getRank()-i, tile.getFile()))
                return true;
            else if (target != null && !target.isEmpty() && !(target.getChessPiece() instanceof King)) break;
        }
        // move down
        for (int i = 1; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()+i, tile.getFile());
            if (checkTile.isAt(tile.getRank()+i, tile.getFile()))
                return true;
            else if (target != null && !target.isEmpty() && !(target.getChessPiece() instanceof King)) break;
        }
        // move right
        for (int j = 1; j <= Board.MAX_COL; j++) {
            Tile target = tileSet.findTile(tile.getRank(), tile.getFile()+j);
            if (checkTile.isAt(tile.getRank(), tile.getFile()+j))
                return true;
            else if (target != null && !target.isEmpty() && !(target.getChessPiece() instanceof King)) break;
        }
        // move left
        for (int j = 1; j <= Board.MAX_COL; j++) {
            Tile target = tileSet.findTile(tile.getRank(), tile.getFile()-j);
            if (checkTile.isAt(tile.getRank(), tile.getFile()-j))
                return true;
            else if (target != null && !target.isEmpty() && !(target.getChessPiece() instanceof King)) break;
        }
        // move up right
        for (int i = 1 ; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()+i, tile.getFile()+i);
            if (checkTile.isAt(tile.getRank()+i, tile.getFile()+i))
                return true;
            else if (target != null && !target.isEmpty() && !(target.getChessPiece() instanceof King)) break;
        }
        return false;
    }

    // -------- GRAPHICS ----------

    /**
     * Get and sets the king icon.
     * @throws IOException if chess piece image (chess_piece.png) not found.
     */
    private void _initIcon() throws IOException {
        image = ImageIO.read(new File("chess_piece.png"));      // Get chess piece image by type and color
        int row = isBlack() ? 1 : 0;
        image = image.getSubimage(248, row * 62, 49, 50);
        //icon = new ImageIcon(image);
        icon = new ChessIcon(image, false);
    }

    /**
     * Change icon to be surrounded by a circle to indicate the King is takable.
     */
    public void setTakable() { icon = new ChessIcon(image, true); }

    /**
     * Reset the icon to be only the King.
     */
    public void reset() { icon = new ChessIcon(image, false); }

    /**
     * Mark for possible moves and possible takes of the king.
     * @param tile: tile the king is on.
     * @param tileSet: the set contain all the tiles.
     */
    public void showSuggest (Tile tile, TileSet tileSet) {
        // move up
        for (int i = 1; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()-i, tile.getFile());
            if (target != null && target.isEmpty()) target.setMovable(true);
            else {
                if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
                break;
            }
        }
        // move down
        for (int i = 1; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()+i, tile.getFile());
            if (target != null && target.isEmpty()) target.setMovable(true);
            else {
                if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
                break;
            }
        }
        // move right
        for (int j = 1; j <= Board.MAX_COL; j++) {
            Tile target = tileSet.findTile(tile.getRank(), tile.getFile()+j);
            if (target != null && target.isEmpty()) target.setMovable(true);
            else {
                if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
                break;
            }
        }
        // move left
        for (int j = 1; j <= Board.MAX_COL; j++) {
            Tile target = tileSet.findTile(tile.getRank(), tile.getFile()-j);
            if (target != null && target.isEmpty()) target.setMovable(true);
            else {
                if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
                break;
            }
        }
    }
}
