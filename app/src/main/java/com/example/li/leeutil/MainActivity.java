package com.example.li.leeutil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.li.leeutil.Http.HttpClientActivity;
import com.example.li.leeutil.Http.HttpURLConnectionFetchPictureActivity;
import com.example.li.leeutil.parsexml.ParseXmlActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 解析xml
        findViewById(R.id.id_parse_xml).setOnClickListener(MainActivity.this);
        findViewById(R.id.id_http).setOnClickListener(MainActivity.this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_parse_xml:{
                startActivity(new Intent(MainActivity.this, ParseXmlActivity.class));
                break;
            }case R.id.id_http:{
//                startActivity(new Intent(MainActivity.this, NetworkActivity.class));
                startActivity(new Intent(MainActivity.this, HttpClientActivity.class));
                break;
            }
        }
    }
}
