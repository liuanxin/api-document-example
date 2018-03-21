package com.gihtub.liuanxin.config;

import com.gihtub.liuanxin.constant.WebConstant;
import com.gihtub.liuanxin.util.JsonCode;
import com.github.liuanxin.api.annotation.EnableApiInfo;
import com.github.liuanxin.api.model.DocumentCopyright;
import com.github.liuanxin.api.model.DocumentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableApiInfo
public class ApiInfoConfig {

    @Value("${online:false}")
    private boolean online;

    @Bean
    public DocumentCopyright apiCopyright() {
        return new DocumentCopyright()
                .setTitle(WebConstant.PROJECT_TITLE)
                .setTeam(WebConstant.PROJECT_CONTACT)
                .setVersion(WebConstant.PROJECT_VERSION)
                .setCopyright(WebConstant.PROJECT_COPYRIGHT)
                .setOnline(online)
                .setIgnoreUrlSet(ignoreUrl())
                .setGlobalResponse(globalResponse());
    }

    private Set<String> ignoreUrl() {
        Set<String> urlSet = new HashSet<>();
        urlSet.add("/error");
        return urlSet;
    }

    private List<DocumentResponse> globalResponse() {
        List<DocumentResponse> responseList = new ArrayList<>();
        for (JsonCode code : JsonCode.values()) {
            responseList.add(new DocumentResponse(code.getFlag(), code.getMsg()));
        }
        return responseList;
    }
}
