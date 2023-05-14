package com.mca.jvm.classloader;

import com.mca.jvm.bytecode.ByCode01;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.FileInputStream;

/**
 * ClassName: T005_MyClassLoader
 * Package: com.mca.jvm.classloader
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/12 - 9:27
 * @version: v1.0
 */
public class T005_MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File("target/classes/com/mca/jvm/classloader", name.replaceAll(".", "/").concat(".class"));
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteOutputStream byteOutputStream = new ByteOutputStream();
            int b = 0;

            while ((b = fileInputStream.read()) != 0){
                byteOutputStream.write(b);
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
        ClassLoader myClassLoader = new T005_MyClassLoader();
        Class<?> aClass = myClassLoader.loadClass("com.mca.jvm.bytecode.ByCode01");
        ByCode01 b01 = (ByCode01) aClass.newInstance();

        System.out.println(myClassLoader.getClass().getClassLoader());
        System.out.println(myClassLoader.getParent());
    }
}
