package com.mca.shardingsphere.hint.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ClassName: MyHintAlgorithm
 * Package: com.mca.shardingsphere.hint.algorithm
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/9 - 21:13
 * @version: v1.0
 */
@Component
public class MyHintAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Long> hintShardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String each : availableTargetNames) {
            System.out.println("---------------availableTargetNames "+each);
            for (Long value : hintShardingValue.getValues()) {
                if (each.endsWith(String.valueOf(value % 2))) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
