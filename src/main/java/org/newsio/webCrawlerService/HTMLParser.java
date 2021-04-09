package org.newsio.webCrawlerService;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class HTMLParser  extends TimerTask {

    private static final Logger LOGGER  = Logger.getLogger(HTMLParser.class.getName());
    private static final Dao<NewsArticle, Integer> NEWS_ARTICLE_INTEGER_DAO = new PostgreSqlDao();
    public static Calendar
         dateParser(String str)  {
            String[] strArray = str.split("\uE804");

            String dateString = strArray[0].substring(1);

            int year = Integer.parseInt((dateString.split(" "))[2]);
            int day  =  Integer.parseInt(dateString.split(",")[0].split(" ")[1]);
            String month = (dateString.split(",")[0].split(" ")[0]);
            SimpleDateFormat format1 = new SimpleDateFormat("dd MMM yyyy");
            try {
                String date  =String.format("%d %s %d",day,month.substring(0,3).toLowerCase(),year);
                Date temp = format1.parse(date);

                Calendar cal  = Calendar.getInstance();
                cal.setTime(temp);

                return cal;
            }catch (DateTimeException | ParseException e) {
                e.printStackTrace();
                return null;
            }
    }
    public static String nameParser(String str) {
        String[] strArray = str.split("\uE804");
        return strArray[1];
    }

    public static void getNews() {
        List<String> latestTitleListDatabase =(NEWS_ARTICLE_INTEGER_DAO.getLatestDatabase().get());
        List<NewsArticle> latestNews = new ArrayList<>();
        try {
            Document doc = Jsoup.connect( "https://thehackernews.com/search?max-results=5").get();
            Elements mainElement = doc.getElementsByClass("main clear");
            Elements storyTitleAllElement  = mainElement.select("div.main-box.clear")
                    .select("h1.story-title-all");
            String storyTitleAll  = storyTitleAllElement.toString();
            System.out.println(storyTitleAll);
            Elements blogElements = doc.getElementsByClass("body-post clear");

            blogElements.forEach(element -> {

                 Elements storyLinkElement = element.select("a.story-link");
                 String storyLink  = storyLinkElement.attr("href");
                 Elements homeTitleElement = element.getElementsByClass("home-title");
                 String homeTitle = homeTitleElement.text();
                 Elements imgLinkElement  = element.getElementsByClass("home-img clear").select("div.img-ratio")
                                                                                                .select("img.home-img-src ");
                 String imgLink = imgLinkElement.attr("data-src");
                 Elements titleElement = element.getElementsByClass("clear home-right").select("h2.home-title");
                 String title  = titleElement.text();

                 Elements dateElement = element.getElementsByClass("item-label");
                 String blogDateName =  dateElement.text();
                 Calendar blogDate =  dateParser(blogDateName);
                 String authorName = nameParser(blogDateName);
                 Elements descriptionElement = element.getElementsByClass("home-desc");
                 String description = descriptionElement.text();
                 NewsArticle newNewsArticle = new NewsArticle();
                 newNewsArticle.articleTitle = title;
                 newNewsArticle.authorName = authorName;
                 newNewsArticle.description = description;
                 newNewsArticle.imgLink = imgLink;
                 newNewsArticle.publishedDate = blogDate;
                 newNewsArticle.storyLink = storyLink;
                 newNewsArticle.homeTitle = homeTitle;
                 boolean isDuplicated = false;
                 for(int i = 0; i < latestTitleListDatabase.size(); i++)  {
                     if (title.equals(latestTitleListDatabase.get(i))) {
                         isDuplicated = true;
                     }
                 }

                if (!isDuplicated) {
                    latestNews.add(newNewsArticle);
                }


            });
            Collections.reverse(latestNews);
            latestNews.forEach(news -> {
                addNewsArticle(news);
            });


        } catch(IOException e) {
            e.printStackTrace();
        }


       ;


    }
    public static Optional<Integer> addNewsArticle(NewsArticle newsArticle) {
        return NEWS_ARTICLE_INTEGER_DAO.save(newsArticle);
    }

    @Override
    public void run() {
       getNews();
    }
}
