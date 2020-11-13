package com.kw.DocumentRepository.chess.ai;

import com.kw.DocumentRepository.chess.Board;
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
    public Board getBestMove(Board board) {
        List<Board> possibleBoards = new ArrayList<>();
        for(int x = 0; x < 8; x++) {
            for(int y = 0 ; y < 8; y++) {
                int piece = board.getPieceAt(x, y);
                if(piece != NULL.code && board.getIsItWhitesTurn() == isPieceWhite(piece)) {
                    List<Board> boards = pieceControllers.get(piece).getPossibleBoardStates(x, y, board);
                    if(boards != null && boards.size() > 0) {
                        possibleBoards.addAll(boards);
                    }
                }
            }
        }

        Board bestBoard = possibleBoards.get(0);
        double bestValue = boardEvaluator.evaluateBoard(bestBoard);;
        for(Board b : possibleBoards) {
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
        PawnController pawnController = new PawnController();
        pieceControllers.put(WHITE_PAWN.code, pawnController);
        pieceControllers.put(BLACK_PAWN.code, pawnController);
        RookController rookController = new RookController();
        pieceControllers.put(WHITE_ROOK.code, rookController);
        pieceControllers.put(BLACK_ROOK.code, rookController);
        KnightController knightController = new KnightController();
        pieceControllers.put(WHITE_KNIGHT.code, knightController);
        pieceControllers.put(BLACK_KNIGHT.code, knightController);
        BishopController bishopController = new BishopController();
        pieceControllers.put(WHITE_BISHOP.code, bishopController);
        pieceControllers.put(BLACK_BISHOP.code, bishopController);
        QueenController queenController = new QueenController();
        pieceControllers.put(WHITE_QUEEN.code, queenController);
        pieceControllers.put(BLACK_QUEEN.code, queenController);
        KingController kingController = new KingController();
        pieceControllers.put(WHITE_KING.code, kingController);
        pieceControllers.put(BLACK_KING.code, kingController);
    }
}
