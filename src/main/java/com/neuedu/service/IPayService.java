package com.neuedu.service;

import com.neuedu.commen.ServerResponse;

public interface IPayService {
    ServerResponse payOrder(Long orderNo);
}
