package com.somik.tech;

import com.google.gson.Gson;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;


public class App {
    public static void main(String[] args) throws InterruptedException {

        CrptApi api = new CrptApi(TimeUnit.SECONDS, 1, "https://ismp.crpt.ru/api/v3/lk/documents/create");

        String json = "";
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("src/main/resources/document.json"));
            json = new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.getMessage();
        }

        Gson gson = new Gson();
        CrptApi.Document document = gson.fromJson(json, CrptApi.Document.class);

        String signature = "signature";

        api.createDocument(document, signature);

        api.shutdown();
    }
}
