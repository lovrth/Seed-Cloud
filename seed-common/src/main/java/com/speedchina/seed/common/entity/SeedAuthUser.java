package com.speedchina.seed.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义的用户实体类
 * @author suyuan
 * @date 2020/5/25 16:02
 */
@Data
public class SeedAuthUser implements Serializable {
    private static final long serialVersionUID = -1748289340320186418L;

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
