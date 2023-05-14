package com.mca.jvm.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * ClassName: T010_ClassReloading1
 * Package: com.mca.jvm.classloader
 * Description: 打破类加载器双亲委派机制
 * 热加载类，热部署
 *
 * @Author: yujie.qin
 * @Create: 2023/3/12 - 11:28
 * @version: v1.0
 */
public class T010_ClassReloading2 {

    private static class MyLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            File file = new File("E:\\project\\mca-course\\JVM\\target\\classes\\" + name.replace(".", "/").concat(".class"));
            if (!file.exists()) {
                return super.loadClass(name);
            }
            try {
                InputStream is = new FileInputStream(file);

                byte[] bytes = new byte[is.available()];
                is.read(bytes);
                return defineClass(name, bytes, 0, bytes.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.loadClass(name);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        MyLoader myLoader = new MyLoader();
        Class<?> clazz = myLoader.loadClass("com.mca.jvm.bytecode.ByCode01");

        myLoader = new MyLoader();
        Class<?> clazzNew = myLoader.loadClass("com.mca.jvm.bytecode.ByCode01");

        System.out.println(clazz == clazzNew);
    }
}
