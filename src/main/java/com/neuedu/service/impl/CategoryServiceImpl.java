package com.neuedu.service.impl;

import com.neuedu.commen.ServerResponse;
import com.neuedu.dao.CategoryMapper;
import com.neuedu.pojo.Category;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ServerResponse getCategory(int categoryId) {



        if(categoryId==0||categoryMapper.existCategoryId(categoryId)==0){
            return ServerResponse.createServerResponseByFail(1,"未找到该品类");
        }

        List<Category> list=categoryMapper.getCategoryByParentId(categoryId);

        return ServerResponse.createServerResponseBySuccess(null,list);

    }

    @Override
    public ServerResponse getDeepCategory(int categoryId) {

       List<Integer> categoryIdList=new ArrayList<>();

       List<Category> categoryList=categoryMapper.getCategoryByParentId(categoryId);

       int head=0;int tail=categoryList.size();
       while(tail!=head){
           List<Category> list=categoryMapper.getCategoryByParentId(categoryList.get(head++).getId());
           categoryList.addAll(list);
           tail=categoryList.size();
       }
       for(Category category:categoryList){
           categoryIdList.add(category.getId());
       }


        return ServerResponse.createServerResponseBySuccess(null,categoryIdList);
    }
}
