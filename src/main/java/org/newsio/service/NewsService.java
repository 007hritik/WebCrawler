package org.newsio.service;

import io.grpc.stub.StreamObserver;
import org.newsio.grpc.NewsServiceGrpc;
import org.newsio.grpc.Newsio;
import org.newsio.grpc.Newsio.News;
import org.newsio.webCrawlerService.NewsArticle;
import org.newsio.webCrawlerService.PostgreSqlDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsService extends NewsServiceGrpc.NewsServiceImplBase {

    @Override
    public void fetchLatestNews(Newsio.noParams request, StreamObserver<Newsio.News> responseObserver) {
        System.out.println("fetch news method called ");
        PostgreSqlDao postgreSqlDao = new PostgreSqlDao();
        Optional<List<NewsArticle>> articles = postgreSqlDao.getLatest();
        articles.ifPresent(article -> {
            article.forEach(a -> {
                responseObserver.onNext(newsArticleToNews(a));
            });


        });
        responseObserver.onCompleted();
    }
    public static News  newsArticleToNews(NewsArticle newsArticle) {
        return News.newBuilder()
                .setAuthor(newsArticle.getAuthorName())
                .setTitle(newsArticle.getArticleTitle())
                .setDescription(newsArticle.getDescription())
                .setId(newsArticle.get_id())
                .setDate(newsArticle.getPublishedDate().toString())
                .setImageLink(newsArticle.getImgLink())
                .build();
    }
}
