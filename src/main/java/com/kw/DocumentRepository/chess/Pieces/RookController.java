package com.kw.DocumentRepository.chess.Pieces;

import com.kw.DocumentRepository.chess.Board;

import java.util.List;

public class RookController implements PieceController {
    private Board board;
    private Piece piece;
    private List<Board> boards;
    private int x;
    private int y;
    private boolean isWhite;

    @Override
    public List<Board> getPossibleBoardStates(int x, int y, Board board) {
        return null;
    }
}
