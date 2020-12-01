package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import org.apache.commons.io.FilenameUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("admin/product")
public class FileUploadController {

    @Value("${fileServer.url}")
    private String fileServerUrl;
    /**
     *   1.  读取 trakcer.conf ,初始化。
     *         2.  创建一个trakcerClient
     *         3.  创建一个tradkerServer
     *         4.  创建一个stroageClient
     *         5.  上传
     *         6.  将上传之后的文件url 放入Result
    * */
    @PostMapping("fileUpload")
    public Result<String> fileUpload(MultipartFile file) throws IOException, MyException {
        String configFile = this.getClass().getResource("/tracker.conf").getFile();
        String path = null;
        if (configFile != null) {
            //1.初始化。
            ClientGlobal.init(configFile);
            //2.创建一个trakcerClient
            TrackerClient trackerClient = new TrackerClient();
            //3.创建一个trakerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //4.创建一个stroageClient
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, null);
            //5.上传
            //第一个参数表示上传文件的字节数组，第二个参数后缀名
            String filename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(filename);
            //  获取上上传之后的url group1/M00/00/01/wKjIgF9zVUOEAQ_2AAAAAPzxDAI115.png
            path = storageClient1.upload_appender_file1(file.getBytes(), extension, null);

            System.out.println(fileServerUrl + path);
        }
        return Result.ok(fileServerUrl + path);
    }
/*

    //  文件上传的时候：注意文件服务器有可能会更改ip 地址。就不能将ip 写死在代码中，应该放在配置文件中：软编码。
    @Value("${fileServer.url}")
    private String fileServerUrl;  // fileServerUrl = http://192.168.200.128:8080/
    //  springMVC 文件上传对象
    //  http://api.gmall.com/admin/product/fileUpload
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) throws IOException, MyException {
        */
/*
        1.  读取 trakcer.conf ,初始化。
        2.  创建一个trakcerClient
        3.  创建一个tradkerServer
        4.  创建一个stroageClient
        5.  上传
        6.  将上传之后的文件url 放入Result
         *//*

        String configFile = this.getClass().getResource("/tracker.conf").getFile();

        String path = null;
        //  判断
        if (configFile!=null){
            //  初始化
            ClientGlobal.init(configFile);
            //  创建一个trakcerClient
            TrackerClient trackerClient = new TrackerClient();
            //  创建一个tradkerServer
            TrackerServer trackerServer = trackerClient.getConnection();
            //  创建一个stroageClient
            StorageClient1 storageClient1 = new StorageClient1(trackerServer,null);
            //  上传 xxx.jpg  yyy.jpg;
            //  第一个参数表示上传文件的字节数组，第二个参数后缀名
            String originalFilename = file.getOriginalFilename();
            String extName = FilenameUtils.getExtension(originalFilename);
            //  获取上上传之后的url group1/M00/00/01/wKjIgF9zVUOEAQ_2AAAAAPzxDAI115.png
            path = storageClient1.upload_appender_file1(file.getBytes(), extName, null);

            System.out.println(fileServerUrl+path);
        }

        //  http://192.168.200.128:8080/group1/M00/00/01/wKjIgF9zVUOEAQ_2AAAAAPzxDAI115.png
        //  返回数据
        return Result.ok(fileServerUrl+path);
    }
*/
}
