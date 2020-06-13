package com.speedchina.seed.server.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.Principal;

@RestController
public class TestController {

    @GetMapping("info")
    public String test(){
        return "seed-server-mapper.mapper";
    }

    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(@NotEmpty String name) {
        return "hello" + name;
    }
}
