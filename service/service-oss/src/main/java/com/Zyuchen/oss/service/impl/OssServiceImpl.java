package com.Zyuchen.oss.service.impl;

import com.Zyuchen.oss.service.OssService;
import com.Zyuchen.oss.utils.ConstantPropertiesUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(String module, MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try{
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();

            //同名文件重复上传会覆盖，使用UUID重命名文件
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid+fileName;

            //使用日期对文件进行分类
            String datePath = new DateTime().toString("yyyy/MM/dd");
            fileName = moduleToPath(module) + "/"+datePath+"/"+fileName;

            //bucketName
            //路径文件名
            //文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            ossClient.shutdown();

            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            System.out.println(url);
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public String moduleToPath(String module){
        Map<String, String> pathmMapping = new HashMap<String, String>();
        pathmMapping.put("user","avatar");
        pathmMapping.put("coach","avatar");
        pathmMapping.put("classroom","classRoomPicture");

        return pathmMapping.get(module);
    }
}
