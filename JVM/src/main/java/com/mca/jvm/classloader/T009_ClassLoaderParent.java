package com.mca.jvm.classloader;

/**
 * ClassName: T009_ClassLoaderParent
 * Package: com.mca.jvm.classloader
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/12 - 11:17
 * @version: v1.0
 */
public class T009_ClassLoaderParent {

    private static T005_MyClassLoader parent = new T005_MyClassLoader();

    public static class MyClassLoader extends ClassLoader{
        public MyClassLoader() {
            super(parent);
        }
    }

    public static void main(String[] args) {
        MyClassLoader classLoader = new MyClassLoader();
        System.out.println(MyClassLoader.class.getClassLoader());
        System.out.println(classLoader.getParent());
    }

}
