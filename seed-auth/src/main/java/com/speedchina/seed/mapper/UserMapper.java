package com.speedchina.seed.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.speedchina.seed.common.entity.system.SystemUser;


public interface UserMapper extends BaseMapper<SystemUser>
{
    SystemUser findByName(String username);
}
