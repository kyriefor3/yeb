package com.zxf.server.controller;

import com.zxf.server.entity.Admin;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminInfoController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("更新当前已登陆用户信息")
    @PostMapping("admin/info")
    public RespBean updateAdmin(@RequestBody Admin admin, Authentication authentication){

        if (adminService.updateById(admin)) {
            //更新成功，需要重置Authentication对象
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(admin, null, authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");

    }

    @ApiOperation("更新用户密码")
    @PutMapping("/pass")
    public RespBean updateAdminPassword(@RequestBody Map<String, Object> info) {
        String oldPass = (String) info.get("oldPass");
        String pass = (String) info.get("pass");
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updateAdminPassword(oldPass, pass, adminId);
    }
}
