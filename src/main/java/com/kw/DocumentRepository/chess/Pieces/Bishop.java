package com.kw.DocumentRepository.chess.Pieces;

import com.kw.DocumentRepository.chess.JSONBoard;

import java.util.LinkedList;
import java.util.List;

import static com.kw.DocumentRepository.chess.Pieces.Piece.*;

public class Bishop implements PieceController {
    private JSONBoard board;
    private Piece piece;
    private List<JSONBoard> boards;
    private int x;
    private int y;
    private boolean isWhite;

    @Override
    public List<JSONBoard> getPossibleBoardStates(int x, int y, JSONBoard board) {
        this.board = board;
        this.piece = Piece.getPieceTypeByCode(board.getPieceAt(x, y));
        this.boards = new LinkedList<>();
        this.x = x;
        this.y = y;
        this.isWhite = Piece.isPieceWhite(piece);

        iterate(-1,-1);
        iterate(-1,1);
        iterate(1,1);
        iterate(1,-1);

        this.board = null;
        this.piece = null;

        List<JSONBoard> temp = boards;
        boards = null;
        return temp;
    }

    private void iterate(int xChange, int yChange) {
        int xToCheck = x + xChange;
        int yToCheck = y + yChange;
        while( indexIsCorrect(xToCheck) && indexIsCorrect(yToCheck) ) {
            int piece = board.getPieceAt(xToCheck, yToCheck);
            if(piece == NULL.code) {
                JSONBoard b = board.clone();
                b.setPieceAt(x, y, NULL.code);
                b.setPieceAt(xToCheck, yToCheck, isWhite? WHITE_BISHOP.code : BLACK_BISHOP.code);
                boards.add(b);
                xToCheck += xChange;
                yToCheck += yChange;
            } else if (isWhite != isPieceWhite(piece)) {
                JSONBoard b = board.clone();
                b.setPieceAt(x,y, NULL.code);
                b.setPieceAt(xToCheck, yToCheck, isWhite? WHITE_BISHOP.code : BLACK_BISHOP.code);
                boards.add(b);
                return;
            } else {
                return;
            }
        }
    }

    private boolean indexIsCorrect(int i) {
        return i < 8 && i >= 0;
    }
}
