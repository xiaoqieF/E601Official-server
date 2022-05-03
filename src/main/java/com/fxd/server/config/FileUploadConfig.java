package com.fxd.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 配置静态资源映射
@Configuration
public class FileUploadConfig implements WebMvcConfigurer {

    @Value("${win-upload-file-path}")
    private String winFileSavePath;

    @Value("${unix-upload-file-path}")
    private String unixFileSavePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        // win系统
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/images/**")
                    .addResourceLocations("file:" + winFileSavePath);
        } else {
            // unix系统
            registry.addResourceHandler("/images/**")
                    .addResourceLocations("file:" + unixFileSavePath);
        }
    }
}
