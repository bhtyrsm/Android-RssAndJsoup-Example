package com.example.lenovo.ogrenerekkonus;

/**
 * Created by lenovo on 9.9.2017.
 */
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RssParser {

    public static List<Rss> parseFeed(String feedContent) {
        List<Rss> results = new ArrayList<Rss>();
        Rss rss = new Rss();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            xpp.setInput(new StringReader(feedContent));

            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                if (eventType == XmlPullParser.START_DOCUMENT) {

                }
                else if (eventType == XmlPullParser.START_TAG) {
                    String tagName = xpp.getName();
                    if ("item".equals(tagName)) {
                        rss = new Rss();
                    }
                    else if ("description".equals(tagName)) {
                        String desc = xpp.nextText();
                        rss.setContent(desc);

                        Pattern p = Pattern.compile(".*<img[^>]*src=\"([^\"]*)", Pattern.CASE_INSENSITIVE);
                        Matcher m = p.matcher(desc);
                        String srcUrl = null;
                        while (m.find()) {
                            srcUrl = m.group(1);
                            rss.setImageUrl(srcUrl.toString().trim());
                        }
                    }
                    else if ("dc:date".equals(tagName)) {
                        rss.setPostDate(xpp.nextText());
                    }
                    else if ("pubDate".equals(tagName)) {
                        rss.setPostDate(xpp.nextText());
                    }
                    else if ("title".equals(tagName)) {
                        rss.setTitle(xpp.nextText());
                    }
                    else if ("link".equals(tagName)) {
                        rss.setOriginalPostUrl(xpp.nextText());
                    }
                }
                else if (eventType == XmlPullParser.CDSECT) {
                }
                else if (eventType == XmlPullParser.END_TAG) {
                    String tagName = xpp.getName();
                    if ("item".equals(tagName)) {
                        results.add(rss);
                    }
                }
                else if (eventType == XmlPullParser.TEXT) {
                }
                eventType = xpp.nextToken();

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.e("Main", "Error : " + e.getMessage());
        }
        return results;
    }
}