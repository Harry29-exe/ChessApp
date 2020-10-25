package com.kw.DocumentRepository.mapping;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRegistration {

    @GetMapping("/user/registration")
    @ResponseBody
    public String showRegistrationForm() {

        return "registration";
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUserPage() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");

        return ResponseEntity.ok().headers(headers).body("<!DOCTYPE html>\n" +
                "\n" +
                "<html lang=\"pl\"?>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\"/>\n" +
                "    <title>base html</title>\n" +
                "    <meta name=\"description\" content=\"Opis strony w googlu\"/>\n" +
                "    <meta name=\"keywords\" content=\"tagi do wyszukiwania dla googla czy innego binga\"/>\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\"/>\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>to jest h1</h1>\n" +
                "    jakiś random teks\n" +
                "    <br/> <br/>\n" +
                "    tekst po 2 br'ach\n" +
                "    <br/>\n" +
                "\n" +
                "    <a href=\"https://www.google.com/\" target=\"_blank\" title=\"idz do googla\">\n" +
                "        google.com ->\n" +
                "        <img width=\"400\" height=\"400\" \n" +
                "        src=\"https://s.yimg.com/uu/api/res/1.2/DdytqdFTgtQuxVrHLDdmjQ--~B/aD03MTY7dz0xMDgwO3NtPTE7YXBwaWQ9eXRhY2h5b24-/https://media-mbst-pub-ue1.s3.amazonaws.com/creatr-uploaded-images/2019-11/7b5b5330-112b-11ea-a77f-7c019be7ecae\"\n" +
                "        />\n" +
                "    </a>\n" +
                "\n" +
                "\n" +
                "</body>");
    }

}
