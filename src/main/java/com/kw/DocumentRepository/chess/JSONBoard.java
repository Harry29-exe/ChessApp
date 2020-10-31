package com.kw.DocumentRepository.chess;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class JSONBoard implements Cloneable{
    private Integer[][] pieces;
    private Boolean isItWhitesTurn;
    private Castling castling;
    private ElPassantBeatingAvailableAt elPassantBeatingAvailableAt;

    public static void main(String[] args) {
        JSONBoard board = new JSONBoard(new Integer[][]{
                {-2,-3,-4,-6,-5,-4,-3,-2}, {-1,-1,-1,-1,-1,-1,-1,-1},
                {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},
                {1,1,1,1,1,0,1,1},{2,3,4,6,5,4,3,2}
        }, true, new Castling(true,true,true,true), new ElPassantBeatingAvailableAt(0,0));

        JSONBoard copy = board.clone();
        copy.setPieceAt(0,0,84);
        copy.setItWhitePlayersTurn(false);
        System.out.println(board.getPieceAt(0,0));
        System.out.println(board.getIsItWhitesTurn());
    }

    public JSONBoard(Integer[][] pieces, Boolean whitePlayersTurn, Castling castling, ElPassantBeatingAvailableAt elPassantBeatingAvailableAt) {
        this.pieces = pieces;
        this.isItWhitesTurn = whitePlayersTurn;
        this.castling = castling;
        this.elPassantBeatingAvailableAt = elPassantBeatingAvailableAt;
    }

    public JSONBoard() {
    }

    @Override
    public JSONBoard clone() {
        Integer[][] piecesCopy = new Integer[8][8];
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                piecesCopy[y][x] = pieces[y][x];
            }
        }
        return new JSONBoard(piecesCopy, this.isItWhitesTurn.booleanValue(), this.castling.clone(), this.elPassantBeatingAvailableAt.clone());
    }

    public Integer[][] getPieces() {
        return pieces;
    }

    public Integer getPieceAt(int x, int y) {
        return pieces[y][x];
    }

    public void setPieceAt(int x, int y, int pieceCode) {
        pieces[y][x] = pieceCode;
    }

    public void setPieces(Integer[][] pieces) {
        this.pieces = pieces;
    }

    public boolean getIsItWhitesTurn() {
        return isItWhitesTurn;
    }

    public void setItWhitePlayersTurn(boolean itWhitePlayersTurn) {
        isItWhitesTurn = itWhitePlayersTurn;
    }

    public Castling getCastling() {
        return castling;
    }

    public void setCastling(Castling castling) {
        this.castling = castling;
    }

    public ElPassantBeatingAvailableAt getElPassantBeatingAvailableAt() {
        return elPassantBeatingAvailableAt;
    }

    public void setElPassantBeatingAvailableAt(ElPassantBeatingAvailableAt elPassantBeatingAvailableAt) {
        this.elPassantBeatingAvailableAt = elPassantBeatingAvailableAt;
    }

    public static class Castling implements Cloneable {
        private boolean whiteLong;
        private boolean whiteShort;
        private boolean blackLong;
        private boolean blackShort;

        public Castling(boolean whiteLong, boolean whiteShort, boolean blackLong, boolean blackShort) {
            this.whiteLong = whiteLong;
            this.whiteShort = whiteShort;
            this.blackLong = blackLong;
            this.blackShort = blackShort;
        }

        public Castling() {
        }

        @Override
        public Castling clone() {
            return new Castling(this.whiteLong, this.whiteShort, this.blackLong, this.blackShort);
        }

        public boolean isWhiteLong() {
            return whiteLong;
        }

        public void setWhiteLong(boolean whiteLong) {
            this.whiteLong = whiteLong;
        }

        public boolean isWhiteShort() {
            return whiteShort;
        }

        public void setWhiteShort(boolean whiteShort) {
            this.whiteShort = whiteShort;
        }

        public boolean isBlackLong() {
            return blackLong;
        }

        public void setBlackLong(boolean blackLong) {
            this.blackLong = blackLong;
        }

        public boolean isBlackShort() {
            return blackShort;
        }

        public void setBlackShort(boolean blackShort) {
            this.blackShort = blackShort;
        }
    }

    public static class ElPassantBeatingAvailableAt implements Cloneable {
        private int x;
        private int y;

        public ElPassantBeatingAvailableAt(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public ElPassantBeatingAvailableAt() {
        }

        @Override
        public ElPassantBeatingAvailableAt clone() {
            return new ElPassantBeatingAvailableAt(this.x, this.y);
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

}
