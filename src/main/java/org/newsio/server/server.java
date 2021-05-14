package org.newsio.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.newsio.service.NewsService;
import org.newsio.webCrawlerService.PoliticalNewsArticlDaoImpl;
import org.newsio.webCrawlerService.TechNewsArticleDaoImpl;
import org.newsio.webCrawlerService.TechNewsHTMLParser;

import java.io.IOException;
import java.util.Timer;

public class server {
    public static void main(String[] args) {
        Server server = ServerBuilder.forPort(8080)
                .addService(new NewsService()).build();
           try {
               PoliticalNewsArticlDaoImpl politicalNewsArticlDao = new PoliticalNewsArticlDaoImpl();
               politicalNewsArticlDao.__init__();
                TechNewsArticleDaoImpl newsArticleDao = new TechNewsArticleDaoImpl();
                newsArticleDao.__init__();
            server.start();
            System.out.println("Server is listning on port Number : 8080");
            server.awaitTermination();

        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
