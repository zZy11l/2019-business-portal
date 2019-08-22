package com.neuedu.service;

import com.neuedu.commen.ServerResponse;

import java.util.Map;


public interface IOrderService {
    void closeOrder(String time);

    ServerResponse createOrder(Integer shippingId);

    ServerResponse getOrderList(Integer pageNum, Integer pageSize, Integer id);

    ServerResponse getOrderDetailById(Long orderNo);

    ServerResponse cancelOrderById(Long orderNo);

    ServerResponse payOrder(Long orderNo);

    String alipay_callback(Map<String, String> map);

    ServerResponse queryOrderPayStatus(Long orderNo);
}
