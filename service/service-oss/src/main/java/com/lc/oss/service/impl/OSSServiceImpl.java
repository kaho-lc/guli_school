package com.lc.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lc.oss.service.OSSService;
import com.lc.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OSSServiceImpl implements OSSService {
    //上传头像到oss
    @Override
    public String uploadFileAvatar(MultipartFile file) {

        String endpoint = ConstantPropertiesUtils.END_POINT;

        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;

        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;

        // 填写Bucket名称，例如examplebucket。
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            //通过file来创建输入流
            InputStream inputStream = file.getInputStream();

            //获取文件原始名称
            String fileName = file.getOriginalFilename();

            //在文件名称后加上一个uuid值，实现了相同文件的上传不覆盖
            fileName = UUID.randomUUID().toString().replaceAll("-", "") + fileName;


            //将上传的文件按照日期进行分类
            //获取当前日期
            String datePath = new DateTime().toString("yyyy/MM/dd");

            //将日期与文件名拼接起来
            fileName = datePath + "/" + fileName;


            // 创建PutObject请求。实现上传
            //参数1:Bucket名称 参数2:上传到oss的文件的路径和文件名称 参数3:上传文件的输入流
            ossClient.putObject(bucketName, fileName, inputStream);


            //将上传到oss的路径进行返回
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
}