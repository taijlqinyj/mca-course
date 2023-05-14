package com.mca.shardingsphere.bean;

/**
 * ClassName: DictOrderType
 * Package: com.mca.shardingsphere.mapper
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/9 - 16:21
 * @version: v1.0
 */
public class DictOrderType {
    private Long id;
    private String orderType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "DictOrderType{" +
                "id=" + id +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}
