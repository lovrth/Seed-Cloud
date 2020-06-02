package com.speedchina.seed.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.speedchina.seed.common.entity.SeedConstant;
import com.speedchina.seed.common.entity.SeedResponse;
import com.speedchina.seed.common.utils.SeedUtil;
import com.speedchina.seed.gateway.properties.SeedGatewayProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author suyuan
 * @date 2020/5/26 14:58
 */
@Slf4j
@Component
public class SeedGatewayRequestFilter extends ZuulFilter {

    @Autowired
    private SeedGatewayProperties properties;
    private AntPathMatcher pathMatcher = new AntPathMatcher();
    /**
     * PRE过滤器用于将请求路径与配置的路由规则进行匹配，以找到需要转发的目标地址，并做一些前置加工，比如请求的校验等；
     * @author suyuan
     * @date 2020/5/26 15:00
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * PreDecorationFilter用于处理请求上下文，优先级为5，所以我们可以定义一个优先级在PreDecorationFilter之后的过滤器，这样便可以拿到请求上下文。
     * @author suyuan
     * @date 2020/5/26 15:00
     */
    @Override
    public int filterOrder() {
        return 6;
    }

    /**
     * true时表示是否执行该过滤器的run方法，false则表示不执行；
     * @author suyuan
     * @date 2020/5/26 15:03 
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        HttpServletRequest request = ctx.getRequest();
        String host = request.getRemoteHost();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        log.info("请求URI：{}，HTTP Method：{}，请求IP：{}，ServerId：{}", uri, method, host, serviceId);

        // 禁止外部访问资源实现
        boolean shouldForward = true;
        String forbidRequestUri = properties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            HttpServletResponse response = ctx.getResponse();
            SeedResponse seedResponse = new SeedResponse().message("该URI不允许外部访问");
            try {

                SeedUtil.makeResponse(
                        response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                        HttpServletResponse.SC_FORBIDDEN, seedResponse
                );
                ctx.setSendZuulResponse(false);
                ctx.setResponse(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        byte[] token = Base64Utils.encode((SeedConstant.ZUUL_TOKEN_VALUE).getBytes());
        ctx.addZuulRequestHeader(SeedConstant.ZUUL_TOKEN_HEADER, new String(token));
        return null;
    }
}
