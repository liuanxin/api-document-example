package com.gihtub.liuanxin.config;

import com.gihtub.liuanxin.constant.WebConstant;
import com.github.liuanxin.api.annotation.EnableApiInfo;
import com.github.liuanxin.api.model.DocumentCopyright;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableApiInfo
public class ApiInfoConfig {

    @Value("${online:false}")
    private boolean online;

    @Bean
    public DocumentCopyright apiCopyright() {
        return new DocumentCopyright()
                .setTitle(WebConstant.PROJECT_TITLE)
                .setContact(WebConstant.PROJECT_CONTACT)
                .setVersion(WebConstant.PROJECT_VERSION)
                .setCopyright(WebConstant.PROJECT_COPYRIGHT)
                .setOnline(online);
    }
}
