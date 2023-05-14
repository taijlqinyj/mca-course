package com.mca.disruptor.v2;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * ClassName: LongEventProducer
 * Package: com.mca.disruptor.v2
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

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR = new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
        @Override
        public void translateTo(LongEvent event, long sequence, ByteBuffer bb) {
            event.setValue(bb.getLong(0));
        }
    };

    public void onData(ByteBuffer buffer) {
        ringBuffer.publishEvent(TRANSLATOR, buffer);
    }
}
