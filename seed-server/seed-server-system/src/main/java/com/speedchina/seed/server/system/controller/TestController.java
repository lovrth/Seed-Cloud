package com.speedchina.seed.server.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author MrBird
 */
@RestController
public class TestController {

    @GetMapping("info")
    public String test(){
        return "seed-server-system";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}