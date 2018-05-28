
package com.example.kh.omgandroid;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
//import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import android.support.v7.widget.ShareActionProvider;
public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    ShareActionProvider mShareActionProvider;
    TextView mainTextView;
    Button mainButton;
    EditText mainEditText;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList mNameList = new ArrayList();  //mNameList只是一个字符串列表
    private static final String PREFS = "prefs";
    private static final String PREF_NAME = "name";
    SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTextView =(TextView) findViewById(R.id.main_textview);
        mainTextView.setText("Set in java");
        mainButton = (Button) findViewById(R.id.main_button);
        mainButton.setOnClickListener(this);
        mainEditText = (EditText) findViewById(R.id.main_edittext);
        mainListView = (ListView) findViewById(R.id.main_listview);
        mArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,mNameList);//适配器
        mainListView.setAdapter(mArrayAdapter);
        mainListView.setOnItemClickListener(this);//监听列表

    }

    @Override
    public void onClick(View v) {
//        mainTextView.setText("Button pressed");
        mainTextView.setText(mainEditText.getText().toString() + "is learning Android development!!!");
        mNameList.add(mainEditText.getText().toString());
        mArrayAdapter.notifyDataSetChanged();
        setShareIntent();
        displayWelcome();
    }
    @Override
    public void onItemClick(AdapterView parent,View view,int position,long id){
        //position是列表第几个选项，从0开始计数
        Log.d("OMG_android",position+":"+mNameList.get(position));
    }
     @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        MenuItem shareItem = menu.findItem(R.id.menu_item_share);
        if (shareItem != null){
            mShareActionProvider =(ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
     }
        setShareIntent();
        return true;
     }
     private void setShareIntent(){
        if (mShareActionProvider !=null){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Android Development");
            shareIntent.putExtra(Intent.EXTRA_TEXT,mainTextView.getText());
            mShareActionProvider.setShareIntent(shareIntent);
        }
     }
    public void displayWelcome(){
         mSharedPreferences = getSharedPreferences(PREFS,MODE_PRIVATE);
         String name = mSharedPreferences.getString(PREF_NAME,"");
         if (name.length()>0){
             Toast.makeText(this,"Welcome back,"+name+"!",Toast.LENGTH_LONG).show();
         }
         else {
             AlertDialog.Builder alert = new AlertDialog.Builder(this);
             alert.setTitle("Hello!");
             alert.setMessage("What is your name?");
             final EditText input = new EditText(this);
             alert.setView(input);
             alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int whichButton) {
                     String inputName =input.getText().toString();
                     SharedPreferences.Editor e = mSharedPreferences.edit();
                     e.putString(PREF_NAME, inputName);
                     e.commit();
                     Toast.makeText(getApplicationContext(), "Welcome, " + inputName + "!", Toast.LENGTH_LONG).show();
                 }
             });
             alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog, int whichButton) {}
             });
             alert.show();

         }
    }
}
