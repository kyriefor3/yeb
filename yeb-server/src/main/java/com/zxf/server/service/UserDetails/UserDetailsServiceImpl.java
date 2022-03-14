package com.zxf.server.service.UserDetails;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxf.server.entity.Admin;
import com.zxf.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getAdminWithRolesByUsername(username);
        //需要将admin对象包装成UserDetails对象[UserDetails对象包含用户名，密码，权限信息...]
        //所以这里直接让Admin implements UserDetails 即可
        return admin;
    }
}
