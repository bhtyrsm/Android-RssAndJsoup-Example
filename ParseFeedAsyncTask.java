package com.example.lenovo.ogrenerekkonus;
import android.os.AsyncTask;

import java.util.List;


public class ParseFeedAsyncTask extends AsyncTask<Void, Void, List<Rss>> {
    private static final String feedUrl = "https://www.wired.com/feed/category/business/latest/rss";
    private ParseFeedCallback   callback;
    public ParseFeedAsyncTask(ParseFeedCallback callback) {
        this.callback = callback;
    }
    @Override
    protected List<Rss> doInBackground(Void... params) {
        String xmlContent = Downloader.getContent(feedUrl);
        return RssParser.parseFeed(xmlContent);
    }
    @Override
    protected void onPostExecute(List<Rss> result) {
        super.onPostExecute(result);
        callback.finishedLoadingFeeds(result);
    }
}

