package com.echo.outqry.web.validate.code.image;

import com.echo.outqry.web.validate.code.ValidateCode;

import java.awt.image.BufferedImage;

public class ImageCode extends ValidateCode {

    private BufferedImage bufferedImage;

    public ImageCode(String code, int expireIn, BufferedImage bufferedImage) {
        super(code,expireIn);
        this.bufferedImage = bufferedImage;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}
