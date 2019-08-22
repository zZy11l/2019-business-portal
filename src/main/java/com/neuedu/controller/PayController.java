package com.neuedu.controller;

import com.neuedu.commen.ServerResponse;
import com.neuedu.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/portal/order/")
@ResponseBody
@Controller
public class PayController {
    @Autowired
    private IPayService payService;

    @RequestMapping("pay.do")
    public ServerResponse payOrder(Long orderNo){
        return payService.payOrder(orderNo);
    }


}
