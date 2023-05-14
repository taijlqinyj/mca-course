package com.mca.jmh;

import org.openjdk.jmh.annotations.*;

/**
 * ClassName: TestParallelStream
 * Package: com.mca.jmh
 * Description: JMH 微基准测试
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 15:42
 * @version: v1.0
 */
public class TestParallelStream {

    @Benchmark // 测试哪一段代码
    @Warmup(iterations = 10, time = 1) // 预热，由于JVM中对于特定代码会存在优化（本地化），预热对于测试结果很重要
    @Fork(16) // 线程数，由fork指定
    @BenchmarkMode(value = Mode.Throughput) // 基准测试的模式 Throughput--吞吐量
    @Measurement(iterations = 9, time = 1) // 总共执行多少次测试
    public void test(){
        ParallelStream.parallel();
    }
}
