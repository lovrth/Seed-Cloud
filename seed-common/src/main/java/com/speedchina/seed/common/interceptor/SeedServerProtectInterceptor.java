package com.speedchina.seed.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.speedchina.seed.common.entity.SeedConstant;
import com.speedchina.seed.common.entity.SeedResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局拦截器拦截请求，并校验Zuul Token
 */
public class SeedServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 从请求头中获取 Zuul Token
        String token = request.getHeader(SeedConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(SeedConstant.ZUUL_TOKEN_VALUE.getBytes()));
        // 校验 Zuul Token的正确性
        if (StringUtils.equals(zuulToken, token)) {
            return true;
        } else {
            SeedResponse seedResponse = new SeedResponse();
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(seedResponse.message("请通过网关获取资源")));
            return false;
        }
    }
}
