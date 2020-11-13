package com.kw.DocumentRepository.chess.pieces;

import com.kw.DocumentRepository.chess.Board;
import com.kw.DocumentRepository.chess.Pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static com.kw.DocumentRepository.chess.Board.*;
import static com.kw.DocumentRepository.chess.Pieces.Piece.*;

public class PieceTestUtils {

    public final Piece[] whitePieces = new Piece[]{WHITE_PAWN, WHITE_ROOK, WHITE_KNIGHT, WHITE_BISHOP, WHITE_QUEEN, WHITE_KING};
    public final Piece[] blackPieces = new Piece[]{BLACK_PAWN, BLACK_ROOK, BLACK_KNIGHT, BLACK_BISHOP, BLACK_QUEEN, BLACK_KING};

    public Board createEmptyBoard() {
        Integer[][] pieces = new Integer[8][8];

        for(int x = 0; x < 8; x++)
            for(int y = 0; y < 8; y++)
                pieces[y][x] = 0;

        return new Board(pieces, true,
                new Castling(false, false, false, false), new ElPassantBeatingAvailableAt(-1,-1) );
    }

    public Board createStartBoard() {
        return new Board(new Integer[][]{
                {-2,-3,-4,-5,-6,-4,-3,-2},
                {-1,-1,-1,-1,-1,-1,-1,-1},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1,1},
                {2,3,4,5,6,4,3,2}
                }, true,
                new Castling(true,true,true,true), new ElPassantBeatingAvailableAt(-1,-1));
    }

    public List<Board> createBoardsWithOnePiece(int pieceCode, XY... pieceXYPosition) {
        List<Board> boards = new ArrayList<>();
        for(int i = 0; i < pieceXYPosition.length; i++) {
            Board newBoard = this.createEmptyBoard();
            newBoard.setPieceAt(pieceXYPosition[i].x, pieceXYPosition[i].y, pieceCode);
            boards.add(newBoard);
        }

        return boards;
    }

    public List<Board> createNewBoardsWithMovedPiece(Board originalBoard, int fromX, int fromY, XY... toXY) {
        List<Board> boards = new ArrayList<>();
        int pieceCode = originalBoard.getPieceAt(fromX, fromY);
        for(XY xy: toXY) {
            Board board = originalBoard.clone();
            board.setPieceAt(fromX, fromY, NULL.code);
            board.setPieceAt(xy.x, xy.y, pieceCode);
            boards.add(board);
        }

        return boards;
    }

    public boolean boardEquals(Board b1, Board b2) {
        boolean equals = true;
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                equals = b1.getPieceAt(x, y).equals(b2.getPieceAt(x, y)) && equals;
            }
        }
        equals = b1.getIsItWhitesTurn() == b2.getIsItWhitesTurn() && equals;

        Castling b1C = b1.getCastling();
        Castling b2C = b2.getCastling();

        equals = (b1C.isBlackLong() == b2C.isBlackLong() && b1C.isBlackShort() == b2C.isBlackShort() &&
                b1C.isWhiteLong() == b2C.isWhiteLong() && b1C.isWhiteShort() == b2C.isWhiteShort())  && equals;

        equals = b1.getElPassantBeatingAvailableAt().getX() == b2.getElPassantBeatingAvailableAt().getX() && equals;
        equals = b1.getElPassantBeatingAvailableAt().getY() == b2.getElPassantBeatingAvailableAt().getY() && equals;

        return equals;
    }


    public boolean boardListEquals(List<Board> l1, List<Board> l2) {
        if(l1.size() != l2.size())
            return false;

        for(Board b1 : l1) {
            boolean b1InL2 = false;
            for(Board b2 : l2) {
                if(boardEquals(b1,b2)) {
                    b1InL2 = true;
                    l2.remove(b2);
                    break;
                }
            }

            if(!b1InL2)
                return false;
        }

        return true;
    }
    //public List<Board>

    public static class XY {
        public final int x;
        public final int y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public static XY xy(int x, int y) {
            return new XY(x,y);
        }
    }
}
