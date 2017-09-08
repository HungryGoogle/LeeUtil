package com.example.li.leeutil.parsexml;

/**
 * SAX(Simple API for XML)解析器是一种基于事件的解析器
 * DOM解析器： DOM是基于树形结构的的节点或信息片段的集合，允许开发人员使用DOM API遍历XML树、检索所需数据。分析该结构通常需要加载整个文档和构造树形结构，然后才可以检索和更新节点信息。
 * 由于DOM在内存中以树形结构存放，因此检索和更新效率会更高。但是对于特别大的文档，解析和加载整个文档将会很耗资源。
 * PULL解析器的运行方式和SAX类似，都是基于事件的模式。不同的是，在PULL解析过程中，我们需要自己获取产生的事件然后做相应的操作，而不像SAX那样由处理器触发一种事件的方法，执行我们的代码。PULL解析器小巧轻便，解析速度快，简单易用，非常适合在Android移动设备中使用，Android系统内部在解析各种XML时也是用PULL解析器。
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.example.li.leeutil.R;
import com.example.li.leeutil.parsexml.parser.BookParser;
import com.example.li.leeutil.parsexml.parser.DomBookParser;
import com.example.li.leeutil.parsexml.parser.PullBookParser;
import com.example.li.leeutil.parsexml.parser.SaxBookParser;

import java.io.InputStream;
import java.util.List;

public class ParseXmlActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "LeeTest----->";
    private ViewGroup mShowXmlDatalayout;
    private BookParser parser;
    private List<Book> books;

    private Handler mUiHandler = new Handler() {
        public void handleMessage(Message msg) {
            List<Book> lists = (List<Book>) msg.obj;
            if (lists == null) {
                return;
            }

            for (int i = 0; i < lists.size(); i++) {
                Book book = lists.get(i);
                StringBuffer sb = new StringBuffer();
                sb.append(book.getId() + " " + book.getName() + " " + book.getPrice());

                TextView txt = new TextView(getApplicationContext());
                txt.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                txt.setText(sb.toString());
                mShowXmlDatalayout.addView(txt);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_xml);

        mShowXmlDatalayout = findViewById(R.id.layout);

        // xml解析
        findViewById(R.id.id_pull_parse_xml).setOnClickListener(this);
        findViewById(R.id.id_sax_parse_xml).setOnClickListener(this);
        findViewById(R.id.id_dom_parse_xml).setOnClickListener(this);

        // 清空数据
        findViewById(R.id.id_clear_data).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        // 如果是清空，直接处理后返回
        if (R.id.id_clear_data == view.getId()) {
            mShowXmlDatalayout.removeAllViews();
            return;
        }

        InputStream is = getClassLoader().getResourceAsStream("assets/book.xml");
        ;
        switch (view.getId()) {
            case R.id.id_pull_parse_xml: {
                parser = new PullBookParser();  // 创建实例
                break;
            }
            case R.id.id_sax_parse_xml: {
                parser = new SaxBookParser();  // 创建实例
                break;
            }
            case R.id.id_dom_parse_xml: {
                parser = new DomBookParser();  // 创建S实例
                break;
            }
        }

        // 解析输入流
        try {
            books = parser.parse(is);
            for (Book book : books) {
                Log.i(TAG, book.toString());
            }

            Message msg = mUiHandler.obtainMessage();
            msg.obj = books;
            mUiHandler.sendMessage(msg);

            String toXml = parser.serialize(books);
            Log.i(TAG, "to xml ----------------------------------");
            Log.i(TAG, toXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}