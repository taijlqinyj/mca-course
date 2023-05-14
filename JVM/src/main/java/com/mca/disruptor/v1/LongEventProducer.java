package com.mca.disruptor.v1;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * ClassName: LongEventProducer
 * Package: com.mca.disruptor.v1
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 20:36
 * @version: v1.0
 */
public class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer buffer) {
        long sequence = ringBuffer.next();
        try {
            LongEvent event = ringBuffer.get(sequence);
            event.setValue(buffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
