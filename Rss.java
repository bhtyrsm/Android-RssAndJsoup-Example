package com.example.lenovo.ogrenerekkonus;

/**
 * Created by lenovo on 9.9.2017.
 */

public class Rss {

    private String title;
    private String content;
    private String postDate;
    private String imageUrl;
    private String originalPostUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOriginalPostUrl() {
        return originalPostUrl;
    }

    public void setOriginalPostUrl(String originalPostUrl) {
        this.originalPostUrl = originalPostUrl;
    }
}