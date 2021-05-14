package org.newsio.service;

import io.grpc.stub.StreamObserver;
import org.newsio.grpc.NewsServiceGrpc;
import org.newsio.grpc.Newsio;
import org.newsio.grpc.Newsio.News;
import org.newsio.webCrawlerService.NewsArticle;
import org.newsio.webCrawlerService.PoliticalNewsArticlDaoImpl;
import org.newsio.webCrawlerService.TechNewsArticleDaoImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsService extends NewsServiceGrpc.NewsServiceImplBase {
    @Override
    public void fetchLatestPoliticalNews(Newsio.noParams request, StreamObserver<News> responseObserver) {
        System.out.println("fetch news method called ");
        PoliticalNewsArticlDaoImpl politicalNewsArticlDao = new PoliticalNewsArticlDaoImpl();
        List<NewsArticle> articles = politicalNewsArticlDao
                .getAll();

        articles.forEach(a -> {
            responseObserver.onNext(newsArticleToNews(a));
        });


        responseObserver.onCompleted();
    }

    @Override
    public void fetchLatestTechNews(Newsio.noParams request, StreamObserver<Newsio.News> responseObserver) {
        System.out.println("fetch news method called ");
        TechNewsArticleDaoImpl techNewsArticleDao = new TechNewsArticleDaoImpl();
        List<NewsArticle> articles = techNewsArticleDao.getAll();

            articles.forEach(a -> {
                responseObserver.onNext(newsArticleToNews(a));
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
