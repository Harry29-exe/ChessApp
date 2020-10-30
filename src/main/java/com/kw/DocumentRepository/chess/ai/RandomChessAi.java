package com.kw.DocumentRepository.chess.ai;

import com.kw.DocumentRepository.chess.Board;
import com.kw.DocumentRepository.chess.Move;
import com.kw.DocumentRepository.chess.Pieces.Piece;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomChessAi implements ChessAI {

    @Override
    public Move getBestMove(Board board, boolean aIPlaysAsWhite) {
        List<Move> availableMoves = new LinkedList<>();
        if(board.isItIsWhitePlayersTurn()) {
            List<Piece> aIPieces = board.getWhitePieces();
            for(Piece piece : aIPieces) {
                availableMoves.addAll(piece.getPossibleMoves(board));
            }
        } else {
            List<Piece> aIPieces = board.getBlackPieces();
            for(Piece piece : aIPieces) {
                availableMoves.addAll(piece.getPossibleMoves(board));
            }
        }

        Random random = new Random();
        return availableMoves.get(random.nextInt(availableMoves.size()));
    }
}
