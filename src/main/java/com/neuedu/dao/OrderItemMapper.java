package com.neuedu.dao;

import com.neuedu.pojo.OrderItem;

import java.util.List;

public interface OrderItemMapper {
    int insert(OrderItem orderItem);

    OrderItem selectByPrimaryKey(Integer id);

    List<OrderItem> selectAllByOrderNo(Long orderNo);
}
