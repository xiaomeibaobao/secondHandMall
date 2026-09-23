package com.hzs.main.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author 220419
 * @description
 * @date 2026/9/23
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Value("${file.upload.dir:uploads/}")
    private String uploadDir;
    @Value("${file.upload.url-prefix:/uploads/}")
    private String urlPrefix;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File dir = new File(uploadDir);
        if(!dir.isAbsolute()) {
            String projectRoot = System.getProperty("user.dir");
            dir = new File(projectRoot + File.separator + uploadDir);
        }
        String absolutePath = dir.getAbsolutePath() + File.separator;
        registry.addResourceHandler(urlPrefix + "**").addResourceLocations("file:" + absolutePath);
    }
}
