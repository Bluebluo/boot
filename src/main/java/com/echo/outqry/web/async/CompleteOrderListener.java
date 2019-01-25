package com.echo.outqry.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.async.DeferredResult;

@Component
public class CompleteOrderListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderQueue orderQueue;

    @Autowired
    private DeferredResultHandler deferredResultHandler;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        new Thread(() -> {
            while (true) {
                if (!StringUtils.isEmpty(orderQueue.getCompleteOrder())) {
                    String orderNumber = orderQueue.getCompleteOrder();
                    deferredResultHandler.getMap().get(orderNumber).setResult("订单处理完成" + orderNumber);
                    logger.info("订单处理完成" + orderNumber);
                    orderQueue.setCompleteOrder(null);
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
