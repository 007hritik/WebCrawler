package org.newsio.webCrawlerService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.*;

public class PoliticalNewsHTMLParser {
    private static Logger HTMLParser_LOGGER = LoggerFactory.getLogger(TechNewsHTMLParser.class);


    public static Calendar dateParser(String str) {

        int year = Integer.parseInt((str.split(" "))[2]);
        int day = Integer.parseInt(str.split(",")[0].split(" ")[1]);
;
        String month = (str.split(",")[0].split(" ")[0]);

        SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yyyy");
        try {
            String date = String.format("%d %s %d", day, month.substring(0, 3).toLowerCase(), year);
            Date temp = format1.parse(date);

            Calendar cal = Calendar.getInstance();
            cal.setTime(temp);


            return cal;
        } catch (DateTimeException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<NewsArticle> getNewsFromMoneyControl(int pageno) {
        List<NewsArticle> latestNews = new ArrayList<>();
        String url = "https://www.moneycontrol.com/news/politics/page-"+pageno+"/";
        System.out.println(url);
        try {
            Document doc = Jsoup.connect(url).get();
            Elements mainElement = doc.getElementsByClass("mid-contener contener clearfix").first().getElementsByClass("clearfix");
            mainElement.forEach(element -> {
               String newsUrl = element.getElementsByTag("a").attr("href");
               try {
                   Document newsDocuent = Jsoup.connect(newsUrl).get();
                   String articleTitle = newsDocuent.getElementsByClass("article_title").text();
                   String articleLink = newsUrl;
                   String imglink = newsDocuent.getElementsByClass("article_image_wrapper article_image_main_wrapper").first().getElementsByTag("img").first().attr("data-src");
                   String publisherName = "Money Control";
                   String authorName = newsDocuent.getElementsByClass("article_author").first().getElementsByTag("a").text();
                   String publishedDate = newsDocuent.getElementsByClass("article_schedule").first().getElementsByTag("span").text();
                   String articleContent = newsDocuent.getElementsByClass("content_wrapper").first().getElementsByTag("p").text();
                   NewsArticle newsArticle = new NewsArticle();
                   dateParser(publishedDate);
                  if (authorName.equals("")) {
                      authorName = "MoneyControl News";
                  }
                   NewsArticle newNews = new NewsArticle();
                   newNews.setImgLink(imglink);
                   newNews.setDescription(articleContent);
                   newNews.setArticleTitle(articleTitle);
                    newNews.setStoryLink(articleLink);
                   newNews.setPublishedDate(dateParser(publishedDate));
                   newNews.setAuthorName(authorName);

                    latestNews.add(newNews);


               } catch (Exception e) {
                   e.printStackTrace();
               }

            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return latestNews;
    }
}
