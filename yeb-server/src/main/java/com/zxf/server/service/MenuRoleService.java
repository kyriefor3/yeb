package com.zxf.server.service;

import com.zxf.server.entity.MenuRole;
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
public interface MenuRoleService extends IService<MenuRole> {

    List<Integer> getMidByRid(Integer rid);

    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
