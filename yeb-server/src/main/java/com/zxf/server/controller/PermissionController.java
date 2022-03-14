package com.zxf.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxf.server.entity.Menu;
import com.zxf.server.entity.MenuRole;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.service.MenuRoleService;
import com.zxf.server.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 权限组功能
 * 删除特定角色的权限信息
 * 这是/system/basic/**下的功能
 */
@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuRoleService menuRoleService;

    @ApiOperation("查询所有菜单,包括子菜单")//便于更新处理
    @GetMapping("menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation("根据角色id，获取对应的菜单id")//便于让前端在该角色的菜单项上打勾
    @GetMapping("mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid) {
        return menuRoleService.getMidByRid(rid);
    }

    @ApiOperation("根据角色id，更新该角色的菜单")//包括删除和添加操作【写在一起】
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        return menuRoleService.updateMenuRole(rid, mids);
    }
}
