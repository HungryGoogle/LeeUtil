package com.example.li.leeutil.jni;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.li.leeutil.R;

public class FirstJniActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_test);

        findViewById(R.id.id_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView tv = (TextView) findViewById(R.id.id_result);
                tv.setText(stringFromJNI());

                // 加密
                String src = "hello";
                String encodeString = encode(src, src.length());

                Log.i("leeTest------>", "src : " + src);
                Log.i("leeTest------>", "encode : " + encodeString);

                // 解密
                String decodeString = decode(encodeString, encodeString.length());
                Log.i("leeTest------>", "decode : " + decodeString);

                testInt(3);

                // Jni 传给C数组
                int[] array = {1, 2, 3, 4, 5};
                encodeArray(array);

                //不需要返回值，实际操作的是同一块内存，内容已经发生了改变
                for (int i = 0; i < 5; ++i) {
                    Log.i("leeTest------>", String.valueOf(array[i]));
                }

                // Jni C调用Android，打印的日志在logcat里面
                cLog();

                // Jni C调用Android的方法，打印的日志在logcat里面
                cCallJavaFunc();
            }
        });
    }

    /**
     *  这个函数，在{@link FirstJniActivity#cCallJavaFunc}会被反射调用
     */
    public void show(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("标题");
        builder.setMessage(message);
        builder.show();
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native String encode(String str, int strLength);
    public native String decode(String str, int strLength);
    public native String testInt(int strLength);
    public native void cLog();
    private native void encodeArray(int[] arr);
    public native void cCallJavaFunc();


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
}
