package com.zxf.server.service;

import com.zxf.server.entity.Admin;
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
public interface AdminService extends IService<Admin> {

    Admin getAdminWithRolesByUsername(String username);

    List<Admin> getAllAdminsWithRoles();

    RespBean updateAdmin(Admin admin);

    RespBean delAdmin(Integer id);

    RespBean updateAdminRoles(Integer mid,Integer[] rids);

    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);
}
