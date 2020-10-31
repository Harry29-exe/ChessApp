package com.kw.DocumentRepository.chess.Pieces;

import java.util.HashMap;
import java.util.Map;

public enum Piece {
    BLACK_PAWN(-1),
    BLACK_ROOK(-2),
    BLACK_KNIGHT(-3),
    BLACK_BISHOP(-4),
    BLACK_QUEEN(-5),
    BLACK_KING(-6),
    NULL(0),
    WHITE_PAWN(1),
    WHITE_ROOK(2),
    WHITE_KNIGHT(3),
    WHITE_BISHOP(4),
    WHITE_QUEEN(5),
    WHITE_KING(6);

    private static final Map<Integer, Piece> BY_CODE = new HashMap<>();

    static {
        for(Piece piece : values()) {
            BY_CODE.put(piece.code, piece);
        }
    }

    public static Piece getPieceTypeByCode(int code) {
        return BY_CODE.get(code);
    }

    public static boolean isPieceWhite(Piece piece) {
        return piece.code > 0;
    }

    public static boolean isPieceWhite(int code) {
        return code > 0;
    }

    public final int code;
    Piece(int i) {
        code = i;
    }
}
