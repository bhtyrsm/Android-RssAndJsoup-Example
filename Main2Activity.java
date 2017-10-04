package com.example.lenovo.ogrenerekkonus;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main2Activity extends Activity {


    private String  strUrl;
    private ProgressDialog progressDialog;
    TextView txt;
    String tt = "1", er = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt=(TextView)findViewById(R.id.txtShow);
        Intent intent = getIntent();
        strUrl=intent.getStringExtra("link").toString();


        new FetchDescription().execute(); // açıklama çekmek için



    }


    private class FetchDescription extends AsyncTask<Void, Void, Void> {

        String desc;
        String veri;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(Main2Activity.this);
            progressDialog.setTitle("AÇIKLAMA");
            progressDialog.setMessage("Açıklama Çekiliyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try{

                Document doc  = Jsoup.connect(strUrl).get();
                Elements elements = doc.select("div[class=article-main-component__content]");

                elements.select("p");
                elements.select("img").remove();
                 veri=elements.html();
                desc=Jsoup.parse(veri).text();



            }catch (Exception e){

                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            Toast.makeText(getApplicationContext(),"asdva" +Jsoup.parse(veri).text(),Toast.LENGTH_LONG);


            txt.setText(desc);
            progressDialog.dismiss();
        }
    }



}
