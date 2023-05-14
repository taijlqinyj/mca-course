package com.mca.disruptor.v1;

import com.lmax.disruptor.EventFactory;

/**
 * ClassName: LongEventFactory
 * Package: com.mca.disruptor.v1
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 20:33
 * @version: v1.0
 */
public class LongEventFactory implements EventFactory<LongEvent> {
    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
