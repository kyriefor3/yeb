package com.zxf.server.controller;


import com.zxf.server.entity.Role;
import com.zxf.server.entity.resp.RespBean;
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
@RequestMapping("/system/basic/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation("添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if (!role.getName().startsWith("Role_")) {
            role.setName("Role_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加角色成功！");
        }
        return RespBean.error("添加角色失败！");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return RespBean.success("删除角色成功！");
        }
        return RespBean.error("删除角色失败！");
    }
}
