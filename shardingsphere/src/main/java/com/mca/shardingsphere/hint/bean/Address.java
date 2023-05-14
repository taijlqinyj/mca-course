package com.mca.shardingsphere.hint.bean;

/**
 * ClassName: Address
 * Package: com.mca.shardingsphere.hint.bean
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/9 - 20:54
 * @version: v1.0
 */
public class Address {
    private static final long serialVersionUID = 661434701950670670L;

    private Long addressId;

    private String addressName;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(final Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(final String addressName) {
        this.addressName = addressName;
    }
}
