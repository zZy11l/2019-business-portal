package com.neuedu.dao;

import com.neuedu.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    int existCategoryId(@Param("id") int categoryId);

    List<Category> getCategoryByParentId(@Param("parentId") int categoryId);


}
