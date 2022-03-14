package com.zxf.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zxf.server.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenusByAdminId(Integer id);

    List<Menu> getMenusWithRoles();

    List<Menu> getAllMenus();

}
