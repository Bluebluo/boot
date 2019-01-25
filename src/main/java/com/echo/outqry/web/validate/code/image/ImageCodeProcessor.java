package com.echo.outqry.web.validate.code.image;

import com.echo.outqry.web.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @program: outqry
 * @description: 图片验证码处理器
 * @author: Li Hongmou
 * @create: 2019-01-21 16:21
 **/
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * @param request
     * @Description: 生成验证码
     * @Param: [request]
     * @return: void
     * @Author: Li HongMou
     * @Date: 2019/1/21
     */
    @Override
    public void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getBufferedImage(),"JPEG",request.getResponse().getOutputStream());
    }

}
