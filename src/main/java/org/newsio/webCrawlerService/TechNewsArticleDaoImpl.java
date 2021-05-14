package org.newsio.webCrawlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public  class TechNewsArticleDaoImpl extends TechNewsHTMLParser implements Dao<NewsArticle> {
    public static final Logger techNewsArticleImpl_LOGGER =
            LoggerFactory.getLogger(NewsArticle.class);
    private static final Connection databaseConnection;

    static {
        databaseConnection = DatabaseConnection.getConnection();
        try {
            DatabaseMetaData databaseMetaData=  databaseConnection.getMetaData();
            ResultSet tables = databaseMetaData.getTables(null, null, "technews",null);

            if(tables.next()) {techNewsArticleImpl_LOGGER.info("Table already exists {}", tables.getString("TABLE_NAME"));
            } else {
                techNewsArticleImpl_LOGGER.trace("Table does not exist creating table 'technews'");
                String createTechNewsTableQuery = "CREATE TABLE technews(" +
                        "_id serial PRIMARY KEY, " +
                        "storyLink TEXT NOT NULL, " +
                        "imgLink TEXT ," +
                        "title TEXT NOT NULL UNIQUE," +
                        "publishedDate VARCHAR(50) ," +
                        "description TEXT NOT NULL," +
                        "publisherName VARCHAR(200) NOT NULL," +
                        "authorName VARCHAR(200))";
                Statement stmt  = databaseConnection.createStatement();
                boolean isExecuted = stmt.execute(createTechNewsTableQuery);
                techNewsArticleImpl_LOGGER.trace("techNews Table created ");
            }
        } catch (SQLException exception) {
            techNewsArticleImpl_LOGGER.trace(exception.toString());
            exception.printStackTrace();
        }


    }
    public   void __init__(){
        List<NewsArticle> listOfNewsArticles  = getNewsFromHackerNews();
        listOfNewsArticles.forEach(this::save);
        System.out.println("this is running");
    }

    /* This function get the lastest 10 article form the database */
    public Optional<List<String>> getLatest() {
        List<String> latestNews = new ArrayList<>();
        String sql = "select title from technews where _id >= ((select max(_id) from technews)-10)";
        try (Statement stmt = databaseConnection.createStatement();){
            ResultSet resultSet= stmt.executeQuery(sql);
            while(resultSet.next()) {
                latestNews.add(resultSet.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(latestNews);
    }
    @Override
    public Optional<NewsArticle> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<NewsArticle> getAll() {
        List<NewsArticle> allNews = new ArrayList<>();
        String sql =  "SELECT * FROM technews";
        try (Statement stmt = databaseConnection.createStatement();){
            ResultSet resultSet= stmt.executeQuery(sql);
            while(resultSet.next()) {
                NewsArticle newsArticle = new NewsArticle();
                newsArticle.setArticleTitle(resultSet.getString("title"));
                newsArticle.set_id(resultSet.getString("_id"));
                newsArticle.setDescription(resultSet.getString("description"));
                newsArticle.setAuthorName(resultSet.getString("authorname"));
                newsArticle.setImgLink(resultSet.getString("imglink"));
                java.sql.Date dateOfPublishing = resultSet.getDate("publisheddate");
                Calendar cal  = new GregorianCalendar();
                cal.setTime(dateOfPublishing);
                newsArticle.setPublishedDate(cal);
                allNews.add(newsArticle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  allNews;
    }

    @Override
    public Optional<List<String>> getLatestDatabase() {
        return Optional.empty();
    }

    @Override
    public void save(NewsArticle newsArticle) {
        Optional<List<String>>  latestNewsTitle = getLatest();
        latestNewsTitle.ifPresentOrElse(latestnews -> {
            latestnews.forEach(title -> {
                if (newsArticle.articleTitle.equals(title)) {
                    techNewsArticleImpl_LOGGER.info("Article :::Duplicate Found::: {} ", title);
                } else  {
                    int generatedId  = 0;
                    String sqlString = "INSERT INTO technews(storylink," +
                                " imglink,title,publisheddate,description,publishername,authorName) VALUES(?,?,?,?,?,?,?)";
                    try {
                        PreparedStatement statement = databaseConnection.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS);
                        statement.setString(1,newsArticle.storyLink);
                            statement.setString(2, newsArticle.imgLink);
                            statement.setString(3, newsArticle.articleTitle);



                            Date tempDate ;
                            tempDate = newsArticle.publishedDate.getTime();

                            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                            String date1 = format1.format(tempDate);
                            Date inActiveDate = null;
                            try {
                                inActiveDate = format1.parse(date1);
                            } catch (ParseException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }

                            statement.setDate(4, new java.sql.Date(inActiveDate.getTime()));
                            statement.setString(5, newsArticle.description);
                            statement.setString(6, "Hacker News");
                            statement.setString(7, newsArticle.authorName);
                            int noOfRowsInserted = statement.executeUpdate();
                        techNewsArticleImpl_LOGGER.info("Article :::CREATED::: {} ", title);
                    } catch (SQLException exception) {

                    }
                }
            });
        }, () -> {
                System.out.println("The latest news is empty");
        });
    }


    @Override
    public void update(NewsArticle newsArticle) {

    }

    @Override
    public void delete(NewsArticle newsArticle) {

    }

    Optional<List<NewsArticle>> getLatestNewsArticle(int noOfDays) {
        return Optional.empty();
    }
    Optional<List<NewsArticle>> getFromPublisher(String publisherId) {
        return Optional.empty();
    }
    Optional<List<NewsArticle>> getArticlesOnDate(Calendar fromDate , Calendar toDate) {
        return Optional.empty();
    }

    @Override
    public void run() {
        __init__();
    }
}
