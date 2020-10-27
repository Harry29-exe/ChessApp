package com.kw.DocumentRepository.chess.ai.Pieces;


import com.kw.DocumentRepository.chess.ai.Board;
import com.kw.DocumentRepository.chess.ai.Move;

import java.util.List;

public interface Piece {
    int[] getPosition();

    void setPosition( int x, int y );

    boolean isWhite();

    List<Move> getPossibleMoves(Board board);
}
