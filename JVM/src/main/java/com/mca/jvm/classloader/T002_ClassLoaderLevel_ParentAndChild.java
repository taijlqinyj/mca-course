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
public class T002_ClassLoaderLevel_ParentAndChild {
    public static void main(String[] args) {
        System.out.println(T002_ClassLoaderLevel_ParentAndChild.class.getClassLoader());
        System.out.println(T002_ClassLoaderLevel_ParentAndChild.class.getClassLoader().getClass().getClassLoader());
        System.out.println(T002_ClassLoaderLevel_ParentAndChild.class.getClassLoader().getParent());
        System.out.println(T002_ClassLoaderLevel_ParentAndChild.class.getClassLoader().getParent().getParent());

    }
}
