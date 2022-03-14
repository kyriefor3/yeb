package com.zxf.server.mapper;

import com.zxf.server.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxf
 * @since 2022-03-10
 */
public interface AdminMapper extends BaseMapper<Admin> {

    Admin getAdminWithRolesByUsername(String username);

    List<Admin> getAllAdminsWithRoles(Integer id);

}
