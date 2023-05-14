package com.mca.jvm.classloader;

import com.mca.jvm.bytecode.ByCode01;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * ClassName: T005_MyClassLoader
 * Package: com.mca.jvm.classloader
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/12 - 9:27
 * @version: v1.0
 */
public class T006_MyClassLoaderWithEncription extends ClassLoader {

    private static int seed = 0B10110110;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("E:\\project\\mca-course\\JVM\\target\\classes\\com\\mca\\jvm\\bytecode", name.replace(".", "/").concat(".class"));
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteOutputStream byteOutputStream = new ByteOutputStream();
            int b = 0;

            while ((b = fileInputStream.read()) != 0){
                byteOutputStream.write(b ^ seed);
            }

            byte[] bytes = byteOutputStream.toByteArray();
            byteOutputStream.close();
            fileInputStream.close();

            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    public static void main(String[] args) throws Exception {
        encFile("com.mca.jvm.bytecode.ByCode01");
        ClassLoader myClassLoader = new T006_MyClassLoaderWithEncription();
        Class<?> aClass = myClassLoader.loadClass("com.mca.jvm.bytecode.ByCode01");
        ByCode01 b01 = (ByCode01) aClass.newInstance();

        System.out.println(myClassLoader.getClass().getClassLoader());
        System.out.println(myClassLoader.getParent());
    }

    private static void encFile(String name) throws Exception {
        File file = new File("E:\\project\\mca-course\\JVM\\target\\classes\\", name.replace(".", "/").concat(".class"));
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(new File("E:\\project\\mca-course\\JVM\\target\\classes\\com\\mca\\jvm\\bytecode", name.replace(".", "/").concat(".myclass")));

        int b = 0;
        while ((b = fis.read()) != -1){
            fos.write(b ^ seed);
        }

        fis.close();
        fos.close();
    }
}
