package com.kw.DocumentRepository.chess.Pieces;

import com.kw.DocumentRepository.chess.Board;

import java.util.List;

import static com.kw.DocumentRepository.chess.Pieces.Piece.*;

public class BishopController extends AbstractPieceController {

    @Override
    public List<Board> getPossibleBoardStates(int x, int y, Board board) {
        this.init(x,y,board);

        iterate(-1,-1);
        iterate(-1,1);
        iterate(1,1);
        iterate(1,-1);

        List<Board> returnVal = possibleBoards;
        this.cleanup();

        return returnVal;
    }

    private void iterate(int xChange, int yChange) {
        int xToCheck = x + xChange;
        int yToCheck = y + yChange;
        while( indexIsCorrect(xToCheck) && indexIsCorrect(yToCheck) ) {
            int piece = board.getPieceAt(xToCheck, yToCheck);
            if(piece == NULL.code) {
                Board b = board.clone();
                b.setPieceAt(x, y, NULL.code);
                b.setPieceAt(xToCheck, yToCheck, isWhite? WHITE_BISHOP.code : BLACK_BISHOP.code);
                possibleBoards.add(b);
                xToCheck += xChange;
                yToCheck += yChange;
            } else if (isWhite != isPieceWhite(piece)) {
                Board b = board.clone();
                b.setPieceAt(x,y, NULL.code);
                b.setPieceAt(xToCheck, yToCheck, isWhite? WHITE_BISHOP.code : BLACK_BISHOP.code);
                possibleBoards.add(b);
                return;
            } else {
                return;
            }
        }
    }
}
