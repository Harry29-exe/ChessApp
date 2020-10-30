package com.kw.DocumentRepository.chess.Pieces;

import com.kw.DocumentRepository.chess.Board;
import com.kw.DocumentRepository.chess.Move;

import java.util.List;

public class King implements Piece {
    private int x;
    private int y;
    private boolean isWhite;

    public King(int x, int y, boolean isWhite) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }

    @Override
    public int[] getPosition() {
        return new int[]{x, y};
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean isWhite() {
        return isWhite;
    }

    @Override
    public List<Move> getPossibleMoves(Board board) {
        return null;
    }
}
