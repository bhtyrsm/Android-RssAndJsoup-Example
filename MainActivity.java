package com.example.lenovo.ogrenerekkonus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements ParseFeedCallback {
    private ListView listView;
    TextView txt;
    Rss rss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.rssListview);
        txt=(TextView)findViewById(R.id.topTitle);




        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                 rss = (Rss) listView.getAdapter().getItem(position);

                Intent myintent= new Intent(getApplicationContext() ,Main2Activity.class);
                myintent.putExtra("link", Uri.parse(rss.getOriginalPostUrl().trim()).toString());
                startActivity(myintent);

            }
        });


             new ParseFeedAsyncTask(this).execute((Void) null);


    }


    public void finishedLoadingFeeds(List<Rss> feeds) {
        listView.setAdapter(new RssListAdapter(getApplicationContext(), feeds));
    }



    public boolean internetErisimi() {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);

        if (conMgr.getActiveNetworkInfo() != null

                && conMgr.getActiveNetworkInfo().isAvailable()

                && conMgr.getActiveNetworkInfo().isConnected()) {

            return true;

        } else {

            return false;

        }

    }



}