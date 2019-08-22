package com.neuedu.service;

import com.neuedu.commen.ServerResponse;

public interface ICategoryService {
    ServerResponse getCategory(int categoryId);

    ServerResponse getDeepCategory(int categoryId);
}
