package com.mca.collection.compare;

import java.util.Comparator;

/**
 * ClassName: MyOutComparator
 * Package: com.mca.collection.compare
 * Description: outer comparator
 *
 * @Author: yujie.qin
 * @Create: 2023/3/22 - 22:27
 * @version: v1.0
 */
public class MyOutComparator implements Comparator<Sunflower> {
    @Override
    public int compare(Sunflower o1, Sunflower o2) {
        // use age to compare
        return o1.getAge() - o2.getAge();
        // use name to compare
        // return o1.name.compareTo(o2.getName());
        // use height to compare
        // return o1.height.compareTo(o2.getHeight());
    }
}
