package com.mca.shardingsphere;

import com.mca.shardingsphere.bean.*;
import com.mca.shardingsphere.hint.bean.OrderItem;
import com.mca.shardingsphere.mapper.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class ShardingsphereApplicationTests {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private DictOrderTypeMapper dictOrderTypeMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private MSOrderMapper msOrderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Test
    public void insertOrder(){
        for (int i = 11; i <= 20; i++) {
            Order order = new Order();
            order.setId(i);
            order.setOrderType(i);
            order.setCustomerId(new Random().nextInt(100));
            order.setAmount(i * 100.00);
            orderMapper.insert(order);
        }
    }

    @Test
    public void selectOrder(){
        Order order = orderMapper.selectOne2(3,9);
        System.out.println(order);
    }

    @Test
    public void insertCustomer(){
        for (int i = 1; i <= 10; i++) {
            Customer customer = new Customer();
            customer.setId(i);
            customer.setName("Sunflower_" + i);
            customerMapper.insert(customer);
        }
    }

    @Test
    public void insertDictOrderType(){
        for (int i = 1; i <= 10 ; i++) {
            DictOrderType dictOrderType = new DictOrderType();
            dictOrderType.setId((long)i);
            dictOrderType.setOrderType("order_type_"+i);
            dictOrderTypeMapper.insertDictOrderType(dictOrderType);
        }
    }

    @Test
    public void deleteDictOrderType(){
        dictOrderTypeMapper.deleteDictOrderType(1);
    }

    @Test
    public void insertOrderDetail(){
        for (int i = 1; i <= 10 ; i++) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setId(i);
            orderDetail.setDetail("order_detail_" + i);
            orderDetail.setOrderId(i);
            orderDetailMapper.insert(orderDetail);
        }
    }

    @Test
    public void init() {
        for (int i = 1; i <= 10; i++) {
            int userId = new Random().nextInt(10);
            com.mca.shardingsphere.hint.bean.Order order = new com.mca.shardingsphere.hint.bean.Order();
            order.setOrderId(i);
            order.setUserId(userId);
            order.setAddressId(i);
            order.setStatus("ORDER_INIT_" + i);
            msOrderMapper.insert(order);

            OrderItem item = new OrderItem();
            item.setOrderItemId(i);
            item.setOrderId(i);
            item.setUserId(userId);
            item.setStatus("ORDER_ITEM_INIT_" + i);
            orderItemMapper.insert(item);

//            User user = new User();
//            user.setUserId(i);
//            user.setPwd("user_pwd_" + i);
//            user.setUserName("user_name_" + i);
//            userMapper.insert(user);
        }
    }
}
