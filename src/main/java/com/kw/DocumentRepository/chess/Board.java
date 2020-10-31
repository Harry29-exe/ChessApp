package com.kw.DocumentRepository.chess;

public class Board implements Cloneable {
//    public final boolean WHITE_PIECES_ON_THE_BOTTOM = true;
//    private final PieceController[][] pieceControllers = new PieceController[8][8];
//    private boolean itIsWhitePlayersTurn;
//    private boolean whiteLongCastlingAvailable = true;
//    private boolean whiteShortCastlingAvailable = true;
//    private boolean blackLongCastlingAvailable = true;
//    private boolean blackShortCastlingAvailable = true;
//
//    public Board(JSONBoard board) {
////        itIsWhitePlayersTurn = board.getIsItWhitesTurn();
////        for(int x = 0; x < 8; x++) {
////            for(int y = 0; y < 8; y++) {
////                pieces[y][x] = board.getPieces()[y][x];
////            }
////        }
//    }
//
//    public List<PieceController> getWhitePieces() {
//        List<PieceController> whitePieceControllers = new LinkedList<>();
//        for(int x = 0; x < 8; x++) {
//            for(int y = 0; y < 8; y++) {
//                if(pieceControllers[y][x] != null && pieceControllers[y][x].isWhite()) {
//                    whitePieceControllers.add(pieceControllers[y][x]);
//                }
//            }
//        }
//
//        return whitePieceControllers;
//    }
//
//    public List<PieceController> getBlackPieces() {
//        List<PieceController> whitePieceControllers = new LinkedList<>();
//        for(int x = 0; x < 8; x++) {
//            for(int y = 0; y < 8; y++) {
//                if(pieceControllers[y][x] != null && !pieceControllers[y][x].isWhite()) {
//                    whitePieceControllers.add(pieceControllers[y][x]);
//                }
//            }
//        }
//
//        return whitePieceControllers;
//    }
//
//    public boolean isItIsWhitePlayersTurn() {
//        return itIsWhitePlayersTurn;
//    }
//
//    public void executeMove(Move move){
//        for( Move.Instruction instruction : move.getInstructions() ){
//
//        }
//    }
//
//    public PieceController getPieceAt(int x, int y ){
//        return pieceControllers[y][x];
//    }
//
//
//
//    private Board executeInstruction(Move.Instruction instruction ){
//        switch( instruction.type ){
//            case MOVE:
//
//
//        }
//
//
//        throw new UnsupportedOperationException("Not supported yet");
//    }
//

}
