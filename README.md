## Final Project: Chess Game

Author: Sora Chen <br>
Date: 05/01/2022 <br>
Class: CS3B

### Preview

<image src="chess_demo.gif" width="500"/>

### Description

This program implements a chess game which is playable by two players and includes the
graphical user interface. This game consists of a board of 8x8 grid, two sets of 16 chess pieces
(includes king, queen, bishop, knight, rook, and pawn), a field indicating the player’s turn, and
another field indicating the status. Players can move the chess pieces by clicking on the tile it is
located on, and then click on another tile the piece can move to. Possible moves are indicated by black
dots on the tiles. Victory occurs when one of the players is able to ‘checkmate’ another player, which
is when another player’s king cannot escape from the player’s check (when one of the player’s pieces
can take the king). Players will not be able to make a move that ignores the check or result in a check.

### User Guide

Dependency: javax

Please execute the `ChessGame.java` file under `Final Project_Code \ src` to run the game.
Be sure all the other supplied classes are in the same directory, and `chess_piece.png` has to be in the
project directory.
After executing the program, a window will pop up and the user can play the game by clicking on the
tiles and moving the chess pieces.
All javadocs html files are included in the `Final Project_Code \ javadocs` folder.

### Test Plan

These are what will be tested in the program:
- Does all the chess piece move in the intended way
- Does the suggestion of the chess move shows correctly
- When one of the player win
  How to test them:
- Try to move each chess piece in several locations and see if they are not able to
  move to the location where they cannot, and see if the suggestions are valid.
- Proceed the game until one of the players (white and black) wins the game.
