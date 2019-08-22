package com.neuedu.controller;

import com.neuedu.commen.ServerResponse;
import com.neuedu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@ResponseBody
@RequestMapping("/manage/category/")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("get_category.do")
    public ServerResponse getCategory(int categoryId,
                                      HttpSession session){

        return categoryService.getCategory(categoryId);
    }

    @RequestMapping("get_deep_category.do")
    public ServerResponse getDeepCategory(int categoryId,HttpSession session){

        return categoryService.getDeepCategory(categoryId);
    }


}

