package com.kw.DocumentRepository.chess.ai.Pieces;

import com.kw.DocumentRepository.chess.ai.Board;
import com.kw.DocumentRepository.chess.ai.Move;

import java.util.List;

public class Queen implements Piece {
    private int x;
    private int y;
    private boolean isWhite;

    public Queen(int x, int y, boolean isWhite) {
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
