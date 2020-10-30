package com.kw.DocumentRepository.chess.ai;

import com.kw.DocumentRepository.chess.Board;
import com.kw.DocumentRepository.chess.Move;

public interface ChessAI {

    Move getBestMove(Board board, boolean aIPlaysAsWhite);

}
