package com.kw.DocumentRepository.chess.Pieces;

import com.kw.DocumentRepository.chess.Board;

import static com.kw.DocumentRepository.chess.Pieces.Piece.*;

import java.util.List;

public class KingController extends AbstractPieceController {

    @Override
    public List<Board> getPossibleBoardStates(int x, int y, Board board) {
        init(x,y,board);

        checkCastling();
        for(int xChange = -1; xChange < 2; xChange++) {
            for(int yChange = -1; yChange < 2; yChange++) {
                checkCoordinates(x+xChange, y+yChange);
            }
        }


        List<Board> returnVal = possibleBoards;
        cleanup();
        return returnVal;
    }

    private void checkCoordinates(int x, int y) {
        if(this.coordinatesAreCorrect(x, y)) {
            if(board.getPieceAt(x,y) == NULL.code || isWhite != isPieceWhite(board.getPieceAt(x,y))) {
                Board newBoard = board.clone();
                newBoard.setPieceAt(x,y, isWhite? WHITE_KING.code : BLACK_KING.code);
                newBoard.setPieceAt(this.x, this.y, NULL.code);
                possibleBoards.add(newBoard);
            }
        }
    }

    //should also check if this conditions are fulfilled:
    //The king is not currently in check.
    //The king does not pass through a square that is attacked by an enemy piece.
    //The king does not end up in check. (True of any legal move.)
    private void checkCastling() {
        if(isWhite) {
            if(board.getCastling().isWhiteShort()) {
                boolean spaceBetweenIsEmpty = true;

                for(int x = this.x; x < 7; x++)
                    spaceBetweenIsEmpty = board.getPieceAt(x, this.y) == NULL.code && spaceBetweenIsEmpty;
                if(spaceBetweenIsEmpty)
                    addCastleShort();
            }
            if(board.getCastling().isWhiteLong()) {
                boolean spaceBetweenIsEmpty = true;

                for(int x = this.x; x > 0; x--)
                    spaceBetweenIsEmpty = board.getPieceAt(x, this.y) == NULL.code && spaceBetweenIsEmpty;
                if(spaceBetweenIsEmpty)
                    addCastleLong();
            }
        } else {
            if(board.getCastling().isBlackShort()) {
                boolean spaceBetweenIsEmpty = true;

                for(int x = this.x; x < 7; x++)
                    spaceBetweenIsEmpty = board.getPieceAt(x, this.y) == NULL.code && spaceBetweenIsEmpty;
                if(spaceBetweenIsEmpty)
                    addCastleShort();
            }
            if(board.getCastling().isBlackLong()) {
                boolean spaceBetweenIsEmpty = true;

                for(int x = this.x; x > 0; x--)
                    spaceBetweenIsEmpty = board.getPieceAt(x, this.y) == NULL.code && spaceBetweenIsEmpty;
                if(spaceBetweenIsEmpty)
                    addCastleLong();
            }
        }
    }

    private void addCastleLong() {
        Board newBoard = board.clone();
        newBoard.setPieceAt(this.x, this.y, NULL.code);
        newBoard.setPieceAt(this.x,0, NULL.code);
        newBoard.setPieceAt(this.x - 2, this.y, WHITE_KING.code);
        newBoard.setPieceAt(this.x - 1, this.y, WHITE_ROOK.code);
        possibleBoards.add(newBoard);
    }

    private void addCastleShort() {
        Board newBoard = board.clone();
        newBoard.setPieceAt(this.x, this.y, NULL.code);
        newBoard.setPieceAt(this.x,7, NULL.code);
        newBoard.setPieceAt(this.x + 2, this.y, WHITE_KING.code);
        newBoard.setPieceAt(this.x + 1, this.y, WHITE_ROOK.code);
        possibleBoards.add(newBoard);
    }
}
