package com.kw.DocumentRepository.chess.ai;

import com.kw.DocumentRepository.chess.ai.Pieces.Piece;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;

import java.util.List;
import java.util.Map;

public class Board implements Cloneable {
    private final Piece[][] pieces = new Piece[8][8];
    private boolean whiteLongCastlingAvailable = true;
    private boolean whiteShortCastlingAvailable = true;
    private boolean blackLongCastlingAvailable = true;
    private boolean blackShortCastlingAvailable = true;

    public static void main(String[] args) {
        Board board = new Board("{\"chessBoardArray\":[[9820,9822,9821,9818,9819,9821,9822,9820],[9823,9823,9823,9823,9823,9823,9823,9823]," +
                "[null,null,null,null,null,null,null,null],[null,null,null,null,null,null,null,null],[null,null,null,null,null,null,null,null]," +
                "[null,null,null,null,null,null,null,null],[9817,9817,9817,9817,9817,9817,9817,9817],[9814,9816,9815,9812,9813,9815,9816,9814]]," +
                "\"isCastlingAvailable\":{\"WHITE_SHORT\":true,\"WHITE_LONG\":true,\"BLACK_SHORT\":true,\"BLACK_LONG\":true},\"enPassantMove\":null}");
    }

    public Board() {

    }

    public Board(String json){
        JsonParser jsonParser = new JacksonJsonParser();
        Map<String, Object> jsonMap = jsonParser.parseMap(json);

        if( jsonMap.get("chessBoardArray") instanceof List && ((List)jsonMap.get("chessBoardArray")).get(0) instanceof List ) {
            List<List<Object>> pieces = (List<List<Object>>) jsonMap.get("chessBoardArray");
            for (List<Object> o1 : pieces) {
                for (Object o2 : o1) {
                    //if (o2 != null)

                }
            }
        }

        for( int y = 0; y < 8; y++ ){
            for( int x = 0; x < 8; x++ ){
                //this.pieces[y][x] =
            }
        }

    }

    public void executeMove(Move move){
        for( Move.Instruction instruction : move.getInstructions() ){

        }
    }

    public Piece getPieceAt( int x, int y ){
        return pieces[y][x];
    }

//    @Override
//    public Board clone(){
//        Board newBoard = new Board();
//        for( int i = 0; i < pieces.length; i++ ){
//            for( int j = 0; j < pieces[0].length; j++){
//                newBoard.set
//            }
//        }
//        return ;
//    }

    private Board executeInstruction(Move.Instruction instruction ){
        switch( instruction.type ){
            case MOVE:


        }


        throw new UnsupportedOperationException("Not supported yet");
    }


}
