package com.kw.DocumentRepository.mapping.frontend;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

@RestController
public class Chess {

    @GetMapping(value = "/chess")
    public ResponseEntity<String> getChessHtml() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        File chessFile = new File("src\\main\\resources\\static\\chess.html");


        return ResponseEntity.ok().headers(headers).body(fileToString(chessFile));
    }

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
