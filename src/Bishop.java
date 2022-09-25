import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bishop extends ChessPiece{

    private BufferedImage image;

    public Bishop (boolean isBlack) {
        super(isBlack, ChessPiece.BISHOP);
        try { _initIcon(); }
        catch (IOException e) { System.out.println(e.getMessage()); }
    }

    /**
     * Check if a tile is takable by bishop which moves diagonally four directions.
     * @param tile: the tile this chess piece is on.
     * @param checkTile: tile to check.
     * @return whether if the check tile is takable or not.
     */
    @Override
    public boolean canTake(Tile tile, Tile checkTile, TileSet tileSet) {
        // move up right
        for (int i = 1 ; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()+i, tile.getFile()+i);
            if (checkTile.isAt(tile.getRank()+i, tile.getFile()+i))
                return true;
            else if (target != null && target.isEmpty()) break;
        }
        // move up left
        for (int i = 1 ; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()+i, tile.getFile()-i);
            if (checkTile.isAt(tile.getRank()+i, tile.getFile()-i))
                return true;
            else if (target != null && target.isEmpty()) break;
        }
        // move down right
        for (int i = 1 ; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()-i, tile.getFile()+i);
            if (checkTile.isAt(tile.getRank()-i, tile.getFile()+i))
                return true;
            else if (target != null && target.isEmpty()) break;
        }
        // check down left
        for (int i = 1 ; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()-i, tile.getFile()-i);
            if (checkTile.isAt(tile.getRank()-i, tile.getFile()-i))
                return true;
            else if (target != null && target.isEmpty()) break;
        }
        return false;
    }

    // -------- GRAPHICS ----------

    /**
     * Get and sets the bishop icon.
     * @throws IOException if chess piece image (chess_piece.png) not found.
     */
    private void _initIcon() throws IOException {
        image = ImageIO.read(new File("chess_piece.png"));      // Get chess piece image by type and color
        int row = isBlack() ? 1 : 0;
        image = image.getSubimage(124, row * 62, 49, 50);
        //icon = new ImageIcon(image);
        icon = new ChessIcon(image, false);
    }

    /**
     * Change icon to be surrounded by a circle to indicate the bishop is takable.
     */
    @Override
    public void setTakable() { icon = new ChessIcon(image, true); }

    /**
     * Reset the icon to be only bishop.
     */
    @Override
    public void reset() { icon = new ChessIcon(image, false); }

    /**
     * Display suggestion of the bishop can move from the tile.
     * @param tile: the tile this bishop is on.
     * @param tileSet: the set containing all the tiles.
     */
    @Override
    public void showSuggest (Tile tile, TileSet tileSet) {
        // move up right
        for (int i = 1 ; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()+i, tile.getFile()+i);
            if (target != null && target.isEmpty()) target.setMovable(true);
            else {
                if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
                break;
            }
        }
        // move up left
        for (int i = 1 ; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()+i, tile.getFile()-i);
            if (target != null && target.isEmpty()) target.setMovable(true);
            else {
                if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
                break;
            }
        }
        // move down right
        for (int i = 1 ; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()-i, tile.getFile()+i);
            if (target != null && target.isEmpty()) target.setMovable(true);
            else {
                if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
                break;
            }
        }
        // move down left
        for (int i = 1 ; i <= Board.MAX_ROW; i++) {
            Tile target = tileSet.findTile(tile.getRank()-i, tile.getFile()-i);
            if (target != null && target.isEmpty()) target.setMovable(true);
            else {
                if (target != null && !target.isEmpty() && !(target.getChessPiece().isBlack() == isBlack())) {target.setTakable();}
                break;
            }
        }
    }
}
