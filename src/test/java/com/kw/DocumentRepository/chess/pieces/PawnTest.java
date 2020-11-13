package com.kw.DocumentRepository.chess.pieces;

import com.kw.DocumentRepository.chess.Pieces.PawnController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PawnTest {
    PieceTestUtils testUtils;
    PawnController pawnController;

    @BeforeAll
    public void setup() {
        testUtils = new PieceTestUtils();
        pawnController = new PawnController();
    }

    @Test
    void should_return_two_boards() {

    }
}
