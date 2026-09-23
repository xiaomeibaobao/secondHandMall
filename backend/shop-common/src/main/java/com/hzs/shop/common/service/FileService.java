package com.hzs.shop.common.service;

import com.hzs.shop.common.exception.BusinessException;
import com.hzs.shop.common.utils.RequestUtil;
import com.hzs.shop.common.vo.FileUploadVO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author 220419
 * @description
 * @date 2026/9/23
 */
@Slf4j
@Service
public class FileService {
    @Value("${file.upload.dir:uploads/}")
    private String updateDir;
    @Value("${file.upload.url-prefix:/uploads/}")
    private String urlPrefix;

    private static final List<String> ALLOWED_TYPES = Arrays.asList("image/jpeg", "image/png", "image/gif", "image/webp");

    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024L;

    private String getAbsoluteUploadDir() {
        File dir = new File(updateDir);
        if(!dir.isAbsolute()) {
            String projectDir = System.getProperty("user.dir");
            dir = new File(projectDir, updateDir);
        }
        return dir.getAbsolutePath();
    }

    public FileUploadVO uploadImage(MultipartFile file, HttpServletRequest request) {
        if(file == null || file.isEmpty()) {
            throw new BusinessException(400, "请选择上传的图片");
        }
        if(file.getSize() > MAX_FILE_SIZE) {
            throw new BusinessException(400, "图片大小不能超过10MB");
        }
        if(!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new BusinessException(400, "不支持的图片格式");
        }
        try {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + suffix;
            String absolutePath = getAbsoluteUploadDir();
            File dir = new File(absolutePath);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            File targetFile = new File(dir, newFileName);
            file.transferTo(targetFile);
            String baseUrl = RequestUtil.getBaseUrl(request);
            String url = baseUrl + urlPrefix + newFileName;
            log.info("图片上传成功：{}", url);
            return FileUploadVO.builder()
                    .url(url)
                    .originalName(fileName)
                    .newName(newFileName)
                    .size(file.getSize())
                    .build();
        } catch (IOException e) {
            log.error("图片上传失败：{}",e.getMessage());
            throw new BusinessException(500, "图片上传失败");
        }
    }
}
