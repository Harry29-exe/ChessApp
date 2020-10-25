package com.kw.DocumentRepository.chess.ai;


import java.util.List;

public interface Piece {
    int[] getPosition();

    void setPosition( int x, int y );

    boolean isWhite();

    List<Move> getPossibleMoves(Board board);
}
