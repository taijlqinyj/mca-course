package com.mca.shardingsphere.mapper;

import com.mca.shardingsphere.hint.bean.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ClassName: MSOrderMapper
 * Package: com.mca.shardingsphere.mapper
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/10 - 20:39
 * @version: v1.0
 */
@Repository
@Mapper
public interface MSOrderMapper {
    @Insert("insert into t_order values(#{orderId},#{userId},#{addressId},#{status})")
    public void insert(Order order);
}
