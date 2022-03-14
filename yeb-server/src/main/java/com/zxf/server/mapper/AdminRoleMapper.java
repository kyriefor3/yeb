package com.zxf.server.mapper;

import com.zxf.server.entity.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxf
 * @since 2022-03-10
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    Integer insertAdminRoles(Integer mid, Integer[] rids);
}
