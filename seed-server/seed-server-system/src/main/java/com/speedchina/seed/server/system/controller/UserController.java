package com.speedchina.seed.server.system.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.speedchina.seed.common.entity.QueryRequest;
import com.speedchina.seed.common.entity.SeedResponse;
import com.speedchina.seed.common.entity.system.SystemUser;
import com.speedchina.seed.common.exception.SeedException;
import com.speedchina.seed.common.utils.SeedUtil;
import com.speedchina.seed.server.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Map;


@Slf4j
@Validated //普通参数的校验 @Validated的作用是让@NotBlank注解生效
@RestController
@RequestMapping("user")
public class UserController
{

    @Autowired
    private IUserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:view')")
    public SeedResponse userList(QueryRequest queryRequest, SystemUser user) {
        Map<String, Object> dataTable = SeedUtil.getDataTable(userService.findUserDetail(user, queryRequest));
        return new SeedResponse().data(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add')") //@Valid对应实体对象传参时的参数校验
    public void addUser(@Valid SystemUser user) throws SeedException
    {
        try {
            this.userService.createUser(user);
        } catch (Exception e) {
            String message = "新增用户失败";
            log.error(message, e);
            throw new SeedException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:update')")
    public void updateUser(@Valid SystemUser user) throws SeedException {
        try {
            this.userService.updateUser(user);
        } catch (Exception e) {
            String message = "修改用户失败";
            log.error(message, e);
            throw new SeedException(message);
        }
    }

    /**
     * {required}提示内容需要在resources路径下创建ValidationMessages.properties配置文件
     * @author suyuan
     * @date 2020/5/28 17:18
     */
    @DeleteMapping("/{userIds}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public void deleteUsers(@NotBlank(message = "{required}") @PathVariable String userIds) throws SeedException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
        } catch (Exception e) {
            String message = "删除用户失败";
            log.error(message, e);
            throw new SeedException(message);
        }
    }
}
