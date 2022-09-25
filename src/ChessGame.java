/**
 * Description
 *      The program implements a chess game which is playable by two players and includes the graphical
 *      user interface. This game consists of a board of 8x8 grid, two sets of 16 chess pieces (includes
 *      king, queen, bishop, knight, rook, and pawn), a field indicating the player’s turn, and another
 *      field indicating the status. Players can move the chess pieces by clicking on the tile it is located
 *      on, and then click on another tile the piece can move to. Victory occurs when one of the players is
 *      able to ‘checkmate’ another player, which is when another player’s king cannot escape from the player’s
 *      check (when one of the player’s pieces can take the king).
 * Reference
 *      The Fastest Checkmates in Chess | https://www.youtube.com/watch?v=5qY3aIp4sTw&ab_channel=Chess.com
 */

import javax.swing.*;

public class ChessGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        Board board = new Board();
        board.setLayout(null);
        frame.setContentPane(board);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
