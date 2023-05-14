package com.mca.shardingsphere.mapper;

import com.mca.shardingsphere.bean.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * ClassName: OrderMapper
 * Package: com.mca.shardingsphere.mapper
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/8 - 23:39
 * @version: v1.0
 */
@Repository
@Mapper
public interface OrderMapper {

    @Insert("insert into bus_order values(#{id},#{orderType},#{customerId},#{amount})")
    public void insert(Order order);

    @Select("select * from bus_order where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderType", column = "order_type"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "amount", column = "amount")
    })
    public Order selectOne(Integer id);

    @Select("select * from bus_order where id = #{id} and customer_id = #{customerId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderType", column = "order_type"),
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "amount", column = "amount")
    })
    public Order selectOne2(Integer id, Integer customerId);
}
