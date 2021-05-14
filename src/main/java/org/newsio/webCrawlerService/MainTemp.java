package org.newsio.webCrawlerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MainTemp {
    public static final Logger logger = LoggerFactory.getLogger(MainTemp.class);
    public static void main(String[] args) {
//        TechNewsArticleDaoImpl newsArticleDao = new TechNewsArticleDaoImpl();
//        newsArticleDao.__init__();
     //   EntertainmentNewsHTMLParser.getNewsFromETOnline();
      //  PoliticalNewsHTMLParser.getNewsFromMoneyControl(1);
       // TechNewsHTMLParser.getNewsFromHackerNews();
        PoliticalNewsArticlDaoImpl politicalNewsArticlDao = new PoliticalNewsArticlDaoImpl();
        politicalNewsArticlDao.__init__();
    }
}