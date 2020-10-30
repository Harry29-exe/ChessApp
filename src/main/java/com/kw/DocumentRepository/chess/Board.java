package com.kw.DocumentRepository.chess;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kw.DocumentRepository.chess.Pieces.Piece;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;

import javax.management.BadAttributeValueExpException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Board implements Cloneable {
    public final boolean WHITE_PIECES_ON_THE_BOTTOM = true;
    private final Piece[][] pieces = new Piece[8][8];
    private boolean itIsWhitePlayersTurn;
    private boolean whiteLongCastlingAvailable = true;
    private boolean whiteShortCastlingAvailable = true;
    private boolean blackLongCastlingAvailable = true;
    private boolean blackShortCastlingAvailable = true;

    public Board() {

    }

    public Board(String json) throws BadAttributeValueExpException {
        JsonParser jsonParser = new JacksonJsonParser();
        Map<String, Object> jsonMap = jsonParser.parseMap(json);

        List<List<Object>> rows = (List<List<Object>>) jsonMap.get("chessBoardArray");
        for (List<Object> cols : rows) {
            for (Object o : cols) {

            }

        }

        for( int y = 0; y < 8; y++ ){
            for( int x = 0; x < 8; x++ ){
                //this.pieces[y][x] =
            }
        }

    }

    public List<Piece> getWhitePieces() {
        List<Piece> whitePieces = new LinkedList<>();
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if(pieces[y][x] != null && pieces[y][x].isWhite()) {
                    whitePieces.add(pieces[y][x]);
                }
            }
        }

        return whitePieces;
    }

    public List<Piece> getBlackPieces() {
        List<Piece> whitePieces = new LinkedList<>();
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                if(pieces[y][x] != null && !pieces[y][x].isWhite()) {
                    whitePieces.add(pieces[y][x]);
                }
            }
        }

        return whitePieces;
    }

    public boolean isItIsWhitePlayersTurn() {
        return itIsWhitePlayersTurn;
    }

    public void executeMove(Move move){
        for( Move.Instruction instruction : move.getInstructions() ){

        }
    }

    public Piece getPieceAt( int x, int y ){
        return pieces[y][x];
    }



    private Board executeInstruction(Move.Instruction instruction ){
        switch( instruction.type ){
            case MOVE:


        }


        throw new UnsupportedOperationException("Not supported yet");
    }


}
