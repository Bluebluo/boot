package com.echo.outqry.web.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/file")
public class FileController {

    final String folder = "E:\\boot\\boot\\src\\main\\resources\\static\\";

    @PostMapping
    public String create(MultipartFile file) throws IOException {
        System.out.println(file.getName()+file.getSize());
        File localFile = new File(folder,file.getName()+new Date().getTime()+".txt");
        file.transferTo(localFile);
        return localFile.getName();
    }

    @GetMapping("/{name}")
    public void download(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
        File file = new File(folder+name+".txt");
        try(
                InputStream in = new FileInputStream(file);
                OutputStream out = response.getOutputStream();
        ){
            IOUtils.copy(in,out);
            response.setHeader("Content-Type","application/octet-stream");
            response.addHeader("Content-Disposition","attachment;filename=hello.txt");
            out.flush();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
