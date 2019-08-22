package com.neuedu.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neuedu.commen.ServerResponse;
import com.neuedu.dao.ShippingMapper;
import com.neuedu.pojo.Shipping;
import com.neuedu.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private ShippingMapper shippingMapper;
    @Override
    public ServerResponse addShipping(Shipping shipping) {
        Integer addResult= shippingMapper.insert(shipping);
        if(addResult==0){
            return ServerResponse.createServerResponseByFail(1,"新建地址失败");
        }

        return ServerResponse.createServerResponseBySuccess("新建地址成功",shipping.getId());



    }

    @Override
    public ServerResponse delShipping(Integer shippingId) {
        int result=shippingMapper.deleteByPrimaryKey(shippingId);
        if(result==0)return ServerResponse.createServerResponseByFail(1,"删除地址失败");

        return ServerResponse.createServerResponseBySuccess("删除地址成功");
    }

    @Override
    public ServerResponse updateShipping(Shipping shipping) {
        System.out.println(shipping);
        Integer addResult= shippingMapper.updateByPrimaryKey(shipping);
        if(addResult==0){
            return ServerResponse.createServerResponseByFail(1,"更新地址失败");
        }

        return ServerResponse.createServerResponseBySuccess("更新地址成功");

    }

    @Override
    public ServerResponse selectShipping(Integer shippingId) {
        Shipping shipping=shippingMapper.selectByPrimaryKey(shippingId);
        return ServerResponse.createServerResponseBySuccess(null,shipping);

    }

    @Override
    public ServerResponse selectShippingList(Integer pageNum, Integer pageSize, Integer id) {
        if(pageNum==null||pageNum==0)pageNum=1;
        if(pageSize==null||pageSize==0)pageSize=10;
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> list= shippingMapper.selectAll(id);
        PageInfo pageInfo=new PageInfo(list);
        return ServerResponse.createServerResponseBySuccess(null,pageInfo);

    }
}
