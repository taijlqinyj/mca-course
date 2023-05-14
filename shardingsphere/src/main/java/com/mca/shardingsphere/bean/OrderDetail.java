package com.mca.shardingsphere.bean;

/**
 * ClassName: Order
 * Package: com.mca.shardingsphere.bean
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/8 - 23:36
 * @version: v1.0
 */
public class OrderDetail {
    private Integer id;
    private String detail;
    private Integer orderId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
