package com.thc.io;

import java.io.InputStream;

/**
 * @author : tanghaochen
 * create at:  2020-02-22  22:45
 * @program IPersistence_test
 * @description:
 */
public class Resources {

    //读取配置文件，将配置文件加载成字节输入流读取到内存中
    public static InputStream getResourceAsStream(String path) {
        InputStream inputStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return inputStream;
    }
}
