package com.kw.DocumentRepository.mapping;

import com.kw.DocumentRepository.configuration.TestBean;
import com.kw.DocumentRepository.persistence.BasicUserGameSaveRepository;
import com.kw.DocumentRepository.persistence.UserGameSave;
import com.kw.DocumentRepository.persistence.UserGameSaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HtmlMapping {

    private TestBean testBean;

    private PasswordEncoder stringEncoder;

    @Autowired
    public HtmlMapping(PasswordEncoder stringEncoder, TestBean testBean) {
        this.stringEncoder = stringEncoder;
        this.testBean = testBean;;
    }

    @GetMapping("/ip")
    public String getIp(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    @GetMapping("/encodeString")
    @ResponseBody
    public String encodeString(@RequestParam String string) {
        return stringEncoder.encode(string);
    }

    @GetMapping("/")
    public ResponseEntity<String> showMainPage() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");

        return ResponseEntity.ok().headers(headers).body("<!DOCTYPE html>\n" +
                "<html lang=\"pl\">\n" +
                "    <head>\n" +
                "        <meta charset=\"utf-8\"/>\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"/>\n" +
                "        <title>XYZ - Portfolio</title>\n" +
                "        <meta name=\"description\" content=\"opis strony\"/>\n" +
                "        <meta name=\"keywords\" content=\"dla wyszukiwarki\"/>\n" +
                "\n" +
                "        <link rel=\"stylesheet\" href=\"style.css\" type=\"text/css\"/>\n" +
                "    </head>\n" +
                "\n" +
                "    <body>\n" +
                "        <h1> tytuł ładny <h1/>    \n" +
                "    </body>\n" +
                "</html>");
    }

    @GetMapping("/style.css")
    public ResponseEntity<String> getMainCss() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/css");

        return ResponseEntity.ok().headers(headers).body("body {\n" +
                "    background-color: rgb(65, 65, 65);\n" +
                "    color: white;\n" +
                "}");
    }

    @GetMapping("/test-bean")
    public String getTestBean() {
        return "" + testBean.getI();
    }

    @GetMapping("/test-ping")
    public String getTestPing() {
        return "";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getName());

        return "uploaded";
    }

    @GetMapping("/user/game-save")
    public String getGameSave(String gameSaveId) {
        UserGameSaveRepository gameSaveRepo = new BasicUserGameSaveRepository();
        UserGameSave userGameSave = gameSaveRepo.getUserGameSaveByGameSaveId(gameSaveId);
        if(userGameSave != null) {
            return userGameSave.getId();
        } else {
            return "no game save with such id";
        }
    }
}
