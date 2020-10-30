package com.kw.DocumentRepository.chess.Pieces;

import com.kw.DocumentRepository.chess.Board;
import com.kw.DocumentRepository.chess.Move;

import java.util.LinkedList;
import java.util.List;

public class Pawn implements Piece {
    private int x;
    private int y;
    private boolean isWhite;

    public Pawn(int x, int y, boolean isWhite) {
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
        List<Move> moves = new LinkedList<>();
        if( isWhite ){
            if (y == 6) {
                if (board.getPieceAt(x, y - 1) == null) {
                    if (board.getPieceAt(x, y - 2) == null) {
                        moves.add( new Move(x, y, new Move.Instruction(Move.InstructionType.MOVE, x, y-2)) );
                    }
                    moves.add( new Move(x, y, new Move.Instruction(Move.InstructionType.MOVE, x, y-1)) );
                }
            } else {
                if (board.getPieceAt(x, y - 1) == null) {
                    moves.add( new Move(x, y, new Move.Instruction(Move.InstructionType.MOVE, x, y-1)) );
                }
            }

            if( (x - 1 >= 0 && x - 1 < 8) && board.getPieceAt(x - 1, y - 1) != null
                    && isWhite != board.getPieceAt(x - 1, y - 1).isWhite() ){
                moves.add( new Move(x, y, new Move.Instruction(Move.InstructionType.DELETE, x-1, y-1), new Move.Instruction(Move.InstructionType.MOVE, x-1, y-1)) );
            }
            if ((x + 1 >= 0 && x + 1 < 8) && board.getPieceAt(x + 1, y - 1) != null
                    && isWhite != board.getPieceAt(x + 1, y - 1).isWhite() ) {
                moves.add( new Move(x, y, new Move.Instruction(Move.InstructionType.DELETE, x+1, y-1), new Move.Instruction(Move.InstructionType.MOVE, x+1, y-1)) );
            }
        } else {
            if( y == 1 ){
                if( board.getPieceAt(x, y+1) == null ){
                    if( board.getPieceAt(x, y+2) == null ){
                        moves.add( new Move(x, y, new Move.Instruction(Move.InstructionType.MOVE, x, y+2)) );
                    }
                    moves.add( new Move(x, y, new Move.Instruction(Move.InstructionType.MOVE, x, y+1)) );
                }
            } else {
                if( board.getPieceAt(x, y+1) == null ) {
                    moves.add( new Move(x, y, new Move.Instruction(Move.InstructionType.MOVE, x, y+1)) );
                }
            }

            if( (x+1 >= 0 && x+1<8) && board.getPieceAt(x+1, y+1) != null
                    && isWhite != board.getPieceAt(x+1, y+1).isWhite() ){
                moves.add( new Move(x, y, new Move.Instruction(Move.InstructionType.DELETE, x+1, y+1), new Move.Instruction(Move.InstructionType.MOVE, x+1, y+1)) );
            }
            if( (x-1 >= 0 && x+1<8) && board.getPieceAt(x-1, y+1) != null
                    && isWhite != board.getPieceAt(x-1, y+1).isWhite() ){
                moves.add( new Move(x, y, new Move.Instruction(Move.InstructionType.DELETE, x-1, y+1), new Move.Instruction(Move.InstructionType.MOVE, x-1, y+1)) );
            }
        }
        return moves;
    }
}
