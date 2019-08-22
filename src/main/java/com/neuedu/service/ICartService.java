package com.neuedu.service;

import com.neuedu.commen.ServerResponse;

public interface ICartService {
    ServerResponse getCartList(Integer id);

    ServerResponse addProduct(Integer productId, Integer count,Integer id);

    ServerResponse updateProductCount(Integer productId, Integer count, Integer id);

    ServerResponse deleteCartProducts(Integer[] productIds, Integer id);

    ServerResponse selectCartProduct(Integer productId, Integer id);

    ServerResponse unselectCartProduct(Integer productId, Integer id);

    ServerResponse getCartProductCount(Integer id);

    ServerResponse selectCartProductAll(Integer id);

    ServerResponse unselectCartProductAll(Integer id);
}
