package com.neuedu.controller;

import com.neuedu.commen.ServerResponse;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("list.do")
    public ServerResponse getProductList(Integer categoryId,
                                         String keyword,
                                         Integer pageNum,
                                         Integer pageSize,
                                         String orderBy){

       return productService.getProductList(categoryId,keyword,pageNum,pageSize,orderBy);
    }

    @RequestMapping("detail.do")
    public ServerResponse getProductsDetail(Integer productId,
                                            Integer is_new,
                                            Integer is_hot,
                                            Integer is_banner){

        return productService.getProductsDetail(productId,is_new,is_hot,is_banner);
    }





}

