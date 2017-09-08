package com.example.li.leeutil.threadpool.CallBackFuture;

import android.util.Log;

import java.util.concurrent.Callable;

/**
 * Created by Li on 2017/9/8.
 */

public class CallableDemo implements Callable<Integer> {

    private int sum;

    @Override
    public Integer call() throws Exception {
        Log.i("leeTest------>", "Callable子线程开始计算啦！");
        Thread.sleep(10000);

        for (int i = 0; i < 5; i++) {
            sum = sum + i;
        }
        Log.i("leeTest------>", "Callable子线程计算结束！");
        return sum;
    }
}