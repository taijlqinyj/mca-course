package com.mca.disruptor.v1;

import com.lmax.disruptor.EventHandler;

/**
 * ClassName: LongEventHandler
 * Package: com.mca.disruptor.v1
 * Description: 产生消费者
 *
 * @Author: yujie.qin
 * @Create: 2023/3/21 - 20:34
 * @version: v1.0
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        System.out.println("LongEventHandler == " + event.getValue());
    }
}
