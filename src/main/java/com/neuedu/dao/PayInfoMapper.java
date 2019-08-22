package com.neuedu.dao;

import com.neuedu.pojo.PayInfo;

public interface PayInfoMapper {

    int insert(PayInfo payInfo);

    int exsitId(Long orderNo);

    int update(PayInfo payInfo);
}
