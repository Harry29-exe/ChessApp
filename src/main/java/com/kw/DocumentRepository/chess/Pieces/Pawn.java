package com.kw.DocumentRepository.chess.Pieces;

import com.kw.DocumentRepository.chess.JSONBoard;

import java.util.LinkedList;
import java.util.List;

import static com.kw.DocumentRepository.chess.Pieces.Piece.*;

public class Pawn implements PieceController {
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

        checkMoveForward();
        checkBeatings();

        this.board = null;
        this.piece = null;

        List<JSONBoard> temp = boards;
        boards = null;
        return temp;
    }

    private void checkMoveForward() {
        int yVar = isWhite ? -1 : 1;
        int yStart = isWhite ? 6 : 1;
        int yEnd = isWhite ? 1 : 6;

        if(y == yStart && board.getPieceAt(x,y + yVar) == NULL.code && board.getPieceAt(x,y + 2*yVar) == NULL.code) {
            JSONBoard b1 = board.clone();
            b1.setPieceAt(x,y, NULL.code);
            b1.setPieceAt(x,y+yVar, piece.code);
            boards.add(b1);
            JSONBoard b2 = board.clone();
            b2.setPieceAt(x,y, NULL.code);
            b2.setPieceAt(x,y+2*yVar, piece.code);
            boards.add(b2);
        } else if(y == yEnd && board.getPieceAt(x,y + yVar) == NULL.code) {
            promoteMove(x, y+yVar);
        } else if(board.getPieceAt(x,y + yVar) == NULL.code) {
            JSONBoard b = board.clone();
            b.setPieceAt(x,y, NULL.code);
            b.setPieceAt(x,y+yVar, piece.code);
            boards.add(b);
        }
    }

    private void checkBeatings() {
        int yVar = isWhite ? -1 : 1;
        int yStart = isWhite ? 6 : 1;
        int yEnd = isWhite ? 1 : 6;

        Piece pieceToBeat;
        if((x+1 >= 0 && x+1 < 8)) {
            pieceToBeat = getPieceTypeByCode(board.getPieceAt(x+1,y+yVar));
            if(pieceToBeat != NULL && !isPieceWhite(piece) == isPieceWhite(pieceToBeat)) {
                if (y == yEnd) {
                    promoteMove(x + 1, y + yVar);
                } else {
                    JSONBoard b = board.clone();
                    b.setPieceAt(x, y, NULL.code);
                    b.setPieceAt(x + 1, y + yVar, isWhite ? WHITE_PAWN.code : BLACK_PAWN.code);
                    boards.add(b);
                }
            }
        }


        if((x-1 >= 0 && x-1 < 8)) {
            pieceToBeat = getPieceTypeByCode(board.getPieceAt(x - 1, y + yVar));
            if( pieceToBeat != NULL && !isPieceWhite(piece) == isPieceWhite(pieceToBeat)) {
                if (y == yEnd) {
                    promoteMove(x - 1, y + yVar);
                } else {
                    JSONBoard b = board.clone();
                    b.setPieceAt(x, y, NULL.code);
                    b.setPieceAt(x - 1, y + yVar, isWhite ? WHITE_PAWN.code : BLACK_PAWN.code);
                    boards.add(b);
                }
            }
        }
    }

    private void promoteMove(int onX, int onY) {
        if(isWhite) {
            for(int p = WHITE_ROOK.code; p < WHITE_KING.code; p++) {
                JSONBoard b = board.clone();
                b.setPieceAt(x,y, NULL.code);
                b.setPieceAt(onX, onY, p);
                boards.add(b);
            }
        } else {
            for(int p = BLACK_ROOK.code; p > BLACK_KING.code; p--) {
                JSONBoard b = board.clone();
                b.setPieceAt(x,y, NULL.code);
                b.setPieceAt(onX, onY, p);
                boards.add(b);
            }
        }
    }

}
