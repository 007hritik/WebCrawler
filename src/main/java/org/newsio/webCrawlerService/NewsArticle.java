package org.newsio.webCrawlerService;
import java.util.Calendar;

public class NewsArticle {
    int _id;
    String homeTitle;
    String storyLink;
    String imgLink;
    String articleTitle;
    Calendar publishedDate;
    String authorName;
    String description;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getHomeTitle() {
        return homeTitle;
    }

    public void setHomeTitle(String homeTitle) {
        this.homeTitle = homeTitle;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Calendar getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Calendar publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
