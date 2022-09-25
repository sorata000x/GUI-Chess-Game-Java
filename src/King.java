import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class King extends ChessPiece{

    private BufferedImage image;

    public King (boolean isBlack) {
        super(isBlack, ChessPiece.KING);
        try { _initIcon(); }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Check if a tile is takable by the King.
     * @param tile: the tile this chess piece is on.
     * @param checkTile: tile to check.
     * @return whether if the check tile is takable or not.
     */
    public boolean canTake(Tile tile, Tile checkTile, TileSet tileSet) {
        // up
        if (checkTile.isAt(tile.getRank()-1, tile.getFile()))
            return true;
        // down
        if (checkTile.isAt(tile.getRank()+1, tile.getFile()))
            return true;
        // right
        if (checkTile.isAt(tile.getRank(), tile.getFile()+1))
            return true;
        // left
        if (checkTile.isAt(tile.getRank(), tile.getFile()-1))
            return true;
        // up right
        if (checkTile.isAt(tile.getRank()-1, tile.getFile()+1))
            return true;
        // up left
        if (checkTile.isAt(tile.getRank()-1, tile.getFile()-1))
            return true;
        // down right
        if (checkTile.isAt(tile.getRank()+1, tile.getFile()+1))
            return true;
        // down left
        if (checkTile.isAt(tile.getRank()+1, tile.getFile()-1))
            return true;
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
        image = image.getSubimage(0, row * 62, 49, 50);
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
        // ------ Move up --------
        Tile target = tileSet.findTile(tile.getRank()+1, tile.getFile());
        // if ([target exist] and [is empty]) -> set target movable
        // else if ([target exist] and [is occupied] and [not same color as this chess piece]) -> set target takable
        if (target != null && target.isEmpty() && tileSet.nothingInCheckFor(target, isBlack())) {
            target.setMovable(true);
        }
        else if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
        // ------ Move down --------
        target = tileSet.findTile(tile.getRank()-1, tile.getFile());
        if (target != null && target.isEmpty() && tileSet.nothingInCheckFor(target, isBlack())) target.setMovable(true);
        else if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
        // ------ Move right --------
        target = tileSet.findTile(tile.getRank(), tile.getFile()+1);
        if (target != null && target.isEmpty() && tileSet.nothingInCheckFor(target, isBlack())) target.setMovable(true);
        else if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
        // ------ Move left --------
        target = tileSet.findTile(tile.getRank(), tile.getFile()-1);
        if (target != null && target.isEmpty() && tileSet.nothingInCheckFor(target, isBlack())) target.setMovable(true);
        else if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
        // ------ Move up right --------
        target = tileSet.findTile(tile.getRank()-1, tile.getFile()+1);
        if (target != null && target.isEmpty() && tileSet.nothingInCheckFor(target, isBlack())) target.setMovable(true);
        else if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
        // ------ Move up left ---------
        target = tileSet.findTile(tile.getRank()-1, tile.getFile()-1);
        if (target != null && target.isEmpty() && tileSet.nothingInCheckFor(target, isBlack())) target.setMovable(true);
        else if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
        // ------ Move down right ------
        target = tileSet.findTile(tile.getRank()+1, tile.getFile()+1);
        if (target != null && target.isEmpty() && tileSet.nothingInCheckFor(target, isBlack())) target.setMovable(true);
        else if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
        // ------ Move down left ---------
        target = tileSet.findTile(tile.getRank()+1, tile.getFile()-1);
        if (target != null && target.isEmpty() && tileSet.nothingInCheckFor(target, isBlack())) target.setMovable(true);
        else if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
    }
}
