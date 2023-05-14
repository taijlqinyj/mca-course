package com.mca.disruptor.v1;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

/**
 * ClassName: Main
 * Package: com.mca.disruptor.v1
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 20:36
 * @version: v1.0
 */
public class Main {
    public static void main(String[] args) {
        LongEventFactory factory = new LongEventFactory();

        // must be power of 2
        int bufferSize = 1024;

        Disruptor<LongEvent> disruptor = new Disruptor(factory, bufferSize, Executors.defaultThreadFactory());

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        for (long l = 0; l < 100; l++) {
            bb.putLong(0, l);

            producer.onData(bb);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        disruptor.shutdown();
    }
}
