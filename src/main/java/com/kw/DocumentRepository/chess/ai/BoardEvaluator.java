package com.kw.DocumentRepository.chess.ai;

import com.kw.DocumentRepository.chess.JSONBoard;

public interface BoardEvaluator {
    double evaluateBoard(JSONBoard board);
}
