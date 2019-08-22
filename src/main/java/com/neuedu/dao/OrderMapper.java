package com.neuedu.dao;

import com.neuedu.pojo.Order;

import java.util.List;

public interface OrderMapper {
    int insert(Order order);

    Order selectByPrimaryKey(Integer id);

    List<Order> selectAllByUserId(Integer id);

    Order selectByOrderNo(Long orderNo);

    int existOrderNo(Long orderNo);

    int cancelStatusByOrderNo(Long orderNo);

    int updateByPrimaryKey(Order order);

    List<Long> selectOrderCanClose(String time);

    int CloseTimeOutOrderByOrderNo(Long orderNo);
}
