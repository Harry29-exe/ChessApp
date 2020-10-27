package com.kw.DocumentRepository.chess.ai.Pieces;

import java.util.Arrays;

public enum Pieces {
    NULL(null),

    WHITE_PAWN(new Pawn(0,0,true)),
    WHITE_ROOK(new Rook(0,0,true)),
    WHITE_KNIGHT(new Knight(0,0,true)),
    WHITE_BISHOP(new Bishop(0,0,true)),
    WHITE_QUEEN(new Queen(0,0,true)),
    WHITE_KING(new King(0,0,true)),

    BLACK_PAWN(new Pawn(0,0,false)),
    BLACK_ROOK(new Rook(0,0,false)),
    BLACK_KNIGHT(new Knight(0,0,false)),
    BLACK_BISHOP(new Bishop(0,0,false)),
    BLACK_QUEEN(new Queen(0,0,false)),
    BLACK_KING(new King(0,0,false))
    ;

    Piece piece;
    Pieces(Piece piece) {
        this.piece = piece;
    }

    Piece getPiece() {
        return piece;
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println(Arrays.toString(Pieces.values()));
    }
}
