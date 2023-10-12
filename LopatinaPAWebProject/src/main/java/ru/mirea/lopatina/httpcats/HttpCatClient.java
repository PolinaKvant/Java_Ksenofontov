package ru.mirea.lopatina.httpcats;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


public class HttpCatClient {
    public static void main(String[] args) throws IOException,
            InterruptedException {
        File dir = new File("cats");
        if (!dir.exists()) {
            dir.mkdir();
        }
        HttpClient client = HttpClient.newHttpClient();
        ArrayList<CompletableFuture> futures = new ArrayList();
        for (int i = 0; i < 600; i++) {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create("https://http.cat/" + i))
                    .GET()
                    .build();
            System.out.println("Send request to https://http.cat/" + i);
            int finalI = i;
            CompletableFuture ft = client.sendAsync(request,
                            HttpResponse.BodyHandlers.ofByteArray())
                    .thenApply(Function.identity())
                    .thenAccept(response -> saveToFile(response, finalI));
            Thread.sleep(100);
            futures.add(ft);
        }
        System.out.println("Wait for end of jobs");
        futures.forEach(CompletableFuture::join);
        Thread.sleep(5000);
    }
    private static void saveToFile(HttpResponse<byte[]> response, int i) {
        if (response.statusCode() == 404) {
            return;
        }
        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream("cats/cat_" + i + ".jpg")
            );
            outputStream.write(response.body());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


}
