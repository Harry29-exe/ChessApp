package com.kw.DocumentRepository.chess.Pieces;


import com.kw.DocumentRepository.chess.Board;

import java.util.List;

public interface PieceController {
    List<Board> getPossibleBoardStates(int x, int y, Board board);
    //List<Move> getPossibleMoves(int x, int y,Board board);
}
