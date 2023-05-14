package com.mca.shardingsphere.mapper;

import com.mca.shardingsphere.bean.OrderDetail;
import com.mca.shardingsphere.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * ClassName: UserMapper
 * Package: com.mca.shardingsphere.mapper
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/10 - 20:42
 * @version: v1.0
 */
@Repository
@Mapper
public interface UserMapper {
    @Insert("insert into t_user values(#{userId},#{pwd},#{userName})")
    public void insert(User user);
}
