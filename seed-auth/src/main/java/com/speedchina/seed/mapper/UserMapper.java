package com.speedchina.seed.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.speedchina.seed.common.entity.system.SystemUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<SystemUser>
{
    SystemUser findByName(String username);
}
