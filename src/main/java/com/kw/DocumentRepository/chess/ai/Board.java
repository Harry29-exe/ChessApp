package com.kw.DocumentRepository.chess.ai;

public class Board implements Cloneable {
    private final Piece[][] pieces = new Piece[8][8];
    private boolean whiteLongCastlingAvailable = true;
    private boolean whiteShortCastlingAvailable = true;
    private boolean blackLongCastlingAvailable = true;
    private boolean blackShortCastlingAvailable = true;

    public Board(Json)

    public void executeMove(Move move){
        for( Move.Instruction instruction : move.getInstructions() ){

        }
    }

    public Piece getPieceAt( int x, int y ){
        return pieces[y][x];
    }

    @Override
    public Board clone(){
        Board newBoard = new Board();
        for( int i = 0; i < pieces.length; i++ ){
            for( int j = 0; j < pieces[0].length; j++){
                newBoard.set
            }
        }
        return ;
    }

    private Board executeInstruction(Move.Instruction instruction ){
        switch( instruction.type ){
            case MOVE:
                Board newBoard = this.clone();

        }
    }


}
