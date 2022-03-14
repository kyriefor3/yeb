package com.zxf.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zxf.server.entity.Menu;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.mapper.MenuMapper;
import com.zxf.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public RespBean getMenusByAdminId(Integer id) {
        List<Menu> list = menuMapper.getMenusByAdminId(id);
        return RespBean.success(list);
    }

    /**
     * 获取所有menus及其对应的roles
     * @return
     */
    @Override
    public List<Menu> getMenusWithRoles() {
        return menuMapper.getMenusWithRoles();
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }
}
