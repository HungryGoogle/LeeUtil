package com.example.li.leeutil.socket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.li.leeutil.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);

        findViewById(R.id.button_socket_connect_server).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Socket socket = new Socket("192.168.1.7", 3000);

                            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            final String line = br.readLine();
                            br.close();

                            socket.close();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ((TextView)findViewById(R.id.textView_server_response)).setText("服务器返回:" + line);
                                }
                            });

                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }
}
