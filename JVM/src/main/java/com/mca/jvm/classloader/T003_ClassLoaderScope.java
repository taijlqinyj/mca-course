package com.mca.jvm.classloader;

/**
 * ClassName: T001_ClassLoaderLevel
 * Package: com.mca.jvm.classloader
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/11 - 17:56
 * @version: v1.0
 */
public class T003_ClassLoaderScope {
    public static void main(String[] args) {
        // Bootstrap ClassLoader 加载路径
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println(pathBoot.replaceAll(";", System.lineSeparator()));

        System.out.println("------------------------");
        // ExtensionClassLoader 加载路径
        String pathExt = System.getProperty("java.ext.dirs");
        System.out.println(pathExt.replaceAll(";", System.lineSeparator()));

        System.out.println("------------------------");
        // AppClassLoader 加载路径
        String pathApp = System.getProperty("java.class.path");
        System.out.println(pathApp.replaceAll(";", System.lineSeparator()));
    }
}
