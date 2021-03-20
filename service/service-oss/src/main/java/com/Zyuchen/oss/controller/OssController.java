package com.Zyuchen.oss.controller;


import com.Zyuchen.common.utils.R;
import com.Zyuchen.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api("OSS")
@RestController
@RequestMapping("/gmsoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/{module}")
    @ApiOperation("OSS文件上传")
    public R uploadOssFile(@ApiParam(name = "module", value = "模块名称", required = true)
                               @PathVariable String module,
                           @ApiParam(name = "file", value = "图片对象", required = true)
                           @RequestBody MultipartFile file){
        String url = ossService.uploadFileAvatar(module, file);
        return R.ok().data("url", url);
    }
}
