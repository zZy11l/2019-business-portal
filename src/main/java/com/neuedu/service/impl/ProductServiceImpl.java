package com.neuedu.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neuedu.commen.ServerResponse;
import com.neuedu.dao.ProductMapper;
import com.neuedu.pojo.Product;
import com.neuedu.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;
    @Override
    public ServerResponse getProductList(Integer categoryId,String keyword, Integer pageNum, Integer pageSize, String orderBy) {

       if(pageNum==null)pageNum=1;
       if(pageSize==null)pageSize=10;



        List<Product> list;

       if(keyword!=null) keyword="%"+keyword+"%";
        if(orderBy.equals("price_desc")){
            PageHelper.startPage(pageNum,pageSize);
            list= productMapper.selectProductsByCategoryIdAndKeyword(categoryId,keyword,"DESC");
        }else if(orderBy.equals("price_asc")){
            PageHelper.startPage(pageNum,pageSize);
            list= productMapper.selectProductsByCategoryIdAndKeyword(categoryId,keyword,"ASC");
        }
        else {
            return ServerResponse.createServerResponseByFail(1,"参数错误");
        }

        PageInfo page=new PageInfo(list);



        return ServerResponse.createServerResponseBySuccess(null,page);

    }

    @Override
    public ServerResponse getProductsDetail(Integer productId, Integer is_new, Integer is_hot, Integer is_banner) {
        if(productId==null)return ServerResponse.createServerResponseByFail(1,"参数错误");

        Product product=productMapper.selectByPrimaryKey(productId);

        if(product.getStatus()!=1){
            return ServerResponse.createServerResponseByFail(4,"该商品已下架");
        }

        return ServerResponse.createServerResponseBySuccess(null,product);

    }
}
