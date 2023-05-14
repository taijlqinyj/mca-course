package com.mca.shardingsphere.mapper;

import com.mca.shardingsphere.bean.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ClassName: CustomerMapper
 * Package: com.mca.shardingsphere.mapper
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/9 - 15:54
 * @version: v1.0
 */
@Repository
@Mapper
public interface CustomerMapper {

    @Insert("insert into bus_customer(id,name) values(#{id},#{name})")
    public void insert(Customer customer);

}
