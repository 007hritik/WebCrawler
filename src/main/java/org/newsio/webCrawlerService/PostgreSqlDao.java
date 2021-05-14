//package org.newsio.webCrawlerService;
//import java.sql.*;
//import java.util.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class PostgreSqlDao implements Dao<NewsArticle, Integer>{
//    private final static Logger LOGGER = Logger.getLogger(PostgreSqlDao.class.getName());
//    private final Optional<Connection> connection;
//
//    public PostgreSqlDao() {
//        this.connection = JdbcConnection.databaseConnect() ;
//    }
//
//    @Override
//    public Optional<NewsArticle> get(int id) {
//        return Optional.empty();
//    }
//
//
//    @Override
//    public Collection<NewsArticle> getAll() {
//        return null;
//    }
//    @Override
//    public Optional<List<String>> getLatestDatabase() {
//        return connection.flatMap(conn -> {
//            Optional<List<String>> latestNewsTitles = Optional.of(new ArrayList<String>());
//            List<String> temp = new ArrayList<String>();
//
//            String sql =  "select _id, title from news_data where _id > ((select max(_id) from news_data)-5)";
//
//            try (Statement statement = conn.createStatement();
//                 ResultSet resultSet = statement.executeQuery(sql)) {
//
//                while (resultSet.next()) {
//                    String title = resultSet.getString("title");
//                    temp.add(title);
//
//                    LOGGER.log(Level.INFO, "Found {0} in database",title);
//                }
//                return Optional.of(temp);
//            } catch (SQLException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//
//            return latestNewsTitles;
//        });
//    }
//
//    @Override
//    public Optional<Map> save(NewsArticle newsArticle) {
//        String message  = "The news article to be added should not be null";
//        NewsArticle nonNullNewsArticle = Objects.requireNonNull(newsArticle, message);
//        String sqlString = "INSERT INTO " + " news_data(title, description , story_link, date_of_publishing, image_link,author)"
//                + " values(?,?,?,?,?,?)";
//        String getTitleAndId = "select _id, title from news_data where _id > (select max(_id) from news_data)-5";
//        return  connection.flatMap(conn -> {
//            Optional<Integer>  generatedId = Optional.empty();
//
//            try (PreparedStatement statement = conn.prepareStatement(sqlString, Statement.RETURN_GENERATED_KEYS)) {
//                statement.setString(1,newsArticle.articleTitle);
//                statement.setString(2, newsArticle.description);
//                statement.setString(3, newsArticle.storyLink);
//
//
////                Date tempDate = newsArticle.publishedDate.getTime();
//
//                    Date tempDate ;
//                tempDate = newsArticle.publishedDate.getTime();
//
//                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//                String date1 = format1.format(tempDate);
//                Date inActiveDate = null;
//                try {
//                    inActiveDate = format1.parse(date1);
//                } catch (ParseException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }
//                statement.setDate(4, new java.sql.Date(inActiveDate.getTime()));
//                statement.setString(5, newsArticle.imgLink);
//                statement.setString(6, newsArticle.authorName);
//                int noOfRowsInserted = statement.executeUpdate();
//
//                if(noOfRowsInserted > 0) {
//                    try(ResultSet resultSet =  statement.getGeneratedKeys()) {
//                        if(resultSet.next()) {
//                            generatedId = Optional.of(resultSet.getInt(1));
//;                        }
//                    }
//                }
//                LOGGER.log(Level.INFO, "{0} created successfully ? {1}",
//                new Object[]{nonNullNewsArticle, (noOfRowsInserted > 0)});
//
//            } catch (SQLException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//                ex.printStackTrace();
//            }
//            return generatedId;
//        });
//
//
//
//
//
//    }
//
//    @Override
//    public Optional<List<NewsArticle>> getLatest() {
//        return connection.flatMap(conn -> {
//            Optional<List<NewsArticle>> latestNewsArticles = Optional.of(new ArrayList<NewsArticle>());
//            List<String> temp = new ArrayList<String>();
//
//            String sql =  "select * from news_data where _id > ((select max(_id) from news_data)-10)";
//
//            try (Statement statement = conn.createStatement();
//                 ResultSet resultSet = statement.executeQuery(sql)) {
//
//                while (resultSet.next()) {
//                    NewsArticle temp2 = new NewsArticle();
//                    int _id = resultSet.getInt("_id");
//
//                    String title = resultSet.getString("title");
//
//                    String description = resultSet.getString("description");
//                    String storyLink = resultSet.getString("story_link");
//                    java.sql.Date dateOfPublishing = resultSet.getDate("date_of_publishing");
//                    String imageLink = resultSet.getString("image_link");
//                    String author = resultSet.getString("author");
//                    temp2._id = _id;
//                    temp2.articleTitle = title;
//                    temp2.authorName = author;
//                    temp2.description = description;
//                    temp2.imgLink = imageLink;
//                    Calendar cal = new GregorianCalendar();
//                    cal.setTime(dateOfPublishing);
//                    temp2.publishedDate = cal;
//                    latestNewsArticles.map(newsArticles -> newsArticles.add(temp2));
//
//                    LOGGER.log(Level.INFO, "Found {0} in database",title);
//                }
//                System.out.println(latestNewsArticles);
//                return latestNewsArticles;
//            } catch (SQLException ex) {
//                LOGGER.log(Level.SEVERE, null, ex);
//            }
//
//            return latestNewsArticles;
//        });
//    }
//
//    @Override
//    public void update(NewsArticle newsArticle) {
//
//    }
//
//    @Override
//    public void delete(NewsArticle newsArticle) {
//
//    }
//}
