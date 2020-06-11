package com.speedchina.seed.properties;

import lombok.Data;

/**
 * Client可配置的方式
 */
@Data
public class SeedClientsProperties {

    private String client;
    private String secret;
    private String grantType = "password,authorization_code,refresh_token";
    private String scope = "all";
}
