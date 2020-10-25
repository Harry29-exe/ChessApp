package com.kw.DocumentRepository.chess.ai;

import java.util.ArrayList;
import java.util.List;

public class Move {
    public final int pieceX;
    public final int pieceY;
    private final List<Instruction> instructions = new ArrayList<>();

    public Move(int pieceX, int pieceY) {
        this.pieceX = pieceX;
        this.pieceY = pieceY;
    }

    public Move(int pieceX, int pieceY, Instruction... instructions) {
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        for( Instruction instruction : instructions ){
            this.instructions.add(instruction);
        }
    }

    public List<Instruction> getInstructions(){
        return instructions;
    }

    public void addInstruction( InstructionType type, int xToExecuteOn, int yToExecuteOn ){
        instructions.add(new Instruction(type, xToExecuteOn, yToExecuteOn));
    }

    public static class Instruction {
        public final InstructionType type;
        public final int xToExecuteOn;
        public final int yToExecuteOn;

        public Instruction(InstructionType type, int xToExecuteOn, int yToExecuteOn) {
            this.type = type;
            this.xToExecuteOn = xToExecuteOn;
            this.yToExecuteOn = yToExecuteOn;
        }
    }

    public enum InstructionType {
        DELETE,
        MOVE,
        TRANSFORM
    }
}
