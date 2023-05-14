package com.mca.jvm.classloader;

import sun.awt.HKSCS;
import sun.net.spi.nameservice.dns.DNSNameService;

/**
 * ClassName: T001_ClassLoaderLevel
 * Package: com.mca.jvm.classloader
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/11 - 17:56
 * @version: v1.0
 */
public class T001_ClassLoaderLevel {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(HKSCS.class.getClassLoader());
        System.out.println(DNSNameService.class.getClassLoader());
        System.out.println(T001_ClassLoaderLevel.class.getClassLoader());

        System.out.println(DNSNameService.class.getClassLoader().getClass().getClassLoader());
        System.out.println(T001_ClassLoaderLevel.class.getClassLoader().getClass().getClassLoader());
    }
}
