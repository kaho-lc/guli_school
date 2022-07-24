package com.lc.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface OSSService {
    String uploadFileAvatar(MultipartFile file);
}
