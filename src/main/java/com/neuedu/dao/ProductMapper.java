package com.neuedu.dao;

import com.neuedu.pojo.Product;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface ProductMapper {


     List<Product> selectProductsByCategoryIdAndKeyword(@Param("categoryId") Integer categoryId,
                                                        @Param("keyword") String keyword,

                                                        @Param("orderBy") String orderBy) ;

     Product selectByPrimaryKey(Integer productId);

    int updateStock(@Param("id") Integer id,@Param("quantity") int i);
}
