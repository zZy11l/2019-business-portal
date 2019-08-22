package com.neuedu.controller;

import com.neuedu.commen.ServerResponse;
import com.neuedu.pojo.UserInfo;
import com.neuedu.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private ICartService cartService;

    @RequestMapping("list.do")
    public ServerResponse getCartList(HttpSession session){
        if(session.getAttribute("login_user")==null){
            return ServerResponse.createServerResponseByFail(10,"用户未登录");
        }
        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");

        return cartService.getCartList(userInfo.getId());



    }

    @RequestMapping("add.do")
    public ServerResponse addCartProduct(Integer productId,
                                         Integer count,
                                         HttpSession session){

        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");
        return cartService.addProduct(productId,count,userInfo.getId());

    }

    @RequestMapping("update.do")
    public ServerResponse updateCartProductCount(Integer productId,
                                                 Integer count,
                                                 HttpSession session){


        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");

        return cartService.updateProductCount(productId,count,userInfo.getId());
    }


    @RequestMapping("delete_product.do")
    public ServerResponse deleteCartProducts(Integer[] productIds,
                                                 HttpSession session){


        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");

        return cartService.deleteCartProducts(productIds,userInfo.getId());
    }

    @RequestMapping("select.do")
    public ServerResponse selectCartProduct(Integer productId,
                                            HttpSession session){


        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");

        return cartService.selectCartProduct(productId,userInfo.getId());
    }

    @RequestMapping("un_select.do")
    public ServerResponse unselectCartProduct(Integer productId,
                                            HttpSession session){


        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");

        return cartService.unselectCartProduct(productId,userInfo.getId());
    }

    @RequestMapping("get_cart_product_count.do")
    public ServerResponse getCartProductCount(HttpSession session){


        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");

        return cartService.getCartProductCount(userInfo.getId());
    }


    @RequestMapping("select_all.do")
    public ServerResponse selectCartProductAll(
                                            HttpSession session){


        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");

        return cartService.selectCartProductAll(userInfo.getId());
    }

    @RequestMapping("un_select_all.do")
    public ServerResponse unselectCartProductAll(
            HttpSession session){


        UserInfo userInfo=(UserInfo)session.getAttribute("login_user");

        return cartService.unselectCartProductAll(userInfo.getId());
    }



}
