package com.zxf.server.service;

import com.zxf.server.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxf.server.entity.resp.RespBean;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxf
 * @since 2022-03-10
 */
public interface MenuService extends IService<Menu> {

    RespBean getMenusByAdminId(Integer id);

    /**
     * 获取所有menus及其对应的roles
     * @return
     */
    List<Menu> getMenusWithRoles();

    List<Menu> getAllMenus();

}
