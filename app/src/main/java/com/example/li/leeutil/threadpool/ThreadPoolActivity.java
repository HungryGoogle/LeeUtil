package com.example.li.leeutil.threadpool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.li.leeutil.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool);

        findViewById(R.id.id_SingleThreadPool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService fixedThreadPool = Executors.newSingleThreadExecutor();
                for (int i = 1; i <= 10; i++) {
                    final int index = i;
                    fixedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.i("leeTest------>", "线程："+threadName+",正在执行第" + index + "个任务");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

        findViewById(R.id.id_FixThreadPool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
                for (int i = 1; i <= 10; i++) {
                    final int index = i;
                    fixedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.i("leeTest------>", "线程："+threadName+",正在执行第" + index + "个任务");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });


        findViewById(R.id.id_CachedThreadPool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExecutorService fixedThreadPool = Executors. newCachedThreadPool ();
                for (int i = 1; i <= 100; i++) {
                    final int index = i;
                    fixedThreadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.i("leeTest------>", "线程：" + threadName + ",正在执行第" + index + "个任务");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });


        findViewById(R.id.id_ScheduledThreadPool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScheduledExecutorService fixedThreadPool = Executors. newScheduledThreadPool (5);
                for (int i = 1; i <= 10; i++) {
                    final int index = i;
                    fixedThreadPool.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.i("leeTest------>", "线程：" + threadName + ",正在执行第" + index + "个任务");
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 0, 20 * 1000, TimeUnit.MILLISECONDS);
                }
            }
        });

        // 0秒后开始，每过20秒，执行10个任务（10个任务10秒就执行完了）
        findViewById(R.id.id_newSingleThreadScheduledExecutor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ScheduledExecutorService fixedThreadPool = Executors. newSingleThreadScheduledExecutor ();
                for (int i = 1; i <= 10; i++) {
                    final int index = i;
                    fixedThreadPool.scheduleAtFixedRate(new Runnable() {
                        @Override
                        public void run() {
                            String threadName = Thread.currentThread().getName();
                            Log.i("leeTest------>", "线程：" + threadName + ",正在执行第" + index + "个任务");
                            try {
                                Thread.sleep(1000);
                                fixedThreadPool.shutdownNow();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }, 0, 20 * 1000, TimeUnit.MILLISECONDS);
                }
            }
        });
    }
}
