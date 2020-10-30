package com.kw.DocumentRepository.mapping;

import com.kw.DocumentRepository.chess.JSONBoard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class ChessApi {

    @GetMapping(value = "api/chess/get-move", produces = "text/plain", consumes = "application/json")
    public String getMove(@RequestBody JSONBoard board) {
        System.out.println("\nNew Request\n");
        System.out.println(board.isWhitePlayersTurn());

        System.out.println(Arrays.deepToString(board.getPieces()));

        System.out.println(board.getCastling());
        System.out.println(board.getCastling().isWhiteLong());
        System.out.println(board.getCastling().isWhiteShort());
        System.out.println(board.getCastling().isBlackLong());
        System.out.println(board.getCastling().isBlackShort());

        System.out.println(board.getElPassantBeatingAvailableAt());
        if(board.getElPassantBeatingAvailableAt() != null) {
            System.out.println(board.getElPassantBeatingAvailableAt().getX());
            System.out.println(board.getElPassantBeatingAvailableAt().getY());
        }

        return null;
    }
}
