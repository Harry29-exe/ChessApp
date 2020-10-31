package com.kw.DocumentRepository.chess.ai;

import com.kw.DocumentRepository.chess.JSONBoard;
import com.kw.DocumentRepository.chess.Pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kw.DocumentRepository.chess.Pieces.Piece.*;

public class BasicChessAi implements ChessAI {
    Map<Integer, PieceController> pieceControllers = new HashMap<>();
    BoardEvaluator boardEvaluator = new BaseBoardEvaluator();

    public BasicChessAi() {
         initPieceControllers();
    }

    @Override
    public JSONBoard getBestMove(JSONBoard board) {
        List<JSONBoard> possibleBoards = new ArrayList<>();
        for(int x = 0; x < 8; x++) {
            for(int y = 0 ; y < 8; y++) {
                int piece = board.getPieceAt(x, y);
                if(piece != NULL.code && board.getIsItWhitesTurn() == isPieceWhite(piece)) {
                    List<JSONBoard> boards = pieceControllers.get(piece).getPossibleBoardStates(x, y, board);
                    if(boards != null && boards.size() > 0) {
                        possibleBoards.addAll(boards);
                    }
                }
            }
        }

        JSONBoard bestBoard = possibleBoards.get(0);
        double bestValue = boardEvaluator.evaluateBoard(bestBoard);;
        for(JSONBoard b : possibleBoards) {
            double value = boardEvaluator.evaluateBoard(b);
            if(board.getIsItWhitesTurn() && value > bestValue) {
                bestBoard = b;
            } else if(!board.getIsItWhitesTurn() && value < bestValue) {
                bestBoard = b;
            }
        }

        bestBoard.setItWhitePlayersTurn(!bestBoard.getIsItWhitesTurn());
        return bestBoard;
    }

    private void initPieceControllers() {
        Pawn pawn = new Pawn();
        pieceControllers.put(WHITE_PAWN.code, pawn);
        pieceControllers.put(BLACK_PAWN.code, pawn);
        Rook rook = new Rook();
        pieceControllers.put(WHITE_ROOK.code, rook);
        pieceControllers.put(BLACK_ROOK.code, rook);
        Knight knight = new Knight();
        pieceControllers.put(WHITE_KNIGHT.code, knight);
        pieceControllers.put(BLACK_KNIGHT.code, knight);
        Bishop bishop = new Bishop();
        pieceControllers.put(WHITE_BISHOP.code, bishop);
        pieceControllers.put(BLACK_BISHOP.code, bishop);
        Queen queen = new Queen();
        pieceControllers.put(WHITE_QUEEN.code, queen);
        pieceControllers.put(BLACK_QUEEN.code, queen);
        King king = new King();
        pieceControllers.put(WHITE_KING.code, king);
        pieceControllers.put(BLACK_KING.code, king);
    }
}
