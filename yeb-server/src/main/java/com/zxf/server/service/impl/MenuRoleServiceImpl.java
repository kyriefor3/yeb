package com.zxf.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxf.server.entity.MenuRole;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.mapper.MenuRoleMapper;
import com.zxf.server.service.MenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxf
 * @since 2022-03-10
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;
    @Override
    public List<Integer> getMidByRid(Integer rid) {
        QueryWrapper<MenuRole> wrapper = new QueryWrapper<>();
        wrapper.eq("rid",rid);
        List<MenuRole> menuRoles = list(wrapper);
        ArrayList<Integer> integers = new ArrayList<>();
        menuRoles.forEach(menuRole -> integers.add(menuRole.getMid()));
        return integers;
    }

    @Override
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        //先把该角色的所有菜单都删除
        QueryWrapper<MenuRole> wrapper = new QueryWrapper<>();
        wrapper.eq("rid",rid);
        remove(wrapper);
        if (null == mids || 0 == mids.length) { //表示只进行了删除菜单的操作，并且是删除全部
            return RespBean.success("菜单更新成功");
        }

        //修改操作
        // 可以循环进行插入操作 save(MenuRole)/saveBatch(Collection<MenuRole>)
        // 也可以把循环的过程交给数据库⭐
        menuRoleMapper.insertMenuRole(rid,mids);

        return RespBean.success("菜单更新成功");
    }
}
