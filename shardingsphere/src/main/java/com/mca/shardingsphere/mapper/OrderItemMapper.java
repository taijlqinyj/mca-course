package com.mca.shardingsphere.mapper;

import com.mca.shardingsphere.bean.OrderDetail;
import com.mca.shardingsphere.hint.bean.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ClassName: OrderItemMapper
 * Package: com.mca.shardingsphere.mapper
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/10 - 20:41
 * @version: v1.0
 */
@Repository
@Mapper
public interface OrderItemMapper {
    @Insert("insert into t_order_item values(#{orderItemId},#{orderId},#{userId},#{status})")
    public void insert(OrderItem orderItem);
}
