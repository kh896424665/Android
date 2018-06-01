package com.example.kh.omgandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by KH on 2018/6/1.
 */

public class JSONAdapter extends BaseAdapter{
    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/";
    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;

    public JSONAdapter(Context context,LayoutInflater inflater){
        mContext = context;
        mInflater = inflater;
        mJsonArray = new JSONArray();
    }

    @Override
    public int getCount() {
        return mJsonArray.length();
    }

    @Override
    public Object getItem(int position) {  //返回给定位置的书，从0开始计数
        return mJsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView ==null){
            convertView = mInflater.inflate(R.layout.row_book,null);
            holder = new ViewHolder();
            holder.thumbnailImageView = (ImageView)convertView.findViewById(R.id.img_thumbnail);
            holder.titleTextView = (TextView)convertView.findViewById(R.id.text_title);
            holder.autherTextView = (TextView)convertView.findViewById(R.id.text_auther);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        JSONObject jsonObject = (JSONObject) getItem(position);
        if (jsonObject.has("cover_i")){
            String imageID = jsonObject.optString("cover_i");

            String imageURL = IMAGE_URL_BASE+imageID+"-S.jpg";
            Picasso.with(mContext).load(imageURL).placeholder(R.mipmap.ic_books).into(holder.thumbnailImageView);
        }else{
            holder.thumbnailImageView.setImageResource(R.mipmap.ic_books);
        }

        String bookTitle = "";
        String autherName = "";
        if (jsonObject.has("title")){
            bookTitle = jsonObject.optString("title");
        }
        if (jsonObject.has("auther_name")){
            autherName = jsonObject.optJSONArray("auther_name").optString(0);
        }
        holder.titleTextView.setText(bookTitle);
        holder.autherTextView.setText(autherName);
        return convertView;
    }

    private static class ViewHolder{
        public ImageView thumbnailImageView;
        public TextView titleTextView;
        public TextView autherTextView;

    }

    public void updateData(JSONArray jsonArray){
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }
}