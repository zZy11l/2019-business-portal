package com.neuedu.service;

import com.neuedu.commen.ServerResponse;
import com.neuedu.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

public interface IShippingService {
    ServerResponse addShipping(Shipping shipping);

    ServerResponse delShipping(@Param("id") Integer shippingId);

    ServerResponse updateShipping(Shipping shipping);

    ServerResponse selectShipping(Integer shippingId);

    ServerResponse selectShippingList(Integer pageNum, Integer pageSize, Integer id);
}
