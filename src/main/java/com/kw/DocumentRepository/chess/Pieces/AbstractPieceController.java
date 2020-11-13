package com.kw.DocumentRepository.chess.Pieces;

import com.kw.DocumentRepository.chess.Board;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractPieceController implements PieceController {
    protected Board board;
    protected Piece piece;
    protected List<Board> possibleBoards;
    protected int x;
    protected int y;
    protected boolean isWhite;

    protected void init(int x, int y, Board board) {
        this.board = board;
        this.piece = Piece.getPieceTypeByCode(board.getPieceAt(x, y));
        this.possibleBoards = new LinkedList<>();
        this.x = x;
        this.y = y;
        this.isWhite = Piece.isPieceWhite(piece);
    }

    protected void cleanup() {
        this.board = null;
        this.piece = null;
        this.possibleBoards = null;
    }

    protected boolean indexIsCorrect(int i) {
        return i < 8 && i >= 0;
    }

    protected boolean coordinatesAreCorrect(int x, int y) {
        return (x >= 0 && x < 8) && (y >= 0 && y < 8);
    }

}
