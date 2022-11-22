package com.yang.springbootaliyunoss.entity;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 小强
 * @date: 2022/11/22 0022
 * @IDE: IntelliJ IDEA
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssProperties {
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    private String bucket;
    private String filehost;

    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

}
