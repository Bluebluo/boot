package com.echo.outqry;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OutqryApplicationTests {

    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void contextLoads() {
        System.out.println("加密后:"+stringEncryptor.encrypt("12345"));
    }

}
