package com.mca.shardingsphere.hint.bean;

/**
 * ClassName: Order
 * Package: com.mca.shardingsphere.hint.bean
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/9 - 20:54
 * @version: v1.0
 */
public class Order {
    private static final long serialVersionUID = 661434701950670670L;

    private long orderId;

    private int userId;

    private long addressId;

    private String status;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(final long orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(final long addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return String.format("order_id: %s, user_id: %s, address_id: %s, status: %s", orderId, userId, addressId, status);
    }
}
