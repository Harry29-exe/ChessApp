package com.kw.DocumentRepository.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Move {
    public final int pieceX;
    public final int pieceY;
    private final List<Instruction> instructions = new ArrayList<>();

    public Move(int pieceX, int pieceY, Instruction... instructions) {
        this.pieceX = pieceX;
        this.pieceY = pieceY;
        this.instructions.addAll(Arrays.asList(instructions));
    }

    public List<Instruction> getInstructions(){
        return instructions;
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
        TRANSFORM,
        LOCK_CASTLING,
        SET_EL_PASSANT
    }
}
