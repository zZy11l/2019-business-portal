package com.neuedu.schedule;

import com.neuedu.service.IOrderService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CloseOrderSchedule {


    @Value("${order.close.timeout}")
    private int orderTimeOut;

    @Autowired
    private IOrderService orderService;

    @Scheduled(cron = "* */2 * * * *")
    public void closeOrder(){
        Date closeOrderTime= DateUtils.addHours(new Date(),-orderTimeOut);

        System.out.println("===刷新订单===");
        orderService.closeOrder(com.neuedu.utils.DateUtils.dateToStr(closeOrderTime));

    }
}
