package com.example.li.leeutil.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.li.leeutil.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpURLConnectionFetchPictureActivity extends AppCompatActivity {
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_urlconnection);

        imageView = (ImageView) this.findViewById(R.id.imageView1);
        //传入网络图片地址


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    // 方案1
//                    // 安装tomcat后，查看本地IP地址，然后将“192.168.1.7”替换为自己的IP即可
//                    // URL url = new URL("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
//                    URL url = new URL("http://192.168.1.7:8080/tomcat.png");
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    //HttpURLConnection默认就是用GET发送请求，所以下面的setRequestMethod可以省略
//                    conn.setRequestMethod("GET");
//                    //HttpURLConnection默认也支持从服务端读取结果流，所以下面的setDoInput也可以省略
//                    conn.setDoInput(true);
//
//                    //禁用网络缓存
//                    conn.setUseCaches(false);
//                    //获取请求头
//                    String requestHeader = getReqeustHeader(conn);
//                    //在对各种参数配置完成后，通过调用connect方法建立TCP连接，但是并未真正获取数据
//                    //conn.connect()方法不必显式调用，当调用conn.getInputStream()方法时内部也会自动调用connect方法
//                    conn.connect();
//                    //调用getInputStream方法后，服务端才会收到请求，并阻塞式地接收服务端返回的数据
//                    InputStream is = conn.getInputStream();
//
//                    //将InputStream转换成byte数组,getBytesByInputStream会关闭输入流
//                    byte[] responseBody = null;//响应体
//                    responseBody = getBytesByInputStream(is);
//
//                    final Bitmap bitmap = BitmapFactory.decodeByteArray(responseBody, 0, responseBody.length);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            imageView.setImageBitmap(bitmap);
//                        }
//                    });

                    // 方案2
                    URL url = new URL("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png");
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5*1000);
                    conn.connect();
                    InputStream in=conn.getInputStream();
                    ByteArrayOutputStream bos=new ByteArrayOutputStream();
                    byte[] buffer=new byte[1024];
                    int len = 0;
                    while((len=in.read(buffer))!=-1){
                        bos.write(buffer,0,len);
                    }
                    byte[] dataImage=bos.toByteArray();
                    bos.close();
                    in.close();
                    Bitmap bitmap=BitmapFactory.decodeByteArray(dataImage, 0, dataImage.length);
                    //Drawable drawable=BitmapDrawable.
                    imageView.setImageBitmap(bitmap);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "图片加载失败", Toast.LENGTH_LONG).show();
                }
            }
        }
        ).start();

//


    }

    //读取请求头
    private String getReqeustHeader(HttpURLConnection conn) {
        //https://github.com/square/okhttp/blob/master/okhttp-urlconnection/src/main/java/okhttp3/internal/huc/HttpURLConnectionImpl.java#L236
        Map<String, List<String>> requestHeaderMap = conn.getRequestProperties();
        Iterator<String> requestHeaderIterator = requestHeaderMap.keySet().iterator();
        StringBuilder sbRequestHeader = new StringBuilder();
        while (requestHeaderIterator.hasNext()) {
            String requestHeaderKey = requestHeaderIterator.next();
            String requestHeaderValue = conn.getRequestProperty(requestHeaderKey);
            sbRequestHeader.append(requestHeaderKey);
            sbRequestHeader.append(":");
            sbRequestHeader.append(requestHeaderValue);
            sbRequestHeader.append("\n");
        }
        return sbRequestHeader.toString();
    }

    //从InputStream中读取数据，转换成byte数组，最后关闭InputStream
    private byte[] getBytesByInputStream(InputStream is) {
        byte[] bytes = null;
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        byte[] buffer = new byte[1024 * 8];
        int length = 0;
        try {
            while ((length = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, length);
            }
            bos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }
}
