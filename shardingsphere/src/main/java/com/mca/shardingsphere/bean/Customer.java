package com.mca.shardingsphere.bean;

/**
 * ClassName: Customer
 * Package: com.mca.shardingsphere.bean
 * Description:
 *
 * @Author: yujie.qin
 * @Create: 2023/3/9 - 15:53
 * @version: v1.0
 */
public class Customer {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
