package com.neuedu.service.impl;

import com.neuedu.commen.ServerResponse;
import com.neuedu.dao.OrderMapper;
import com.neuedu.pojo.PayInfo;
import com.neuedu.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayserviceImpl implements IPayService {

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public ServerResponse payOrder(Long orderNo) {
        if(orderNo==null){
            return ServerResponse.createServerResponseByFail(9,"参数不能为空");
        }
        if(orderMapper.existOrderNo(orderNo)==0){
            return ServerResponse.createServerResponseByFail(7,"要支付的订单不存在");
        }
        PayInfo payInfo=new PayInfo();
        payInfo.setOrderNo(orderNo);
        payInfo.setPayPlatform(1);

            return null;
    }
}
