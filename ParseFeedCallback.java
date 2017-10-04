package com.example.lenovo.ogrenerekkonus;

import java.util.List;

public interface ParseFeedCallback {
    public void finishedLoadingFeeds(List<Rss> feeds);
}