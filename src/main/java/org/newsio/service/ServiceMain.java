//package org.newsio.service;
//
//package org.newsio.service;
//import org.newsio.webCrawlerService.HTMLParser;
//import org.newsio.webCrawlerService.NewsArticle;
//import org.newsio.webCrawlerService.PostgreSqlDao;
//
//import java.util.List;
//import java.util.Optional;
//
//public class ServiceMain {
//    public static void main(String[] args) {
//        //HTMLParser.getNews();
//
////        new PostgreSqlDao().getLatest();
//        PostgreSqlDao postgreSqlDao = new PostgreSqlDao();
//        Optional<List<NewsArticle>> articles = postgreSqlDao.getLatest();
//        articles.ifPresent(article -> {
//            article.forEach(a -> {
//                System.out.println(a);
//            });
//
//        });
//    }
//}
