package com.zxf.server.controller;


import com.zxf.server.entity.Admin;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxf
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("根据用户id获取菜单列表")
    @GetMapping("menu")
    public RespBean getMenusByAdminId() {
        Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = admin.getId();
        return menuService.getMenusByAdminId(id);
    }



}
