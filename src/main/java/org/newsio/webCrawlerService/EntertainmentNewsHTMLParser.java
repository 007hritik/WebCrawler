package org.newsio.webCrawlerService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EntertainmentNewsHTMLParser {
    private static Logger  logger = LoggerFactory.getLogger(EntertainmentNewsHTMLParser.class);

    public static Calendar

    dateParser(String str) {
        String[] strArray = str.split("\uE804");

        String dateString = strArray[0].substring(1);

        int year = Integer.parseInt((dateString.split(" "))[2]);
        int day = Integer.parseInt(dateString.split(",")[0].split(" ")[1]);
        String month = (dateString.split(",")[0].split(" ")[0]);
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
    public static String nameParser(String str) {
        String[] strArray = str.split("\uE804");
        return strArray[1];
    }

    public static List<NewsArticle> getNewsFromETOnline() {

        List<NewsArticle> latestNews = new ArrayList<>();
        try {
            String url = "https://www.etonline.com/news?page=0";
            Document doc = Jsoup.parse(new URL(url).openStream(), "ISO-8859-1", url);

            Elements mainElement = doc.getElementsByClass("main clear");
            Elements storyTitleAllElement = mainElement.select("div.main-box.clear")
                    .select("h1.story-title-all");
            String storyTitleAll = storyTitleAllElement.toString();
            Elements temp= doc.select("div.item-list").first().getElementsByTag("ul");
            Elements newsElements = temp.first().getElementsByTag("li");
            newsElements.forEach(element -> {
                //System.out.println(element);
                String storyLink = element.getElementsByClass("field-content").first().text();
                System.out.println(storyLink);
               // System.out.println(storyLink.attr("href"));

            });
//            blogElements.forEach(element -> {
//                Elements storyLinkElement = element.select("a.story-link");
//                String storyLink = storyLinkElement.attr("href");
//                Elements homeTitleElement = element.getElementsByClass("home-title");
//                String homeTitle = homeTitleElement.text();
//                Elements imgLinkElement = element.getElementsByClass("home-img clear").select("div.img-ratio")
//                        .select("img.home-img-src ");
//                String imgLink = imgLinkElement.attr("data-src");
//                Elements titleElement = element.getElementsByClass("clear home-right").select("h2.home-title");
//                String title = titleElement.text();
//
//                Elements dateElement = element.getElementsByClass("item-label");
//                String blogDateName = dateElement.text();
//                Calendar blogDate = dateParser(blogDateName);
//                String authorName = nameParser(blogDateName);
//                Elements descriptionElement = element.getElementsByClass("home-desc");
//                String description = descriptionElement.text();
//                NewsArticle newNewsArticle = new NewsArticle();
//                newNewsArticle.articleTitle = title;
//                newNewsArticle.authorName = authorName;
//                newNewsArticle.description = description;
//                newNewsArticle.imgLink = imgLink;
//                newNewsArticle.publishedDate = blogDate;
//                newNewsArticle.storyLink = storyLink;
//                newNewsArticle.homeTitle = homeTitle;
//                latestNews.add(newNewsArticle);
//            });


        } catch (IOException e) {
            e.printStackTrace();
        }
        return latestNews;

    }
}
