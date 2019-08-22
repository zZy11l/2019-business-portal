package com.neuedu.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import com.neuedu.commen.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;


@Controller
@ResponseBody
@RequestMapping("/order/")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create.do")
    public ServerResponse createOrder(Integer shippingId){


        return orderService.createOrder(shippingId);

    }

    @RequestMapping("get_order_cart_product.do")
    public ServerResponse getOrderCartProduct(HttpSession session){


        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");

        return null;

    }


    @RequestMapping("list.do")
    public ServerResponse getOrderList(HttpSession session,
                                       Integer pageNum,
                                       Integer pageSize){


        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");

        return orderService.getOrderList(pageNum,pageSize,userInfo.getId());

    }


    @RequestMapping("detail.do")
    public ServerResponse getOrderDetailById(Long orderNo){

        return orderService.getOrderDetailById(orderNo);
    }


    @RequestMapping("cancel.do")
    public ServerResponse cancelOrderById(Long orderNo){

        return orderService.cancelOrderById(orderNo);
    }

    @RequestMapping("pay.do")
    public ServerResponse payOrder(Long orderNo){


        return orderService.payOrder(orderNo);
    }
    @RequestMapping("query_order_pay_status.do")
    public ServerResponse queryOrderPayStatus(Long orderNo){

        return orderService.queryOrderPayStatus(orderNo);

    }


    @RequestMapping("alipay_callback.do")
    public String callBack(HttpServletRequest request){

        Map<String,String[]> params=request.getParameterMap();
        Map<String,String> requestparams= Maps.newHashMap();
        Iterator<String> it=params.keySet().iterator();
        while (it.hasNext()){
            String key=it.next();
            String[] strArr=params.get(key);
            String value="";
            for(int i=0;i<strArr.length;i++){
                value=(i==strArr.length-1)?value+strArr[i]:value+strArr[i]+",";
            }
            requestparams.put(key,value);
        }


        requestparams.remove("sign_type");
        try {
            boolean result=AlipaySignature.rsaCheckV2(requestparams, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());

            if(!result){
                return  "failed";
            }


        } catch (AlipayApiException e) {
            e.printStackTrace();
        }


        return orderService.alipay_callback(requestparams);

    }




}
