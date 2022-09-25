/**
 * Reference
 *      Creates read-only (non-editable) JTextField | https://examples.javacodegeeks.com/desktop-java/swing/jtextfield/create-read-only-non-editable-jtextfield/
 *      Center Text In JTextField | https://www.dreamincode.net/forums/topic/178775-center-text-in-jtextfield/
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener to handle events when player click on the tile.
 */
public class Listener implements ActionListener {
    private Tile from;
    private TileSet tileSet;
    private boolean whiteTurn;
    private JTextField turnDisplay;     // display which player's turn it is
    private JTextField gameOverDisplay;     // display game status

    public Listener() {
        whiteTurn = true;   // white goes first
        _initTurnDisplay();
        _initGameOverDisplay();
    }

    private void _initTurnDisplay() {
        turnDisplay = new JTextField();
        turnDisplay.setEnabled(false);
        turnDisplay.setDisabledTextColor(Color.BLACK);
        turnDisplay.setText("White Play");
        turnDisplay.setFont(new Font("Arial", Font.BOLD, 26));
        turnDisplay.setHorizontalAlignment(JTextField.CENTER);
        turnDisplay.setBounds(600, 50, 200, 60);
    }

    private void _initGameOverDisplay() {
        gameOverDisplay = new JTextField();
        gameOverDisplay.setEnabled(false);
        gameOverDisplay.setDisabledTextColor(Color.BLACK);
        gameOverDisplay.setText("In Progress");
        gameOverDisplay.setFont(new Font("Arial", Font.PLAIN, 26));
        gameOverDisplay.setHorizontalAlignment(JTextField.CENTER);
        gameOverDisplay.setBounds(600, 150, 200, 60);
    }

    /**
     * Execute events when tile being clicked and proceed for game play.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Tile target = (Tile) e.getSource();
        // (nothing selected), (empty tile), (invalid target)
        if ((from == null || from.isEmpty() || !target.isMovable()) && !target.isEmpty() && !target.getChessPiece().isBlack() && whiteTurn
                || (from == null || from.isEmpty() || !target.isMovable()) && !target.isEmpty() && target.getChessPiece().isBlack() && !whiteTurn) {      // set chess piece if none has selected
            from = target;
            tileSet.resetTile();        // clean up old suggestion

            boolean isKing = target.getChessPiece() instanceof King;
            boolean notInCheck = whiteTurn && tileSet.nothingInCheckFor(tileSet.getKing(false), false)
                    || !whiteTurn && tileSet.nothingInCheckFor(tileSet.getKing(true), true);

            if (!target.isEmpty() && (isKing || notInCheck)) {
                target.getChessPiece().showSuggest(target, tileSet);        // display suggestion
            }
        }
        else if (from != null && target.isMovable()) {    // (when something is selected), (valid target)
            ChessPiece movePiece = from.take();
            target.put(movePiece);
            from = null;
            tileSet.resetTile();        // clean up suggestion after place
            whiteTurn = !whiteTurn;     // change turn

            // Checkmate
            // condition 1: in check and king cannot move
            // condition 2: king last standing and cannot move
            boolean isBlack = !whiteTurn;
            Tile king = tileSet.getKing(isBlack);
            king.getChessPiece().showSuggest(king, tileSet);

            if (!tileSet.nothingInCheckFor(king, isBlack) && tileSet.noAvailableMove()
                || !tileSet.nothingInCheckFor(king, isBlack) && tileSet.lastStanding()) {
                if (isBlack)
                    gameOverDisplay.setText("Black Won");
                else
                    gameOverDisplay.setText("White Won");
            }
            tileSet.resetTile();        // clean up suggestion
        }
        else { tileSet.resetTile(); }

        // change turn
        if (whiteTurn)
            turnDisplay.setText("White Play");
        else
            turnDisplay.setText("Black Play");
    }

    public void setTileSet(TileSet tileSet) { this.tileSet = tileSet; }

    public JTextField getTurnDisplay() { return turnDisplay; }

    public JTextField getGameOverDisplay() { return gameOverDisplay; }
}
