package com.speedchina.seed.common.handler;


import com.speedchina.seed.common.entity.SeedResponse;
import com.speedchina.seed.common.utils.SeedUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 令牌不正确401
 * @author suyuan
 * @date 2020/5/26 9:43
 */
public class SeedAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        SeedResponse febsResponse = new SeedResponse();
        SeedUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED, febsResponse.message("token无效")
        );
    }
}
