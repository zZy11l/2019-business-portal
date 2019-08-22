package com.neuedu.service;

import com.neuedu.commen.ServerResponse;

public interface IProductService {
    ServerResponse getProductList(Integer categoryId,String keyword, Integer pageNum, Integer pageSize, String orderBy);

    ServerResponse getProductsDetail(Integer productId, Integer is_new, Integer is_hot, Integer is_banner);
}
