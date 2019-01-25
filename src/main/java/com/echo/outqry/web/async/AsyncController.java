package com.echo.outqry.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api")
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping(value="/order", produces="application/json;charset=UTF-8")
    public Callable<String> order(){
        logger.info("主线程开始");
        Callable<String> result = () -> {
            logger.info("副线程开始");
            Thread.sleep(1000);
            logger.info("副线程结束");
            return "success结束啦";
        };
        logger.info("主线程结束");
        return result;
    }


    @Autowired
    private DeferredResultHandler deferredResultHandler;

    @Autowired
    private OrderQueue orderQueue;

    @PostMapping(value="/orders", produces="application/json;charset=utf-8")
    public DeferredResult<String> orderPost(){
        Random random = new Random();
        String orderNum = String.valueOf(random.nextInt()*10000);
        orderQueue.setPlaceOrder(orderNum);
        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResultHandler.getMap().put(orderNum, deferredResult);
        return deferredResult;
    }
}
