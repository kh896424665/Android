package com.example.kh.omgandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by KH on 2018/6/2.
 */

public class DetailActivity extends AppCompatActivity {     ///这里有点问题
    private static final String IMAGE_URL_BASE = "http://covers.openlibrary.org/b/id/";
    String mImageURL;
    ShareActionProvider mShareActionProvider;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView imageView = (ImageView)findViewById(R.id.img_cover);
        String coverID = this.getIntent().getExtras().getString("coverID");
        if (coverID.length()>0){
            mImageURL = IMAGE_URL_BASE+coverID+"-L.jpg";
            Picasso.with(this).load(mImageURL).placeholder(R.mipmap.img_books_loading).into(imageView);
        }

    }


//    private void setShareIntent(){
//        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.setType("text/plain");
//        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Book Recommendation!");
//        shareIntent.putExtra(Intent.EXTRA_TEXT,mImageURL);
//
//        mShareActionProvider.setShareIntent(shareIntent);
//    }

    private void setShareIntent(){
    if (mShareActionProvider !=null){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Book Recommendation!");
        shareIntent.putExtra(Intent.EXTRA_TEXT,mImageURL);
        mShareActionProvider.setShareIntent(shareIntent);
        }
    }

//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.main,menu);
//        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
//
//        if (shareItem != null){
//            mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem); //文档中括号内没内容
//
//        }
//        setShareIntent();
//        return true;
//    }


        public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
        if (shareItem != null){
            mShareActionProvider =(ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        }
        setShareIntent();
        return true;
    }


}
