package com.mca.jvm.classloader;

/**
 * ClassName: T010_ClassReloading1
 * Package: com.mca.jvm.classloader
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/12 - 11:28
 * @version: v1.0
 */
public class T010_ClassReloading1 {
    public static void main(String[] args) throws ClassNotFoundException {
        T005_MyClassLoader myClassLoader = new T005_MyClassLoader();
        Class<?> clazz = myClassLoader.loadClass("com.mca.jvm.bytecode.ByCode01");

        myClassLoader = null;
        System.out.println(clazz.hashCode());

        myClassLoader = null;

        myClassLoader = new T005_MyClassLoader();
        Class<?> clazz1 = myClassLoader.loadClass("com.mca.jvm.bytecode.ByCode01");
        System.out.println(clazz1.hashCode());

        System.out.println(clazz == clazz1);
    }
}
