package com.kw.DocumentRepository.chess.Pieces;


import com.kw.DocumentRepository.chess.JSONBoard;

import java.util.List;

public interface PieceController {
    List<JSONBoard> getPossibleBoardStates(int x, int y, JSONBoard board);
    //List<Move> getPossibleMoves(int x, int y,Board board);
}
