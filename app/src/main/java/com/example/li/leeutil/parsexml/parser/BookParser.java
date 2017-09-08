package com.example.li.leeutil.parsexml.parser;

import com.example.li.leeutil.parsexml.Book;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Li on 2017/9/8.
 */

public interface BookParser {
    /**
     * 解析输入流 得到Book对象集合
     */
    public List<Book> parse(InputStream is) throws Exception;

    /**
     * 序列化Book对象集合 得到XML形式的字符串
     */
    public String serialize(List<Book> books) throws Exception;
}
