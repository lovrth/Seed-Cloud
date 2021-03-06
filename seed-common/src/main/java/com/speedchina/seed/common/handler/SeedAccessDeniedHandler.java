package com.speedchina.seed.common.handler;


import com.speedchina.seed.common.entity.SeedResponse;
import com.speedchina.seed.common.utils.SeedUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户无权限403（资源服务器异常响应）
 * 覆盖原始异常响应
 */
public class SeedAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        SeedResponse seedResponse = new SeedResponse();
        SeedUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_FORBIDDEN, seedResponse.message("没有权限访问该资源"));
    }
}
