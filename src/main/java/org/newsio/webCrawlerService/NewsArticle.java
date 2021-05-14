package org.newsio.webCrawlerService;
import java.util.Calendar;

public class NewsArticle {
    int _id;
    String homeTitle;

    public String getStoryLink() {
        return storyLink;
    }

    public void setStoryLink(String storyLink) {
        this.storyLink = storyLink;
    }

    String storyLink;
    String imgLink;
    String articleTitle;
    Calendar publishedDate;
    String authorName;
    String description;

    public int get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = Integer.parseInt(_id);
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
void setPublishedDate(Calendar publishedDate) {
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

    @Override
    public String toString() {
        return "NewsArticle{" +
                "_id=" + _id +
                ", homeTitle='" + homeTitle + '\'' +
                ", storyLink='" + storyLink + '\'' +
                ", imgLink='" + imgLink + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", publishedDate=" + publishedDate +
                ", authorName='" + authorName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
