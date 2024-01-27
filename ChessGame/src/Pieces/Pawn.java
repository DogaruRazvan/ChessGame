package Pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class Pawn extends Piece {

    public Pawn(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.x = col * board.tileSize;
        this.y = row * board.tileSize;

        this.isWhite = isWhite;
        this.name = "Pawn";

        this.sprite = sheet.getSubimage(5 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale)
                .getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);
    }
    public boolean isValidMovement(int col, int row) {
        int colorIndex = isWhite ? 1 : -1;

        // push pawn 1
        if (this.col == col && row == this.row - colorIndex && board.getPiece(col, row) == null) {
            return true;
        }

        // push pawn 2
        if (isFirstMove && this.col == col && row == this.row - colorIndex * 2 && board.getPiece(col, row) == null && board.getPiece(col, row + colorIndex) == null) {
            return true;
        }

        // capture left
        if (col == this.col - 1 && row == this.row - colorIndex && board.getPiece(col, row) != null) {
            return true;
        }

        // capture right
        if (col == this.col + 1 && row == this.row - colorIndex && board.getPiece(col, row) != null) {
            return true;
        }

        // En Passant
        if (board.enPassantTile != -1
                && (col == this.col - 1 || col == this.col + 1)
                && row == this.row - colorIndex
                && board.getPiece(col, row + colorIndex) != null
                && board.getPiece(col, row + colorIndex).isWhite != isWhite) {
            return true;
        }

        return false;

    }
    



}
