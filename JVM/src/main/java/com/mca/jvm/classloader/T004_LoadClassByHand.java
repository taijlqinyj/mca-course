package com.mca.jvm.classloader;

import java.io.InputStream;

/**
 * ClassName: T004_LoadClassByHand
 * Package: com.mca.jvm.classloader
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/12 - 9:18
 * @version: v1.0
 */
public class T004_LoadClassByHand {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = T004_LoadClassByHand.class.getClassLoader().loadClass("com.mca.jvm.classloader.T001_ClassLoaderLevel");
        System.out.println(clazz.getName());

        // 利用类加载器加载资源
        InputStream resourceAsStream = T004_LoadClassByHand.class.getClassLoader().getResourceAsStream("");
    }
}
