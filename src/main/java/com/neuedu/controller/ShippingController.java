package com.neuedu.controller;

import com.neuedu.commen.ServerResponse;
import com.neuedu.pojo.Shipping;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/shipping/")
public class ShippingController {

    @Autowired
    private IShippingService shippingService;

    @RequestMapping("add.do")
    public ServerResponse addShipping(Shipping shipping){
        return shippingService.addShipping(shipping);
    }
    @RequestMapping("del.do")
    public ServerResponse delShipping(Integer shippingId){
        return shippingService.delShipping(shippingId);
    }

    @RequestMapping("update.do")
    public ServerResponse updateShipping(Shipping shipping){
        return shippingService.updateShipping(shipping);
    }

    @RequestMapping("select.do")
    public ServerResponse selectShipping(Integer shippingId,
                                         HttpSession session){

        return shippingService.selectShipping(shippingId);
    }

    @RequestMapping("list.do")
    public ServerResponse selectShippingList(Integer pageNum,
                                             Integer pageSize,
                                             HttpSession session){

        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");
        return shippingService.selectShippingList(pageNum,pageSize,userInfo.getId());

    }




}
