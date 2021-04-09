package org.newsio.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.newsio.service.NewsService;
import org.newsio.webCrawlerService.HTMLParser;

import java.io.IOException;

public class server {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(8080)
                .addService(new NewsService()).build();
        try {
             HTMLParser.getNews();
            server.start();
            System.out.println("Server is listning on port Number : 8080");
            server.awaitTermination();

        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
