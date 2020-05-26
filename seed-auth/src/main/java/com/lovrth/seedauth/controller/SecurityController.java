package com.lovrth.seedauth.controller;



import com.speedchina.seed.common.entity.SeedResponse;

import com.speedchina.seed.common.exception.SeedAuthException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@RestController
public class SecurityController
{

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public SeedResponse signout(HttpServletRequest request) throws SeedAuthException
    {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        SeedResponse seedResponse = new SeedResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new SeedAuthException("退出登录失败");
        }
        return seedResponse.message("退出登录成功");
    }
}
