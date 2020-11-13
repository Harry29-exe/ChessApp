package com.kw.DocumentRepository.chess.ai;

import com.kw.DocumentRepository.chess.Board;
import com.kw.DocumentRepository.chess.Pieces.Piece;

public class BaseBoardEvaluator implements BoardEvaluator {

    @Override
    public double evaluateBoard(Board board) {
        double evaluation = 0;
        boolean whiteKingStillOnBoard = false;
        boolean blackKingStillOnBoard = false;

        for(Integer[] row : board.getPieces()) {
            for(Integer piece : row) {
                switch (Piece.getPieceTypeByCode(piece)) {
                    case BLACK_PAWN:
                        evaluation -= 1;
                        break;
                    case BLACK_ROOK:
                    case BLACK_BISHOP:
                        evaluation -= 3;
                        break;
                    case BLACK_KNIGHT:
                        evaluation -=2;
                        break;
                    case BLACK_QUEEN:
                        evaluation -= 5;
                        break;
                    case BLACK_KING:
                        blackKingStillOnBoard = true;
                        break;
                    case WHITE_PAWN:
                        evaluation += 1;
                        break;
                    case WHITE_ROOK:
                    case WHITE_BISHOP:
                        evaluation += 3;
                        break;
                    case WHITE_KNIGHT:
                        evaluation += 2;
                        break;
                    case WHITE_QUEEN:
                        evaluation += 5;
                        break;
                    case WHITE_KING:
                        whiteKingStillOnBoard = true;
                        break;
                    case NULL:
                        break;
                }
            }
        }

        if(!blackKingStillOnBoard) {
            evaluation = 1_000;
        } else if(!whiteKingStillOnBoard) {
            evaluation = -1_000;
        }

        return evaluation;
    }


}
