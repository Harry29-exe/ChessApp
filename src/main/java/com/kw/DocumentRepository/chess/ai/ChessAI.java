package com.kw.DocumentRepository.chess.ai;

import com.kw.DocumentRepository.chess.JSONBoard;

public interface ChessAI {

    JSONBoard getBestMove(JSONBoard board);

}
