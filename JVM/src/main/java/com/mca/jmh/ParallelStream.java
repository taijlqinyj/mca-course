package com.mca.jmh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ClassName: ParallelStream
 * Package: com.mca.jmh
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 15:43
 * @version: v1.0
 */
public class ParallelStream {
    static List<Integer> nums = new ArrayList<>();
    static {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) nums.add(1000000 + r.nextInt(1000000));
    }

    static void foreach() {
        nums.forEach(v->isPrime(v));
    }

    static void parallel() {
        nums.parallelStream().forEach(ParallelStream::isPrime);
    }

    static boolean isPrime(int num) {
        for(int i=2; i<=num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
