package com.neuedu.dao;

import com.neuedu.pojo.Shipping;

import java.util.List;

public interface ShippingMapper {
    int insert(Shipping shipping);

    int deleteByPrimaryKey(Integer shippingId);

    int updateByPrimaryKey(Shipping shipping);

    Shipping selectByPrimaryKey(Integer shippingId);

    List<Shipping> selectAll(Integer id);
}
