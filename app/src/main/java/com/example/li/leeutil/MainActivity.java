package com.example.li.leeutil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.li.leeutil.http.HttpClientActivity;
import com.example.li.leeutil.jni.FirstJniActivity;
import com.example.li.leeutil.parsexml.ParseXmlActivity;
import com.example.li.leeutil.socket.SocketClientActivity;
import com.example.li.leeutil.threadpool.ThreadPoolActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 解析xml
        findViewById(R.id.id_parse_xml).setOnClickListener(MainActivity.this);
        findViewById(R.id.id_http).setOnClickListener(MainActivity.this);
        findViewById(R.id.id_socket).setOnClickListener(MainActivity.this);
        findViewById(R.id.id_threadpool).setOnClickListener(MainActivity.this);
        findViewById(R.id.id_JNI).setOnClickListener(MainActivity.this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.id_parse_xml:{
                startActivity(new Intent(MainActivity.this, ParseXmlActivity.class));
                break;
            }
            case R.id.id_http:{
//                startActivity(new Intent(MainActivity.this, NetworkActivity.class));
                startActivity(new Intent(MainActivity.this, HttpClientActivity.class));
                break;
            }
            case R.id.id_socket:{
                startActivity(new Intent(MainActivity.this, SocketClientActivity.class));
                break;
            }
            case R.id.id_threadpool:{
                startActivity(new Intent(MainActivity.this, ThreadPoolActivity.class));
                break;
            }
            case R.id.id_JNI:{
                startActivity(new Intent(MainActivity.this, FirstJniActivity.class));
                break;
            }
        }
    }
}
