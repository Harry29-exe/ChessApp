package com.kw.DocumentRepository.chess.Pieces;


import com.kw.DocumentRepository.chess.Board;
import com.kw.DocumentRepository.chess.Move;

import java.util.List;

public interface Piece {
    int[] getPosition();

    void setPosition( int x, int y );

    boolean isWhite();

    List<Move> getPossibleMoves(Board board);
}
