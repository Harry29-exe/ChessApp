package com.kw.DocumentRepository.chess.pieces;

import com.kw.DocumentRepository.chess.Board;
import com.kw.DocumentRepository.chess.Pieces.BishopController;
import com.kw.DocumentRepository.chess.Pieces.Piece;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static com.kw.DocumentRepository.chess.Pieces.Piece.*;
import static com.kw.DocumentRepository.chess.pieces.PieceTestUtils.XY.xy;

import java.util.List;

class BishopControllerTest {
    PieceTestUtils testUtils = new PieceTestUtils();
    BishopController bishop = new BishopController();

    @Test
    void should_return_null() {
        Board testBoard = testUtils.createStartBoard();

        List<Board> boards = bishop.getPossibleBoardStates(2, 0, testBoard);
        Assert.assertEquals(0, boards.size());
    }

    @Test
    void should_return_all_moves_on_diagonals_for_white_bishop() {
        Board testBord = testUtils.createEmptyBoard();
        testBord.setPieceAt(3, 3, WHITE_BISHOP.code);

        List<Board> controllerMoves = bishop.getPossibleBoardStates(3, 3, testBord);
        List<Board> allMoves = testUtils.createBoardsWithOnePiece(WHITE_BISHOP.code,
                xy(0, 0), xy(1, 1), xy(2, 2), xy(4, 4), xy(5, 5), xy(6, 6), xy(7, 7),
                xy(0, 6), xy(1, 5), xy(2, 4), xy(4, 2), xy(5, 1), xy(6, 0));

        Assert.assertTrue(testUtils.boardListEquals(allMoves, controllerMoves));
    }

    @Test
    void should_return_all_moves_on_diagonals_for_black_bishop() {
        Board testBord = testUtils.createEmptyBoard();
        testBord.setPieceAt(3, 3, BLACK_BISHOP.code);

        List<Board> controllerMoves = bishop.getPossibleBoardStates(3, 3, testBord);
        List<Board> allMoves = testUtils.createBoardsWithOnePiece(BLACK_BISHOP.code,
                xy(0, 0), xy(1, 1), xy(2, 2), xy(4, 4), xy(5, 5), xy(6, 6), xy(7, 7),
                xy(0, 6), xy(1, 5), xy(2, 4), xy(4, 2), xy(5, 1), xy(6, 0));

        Assert.assertTrue(testUtils.boardListEquals(allMoves, controllerMoves));
    }

    @Test
    void should_return_2_pawn_beatings_for_white_bishop() {
        for (Piece pieceToBeat : testUtils.blackPieces) {
            Board board = testUtils.createEmptyBoard();
            board.setPieceAt(2, 7, WHITE_BISHOP.code);
            board.setPieceAt(1, 6, pieceToBeat.code);
            board.setPieceAt(3, 6, pieceToBeat.code);

            List<Board> controllerMoves = bishop.getPossibleBoardStates(2, 7, board);
            List<Board> allMoves = testUtils.createNewBoardsWithMovedPiece(board, 2, 7, xy(1, 6), xy(3, 6));

            Assert.assertTrue(testUtils.boardListEquals(allMoves, controllerMoves));
        }
    }

    @Test
    void should_return_2_beatings_for_black_bishop() {
        for (Piece pieceToBeat : testUtils.whitePieces) {
            Board board = testUtils.createEmptyBoard();
            board.setPieceAt(2, 7, BLACK_BISHOP.code);
            board.setPieceAt(1, 6, pieceToBeat.code);
            board.setPieceAt(3, 6, pieceToBeat.code);

            List<Board> controllerMoves = bishop.getPossibleBoardStates(2, 7, board);
            List<Board> allMoves = testUtils.createNewBoardsWithMovedPiece(board, 2, 7, xy(1, 6), xy(3, 6));

            Assert.assertTrue(testUtils.boardListEquals(allMoves, controllerMoves));
        }
    }

    @Test
    void should_return_4_beatings_and_all_diagonal_moves_for_white_bishop() {
        for (Piece pieceToBeat : testUtils.blackPieces) {
            Board board = testUtils.createEmptyBoard();
            board.setPieceAt(3, 3, WHITE_BISHOP.code);
            board.setPieceAt(0, 0, pieceToBeat.code);
            board.setPieceAt(7, 7, pieceToBeat.code);
            board.setPieceAt(0, 6, pieceToBeat.code);
            board.setPieceAt(6, 0, pieceToBeat.code);

            List<Board> controllerMoves = bishop.getPossibleBoardStates(3, 3, board);
            List<Board> allMoves = testUtils.createNewBoardsWithMovedPiece(board, 3,3,
                    xy(0, 0), xy(1, 1), xy(2, 2), xy(4, 4), xy(5, 5), xy(6, 6), xy(7, 7),
                    xy(0, 6), xy(1, 5), xy(2, 4), xy(4, 2), xy(5, 1), xy(6, 0));

            Assert.assertTrue(testUtils.boardListEquals(allMoves, controllerMoves));
        }
    }

    @Test
    void should_return_4_beatings_and_all_diagonal_moves_for_black_bishop() {
        for (Piece pieceToBeat : testUtils.whitePieces) {
            Board board = testUtils.createEmptyBoard();
            board.setPieceAt(3, 3, BLACK_BISHOP.code);
            board.setPieceAt(0, 0, pieceToBeat.code);
            board.setPieceAt(7, 7, pieceToBeat.code);
            board.setPieceAt(0, 6, pieceToBeat.code);
            board.setPieceAt(6, 0, pieceToBeat.code);

            List<Board> controllerMoves = bishop.getPossibleBoardStates(3, 3, board);
            List<Board> allMoves = testUtils.createNewBoardsWithMovedPiece(board, 3,3,
                    xy(0, 0), xy(1, 1), xy(2, 2), xy(4, 4), xy(5, 5), xy(6, 6), xy(7, 7),
                    xy(0, 6), xy(1, 5), xy(2, 4), xy(4, 2), xy(5, 1), xy(6, 0));

            Assert.assertTrue(testUtils.boardListEquals(allMoves, controllerMoves));
        }
    }
}
