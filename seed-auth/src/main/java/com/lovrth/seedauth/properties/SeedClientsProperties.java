package com.lovrth.seedauth.properties;

import lombok.Data;

/**
 * Client可配置的方式
 * @author suyuan
 * @date 2020/5/26 8:54
 */
@Data
public class SeedClientsProperties
{

    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}
