package com.neuedu.dao;

import com.neuedu.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    List<Cart> selectCartByUserId(@Param("id") Integer id);
    List<Cart> selectCartWithCheckedByUserId(@Param("id") Integer id);

    int insert(@Param("productId") Integer productId,
               @Param("count") Integer count,
               @Param("userId") Integer id);

    int update(@Param("productId") Integer productId,
               @Param("count") Integer count,
               @Param("userId") Integer id);

    int deleteCartProductById(@Param("productsId") Integer[] productsId,@Param("id") Integer id);

    int existProductById(@Param("productId") Integer productId,@Param("id") Integer id);

    int updateCheckedByProductId(@Param("productId") Integer productId,@Param("id") Integer id);

    int updateUncheckedByProductId(Integer productId, Integer id);

    int cartProductCount(@Param("id") Integer id);

    int updateCheckedAllByProductId(@Param("id") Integer id);

    int updateUncheckedAllByProductId(@Param("id") Integer id);

    int deleteByPrimaryKey(Integer id);
}
