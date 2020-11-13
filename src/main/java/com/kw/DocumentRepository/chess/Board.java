package com.kw.DocumentRepository.chess;

public class Board implements Cloneable{
    private Integer[][] pieces;
    private Boolean isItWhitesTurn;
    private Castling castling;
    private ElPassantBeatingAvailableAt elPassantBeatingAvailableAt;

    public Board(Integer[][] pieces, Boolean whitePlayersTurn, Castling castling, ElPassantBeatingAvailableAt elPassantBeatingAvailableAt) {
        this.pieces = pieces;
        this.isItWhitesTurn = whitePlayersTurn;
        this.castling = castling;
        this.elPassantBeatingAvailableAt = elPassantBeatingAvailableAt;
    }

    public Board() {
    }

    public boolean isCorrect() {
        boolean isCorrect = true;

        if(pieces == null)
            return false;

        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if(pieces[y][x] > 6 || pieces[y][x] < -6)
                    return false;
            }
        }

        if(castling == null)
            return false;

        if(elPassantBeatingAvailableAt == null)
            return false;

        return true;
    }

    @Override
    public Board clone() {
        Integer[][] piecesCopy = new Integer[8][8];
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                piecesCopy[y][x] = pieces[y][x];
            }
        }
        return new Board(piecesCopy, this.isItWhitesTurn, this.castling.clone(), this.elPassantBeatingAvailableAt.clone());
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
