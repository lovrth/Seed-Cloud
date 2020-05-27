package com.speedchina.seed.service;


import com.speedchina.seed.common.entity.SeedAuthUser;
import com.speedchina.seed.common.entity.system.SystemUser;
import com.speedchina.seed.manager.UserManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 校验用户名密码的类
 * @author suyuan
 * @date 2020/5/25 16:05
 */
@Service
public class SeedUserDetailService implements UserDetailsService
{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SystemUser systemUser = userManager.findByName(username);
        if(systemUser!=null){
            String permissions = userManager.findUserPermissions(systemUser.getUsername());
            boolean notLocked = false;
            if (StringUtils.equals(SystemUser.STATUS_VALID, systemUser.getStatus()))
                notLocked = true;
            SeedAuthUser authUser=new SeedAuthUser(systemUser.getUsername(),systemUser.getPassword(),true,true,true,notLocked,AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
            BeanUtils.copyProperties(systemUser,authUser);
            return authUser;
        } else {
            throw new UsernameNotFoundException("");
        }
    }


    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SeedAuthUser user = new SeedAuthUser();
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode("123456"));

        return new User(username, user.getPassword(), user.isEnabled(),
                user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                user.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
    }*/
}
