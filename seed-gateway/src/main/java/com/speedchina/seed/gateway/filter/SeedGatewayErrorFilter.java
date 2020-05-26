package com.speedchina.seed.gateway.filter;


import com.netflix.zuul.context.RequestContext;
import com.speedchina.seed.common.entity.SeedResponse;
import com.speedchina.seed.common.utils.SeedUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * 自定义Zuul异常处理
 * 处理转发请求超时和服务不可用
 * @author suyuan
 * @date 2020/5/26 11:14
 */
@Slf4j
@Component
public class SeedGatewayErrorFilter extends SendErrorFilter {

    @Override
    public Object run() {
        try {
            SeedResponse seedResponse = new SeedResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY); //当前请求的服务名称

            ExceptionHolder exception = findZuulException(ctx.getThrowable()); //当前请求的异常对象
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            seedResponse = resolveExceptionMessage(message, serviceId, seedResponse);

            HttpServletResponse response = ctx.getResponse();
            SeedUtil.makeResponse(
                    response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, seedResponse
            );
            log.error("Zull sendError：{}", seedResponse.getMessage());
        } catch (Exception ex) {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private SeedResponse resolveExceptionMessage(String message, String serviceId, SeedResponse seedResponse) {
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return seedResponse.message("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return seedResponse.message(serviceId + "服务不可用");
        }
        return seedResponse.message("Zuul请求" + serviceId + "服务异常");
    }
}
