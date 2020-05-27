package com.speedchina.seed.service;


import com.speedchina.seed.common.entity.SeedConstant;
import com.speedchina.seed.common.exception.ValidateCodeException;
import com.speedchina.seed.common.service.RedisService;
import com.speedchina.seed.properties.SeedAuthProperties;
import com.speedchina.seed.properties.SeedValidateCodeProperties;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码服务类
 * @author suyuan
 * @date 2020/5/27 14:57
 */
@Service
public class ValidateCodeService
{
    @Autowired
    private RedisService redisService;
    @Autowired
    private SeedAuthProperties properties;

    /**
     * 生成验证码
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException
    {
        String key = request.getParameter("key");
        if (StringUtils.isBlank(key)) {
            throw new ValidateCodeException("验证码key不能为空");
        }
        SeedValidateCodeProperties code = properties.getCode();
        setHeader(response, code.getType());

        Captcha captcha = createCaptcha(code);
        redisService.set(SeedConstant.CODE_PREFIX + key, StringUtils.lowerCase(captcha.text()), code.getTime());
        captcha.out(response.getOutputStream());
    }

    /**
     * 校验验证码
     *
     * @param key   前端上送 key
     * @param value 前端上送待校验值
     */
    public void check(String key, String value) throws ValidateCodeException {
        Object codeInRedis = redisService.get(SeedConstant.CODE_PREFIX + key);
        if (StringUtils.isBlank(value)) {
            throw new ValidateCodeException("请输入验证码");
        }
        if (codeInRedis == null) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInRedis))) {
            throw new ValidateCodeException("验证码不正确");
        }
    }

    private Captcha createCaptcha(SeedValidateCodeProperties code) {
        Captcha captcha = null;
        if (StringUtils.equalsIgnoreCase(code.getType(), SeedConstant.GIF)) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    /**
     * 用于设置响应头。在生成验证码图片后我们需要将其返回到客户端，所以需要根据不同的验证码格式设置不同的响应头
     * @author suyuan
     * @date 2020/5/27 15:02
     */
    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, SeedConstant.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
