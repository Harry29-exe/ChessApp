package com.kw.DocumentRepository.mapping;

import com.kw.DocumentRepository.chess.JSONBoard;
import com.kw.DocumentRepository.chess.ai.BasicChessAi;
import com.kw.DocumentRepository.chess.ai.ChessAI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

@RestController
public class ChessApi {

    @PostMapping(value = "api/chess/get")
    public String makeTest(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaderNames();
        String header;
        while (headers.hasMoreElements()) {
            header = headers.nextElement();
            System.out.println(header + ":");
            System.out.println("    " +request.getHeader(header));
        }

        return "test is working";
    }

    @PostMapping(value = "api/chess/make-move", produces = "text/plain", consumes = "application/json")
    public ResponseEntity<JSONBoard> getMove(@RequestBody JSONBoard board) {
//        System.out.println("\nNew Request");
//        System.out.println(board.getIsItWhitesTurn());
//
//        System.out.println(Arrays.deepToString(board.getPieces()));
//
//        System.out.println("Castling");
//        System.out.println(board.getCastling().isWhiteLong());
//        System.out.println(board.getCastling().isWhiteShort());
//        System.out.println(board.getCastling().isBlackLong());
//        System.out.println(board.getCastling().isBlackShort());
//
//        System.out.println("El passant:");
//        if(board.getElPassantBeatingAvailableAt() != null) {
//            System.out.println(board.getElPassantBeatingAvailableAt().getX());
//            System.out.println(board.getElPassantBeatingAvailableAt().getY());
//        }
//
//        System.out.println("\n"+board.getPieces()[0][0]);
//        board.getPieces()[0][0] = 9817;
//        System.out.println(board.getPieces()[0][0]);
        ChessAI chessAI = new BasicChessAi();

        return ResponseEntity.ok().header("Content-Type", "application/json").body(chessAI.getBestMove(board));
    }
}
