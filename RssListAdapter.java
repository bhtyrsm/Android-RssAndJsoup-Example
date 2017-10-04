package com.example.lenovo.ogrenerekkonus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

public class RssListAdapter extends BaseAdapter {

    private List<Rss>      rssList;
    private LayoutInflater inflater;
    private Context        context;
    private ImageLoader   imageLoader;

    public RssListAdapter(Context context, List<Rss> rssList) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.rssList = rssList;
        this.context = context;
        this.imageLoader = ImageLoader.getInstance();
        this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

    @Override
    public int getCount() {
        return rssList.size();
    }

    @Override
    public Object getItem(int position) {
        return rssList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        Rss rss = rssList.get(position);
        View rowView = view;
        TextView txtTitle;
        TextView txtDate;

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.row_main, null);
            txtTitle = (TextView) rowView.findViewById(R.id.rss_title);
            txtDate = (TextView) rowView.findViewById(R.id.rss_date);
            //txtTitle.setTypeface(Typeface.createFromAsset(context.getAssets(), "MuseoSansRounded-700.otf"));
            //txtDate.setTypeface(Typeface.createFromAsset(context.getAssets(), "HelveticaNeueMedium.ttf"));
        }
        txtDate = (TextView) rowView.findViewById(R.id.rss_date);
        txtTitle = (TextView) rowView.findViewById(R.id.rss_title);
        txtTitle.setText(rss.getTitle());
        txtDate.setText(rss.getPostDate());
        return rowView;
    }
}