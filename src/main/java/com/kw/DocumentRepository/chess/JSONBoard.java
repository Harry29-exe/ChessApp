package com.kw.DocumentRepository.chess;

public class JSONBoard {
    private int[][] pieces;
    private Boolean whitePlayersTurn;
    private Castling castling;
    private ElPassantBeatingAvailableAt elPassantBeatingAvailableAt;

    public JSONBoard(int[][] pieces, Boolean whitePlayersTurn, Castling castling, ElPassantBeatingAvailableAt elPassantBeatingAvailableAt) {
        this.pieces = pieces;
        this.whitePlayersTurn = whitePlayersTurn;
        this.castling = castling;
        this.elPassantBeatingAvailableAt = elPassantBeatingAvailableAt;
    }

    public JSONBoard() {
    }

    public int[][] getPieces() {
        return pieces;
    }

    public void setPieces(int[][] pieces) {
        this.pieces = pieces;
    }

    public boolean isWhitePlayersTurn() {
        return whitePlayersTurn;
    }

    public void setItWhitePlayersTurn(boolean itWhitePlayersTurn) {
        whitePlayersTurn = itWhitePlayersTurn;
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

    public static class Castling {
        private boolean whiteLong;
        private boolean whiteShort;
        private boolean blackLong;
        private boolean blackShort;

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

    public static class ElPassantBeatingAvailableAt {
        private int x;
        private int y;

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
