package com.kw.DocumentRepository.mapping;

import com.kw.DocumentRepository.chess.Board;
import com.kw.DocumentRepository.chess.ai.BasicChessAi;
import com.kw.DocumentRepository.chess.ai.ChessAI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
public class ChessApi {

    @PostMapping(value = "/api/chess/get")
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

    @PostMapping(value = "/api/chess/make-move", produces = "text/plain", consumes = "application/json")
    public ResponseEntity<Board> getMove(@RequestBody Board board) {
        if(!board.isCorrect()) {
            return ResponseEntity.badRequest().body(null);
        }
        ChessAI chessAI = new BasicChessAi();


        return ResponseEntity.ok().header("Content-Type", "application/json").body(chessAI.getBestMove(board));
    }
}
