package com.zxf.server.controller;


import com.zxf.server.entity.Admin;
import com.zxf.server.entity.Role;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.service.AdminService;
import com.zxf.server.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxf
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @ApiOperation("获取所有Admin")
    @GetMapping("/")
    public RespBean getAllAdmins(){
        List<Admin> admins = adminService.getAllAdminsWithRoles();
        return RespBean.success(admins);
    }

    @ApiOperation("修改Admin[只是简单修改，不涉及admin的role信息]")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin) {
        return adminService.updateAdmin(admin);
    }


    @ApiOperation("删除Admin[删除操作会影响其他表。这里忽略]")
    @DeleteMapping("/{id}")
    public RespBean delAdmin(@PathVariable Integer id){
        return adminService.delAdmin(id);
    }

    @ApiOperation("获取所有role")
    @GetMapping("/roles")
    public RespBean getAllRoles(){
        List<Role> roles = roleService.list();
        return RespBean.success(roles);
    }

    @ApiOperation("更新admin的role[包括添加和删除]")
    @PutMapping("/role")
    public RespBean updateAdminRoles(Integer mid,Integer[] rids){
        return adminService.updateAdminRoles(mid, rids);
    }
}
