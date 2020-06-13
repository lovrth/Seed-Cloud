package com.speedchina.seed.server.test.service;


import com.speedchina.seed.common.entity.SeedServerConstant;
import com.speedchina.seed.server.test.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

/**
 * feign调用
 * value指定远程服务的名称
 * contextId指定这个Feign Client的别名
 * fallbackFactory指定了回退方法
 */
@FeignClient(value = SeedServerConstant.SEED_SERVER_SYSTEM, contextId = "helloServiceClient", fallbackFactory = HelloServiceFallback.class)
public interface IHelloService {

    @GetMapping("hello")
    String hello(@RequestParam("name") String name);
}
