package com.Zyuchen.oss.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadFileAvatar(String module, MultipartFile file);
}
