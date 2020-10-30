package com.kw.DocumentRepository.mapping.frontend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class Chess {

    @GetMapping(value = "/chess")
    public ResponseEntity<String> getChessHtml() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        File chessFile = new File("src\\main\\resources\\static\\chess.html");


        return ResponseEntity.ok().headers(headers).body(fileToString(chessFile));
    }

//    @GetMapping(value = "/chessScript")
//    public ResponseEntity<String> getChessScript() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "text/javascript");
//        File scriptFile = new File("src\\main\\resources\\static\\chess\\chessScript.js");
//
//        return ResponseEntity.ok().headers(headers).body(fileToString(scriptFile));
//    }
//
//    @GetMapping(value = "/chessBoard")
//    public ResponseEntity<String> getChessBoardScript() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "text/javascript");
//        File scriptFile = new File("src\\main\\resources\\static\\chess\\chessBoard.js");
//
//        return ResponseEntity.ok().headers(headers).body(fileToString(scriptFile));
//    }
//
//    @GetMapping(value = "/chessDrawing")
//    public ResponseEntity<String> getChessDrawingScript() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "text/javascript");
//        File scriptFile = new File("src\\main\\resources\\static\\chess\\chessDrawing.js");
//
//        return ResponseEntity.ok().headers(headers).body(fileToString(scriptFile));
//    }
//
//    @GetMapping(value = "/chessService")
//    public ResponseEntity<String> getChessServiceScript() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "text/javascript");
//        File scriptFile = new File("src\\main\\resources\\static\\chess\\chessService.js");
//
//        return ResponseEntity.ok().headers(headers).body(fileToString(scriptFile));
//    }


    private String fileToString(File file) {
        char[] string = new char[0];
        try {
            Reader reader = new FileReader(file);
            string = new char[(int) file.length()];
            reader.read(string);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(string);
    }
}
