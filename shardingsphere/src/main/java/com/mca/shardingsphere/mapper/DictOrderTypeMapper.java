package com.mca.shardingsphere.mapper;

import com.mca.shardingsphere.bean.DictOrderType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ClassName: DictOrderTypeMapper
 * Package: com.mca.shardingsphere.mapper
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/9 - 16:22
 * @version: v1.0
 */
@Repository
@Mapper
public interface DictOrderTypeMapper {

    @Insert("insert into dict_order_type(id, order_type) values(#{id}, #{orderType})")
    public void insertDictOrderType(DictOrderType dictOrderType);

    @Delete("delete from dict_order_type where id = #{id}")
    public void deleteDictOrderType(Integer id);
}
