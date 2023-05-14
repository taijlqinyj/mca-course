package com.mca.shardingsphere.mapper;

import com.mca.shardingsphere.bean.OrderDetail;
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
public interface OrderDetailMapper {

    @Insert("insert into bus_order_detail values(#{id},#{detail},#{orderId})")
    public void insert(OrderDetail orderDetail);

    @Select("select * from bus_order_detail where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "detail", column = "detail"),
            @Result(property = "orderId", column = "order_id")
    })
    public OrderDetail selectOne(Integer id);

}
