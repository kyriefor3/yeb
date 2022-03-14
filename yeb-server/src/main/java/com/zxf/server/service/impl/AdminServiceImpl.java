package com.zxf.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxf.server.entity.Admin;
import com.zxf.server.entity.AdminRole;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.mapper.AdminMapper;
import com.zxf.server.mapper.AdminRoleMapper;
import com.zxf.server.service.AdminRoleService;
import com.zxf.server.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin getAdminWithRolesByUsername(String username) {
        return adminMapper.getAdminWithRolesByUsername(username);
    }

    @Override
    public List<Admin> getAllAdminsWithRoles() {
        Admin admin = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return adminMapper.getAllAdminsWithRoles(admin.getId());
    }

    @Override
    public RespBean updateAdmin(Admin admin) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("id",admin.getId());
        if(this.update(admin,wrapper)){
            return RespBean.success("操作员修改成功");
        }
        return RespBean.error("操作员修改失败");

    }

    @Override
    public RespBean delAdmin(Integer id) {
        if (this.removeById(id)) {
            return RespBean.success("操作员删除成功");
        }
        return RespBean.error("操作员删除失败");
    }

    @Override
    public RespBean updateAdminRoles(Integer mid, Integer[] rids) {
        //删除admin下全部role
        QueryWrapper<AdminRole> wrapper = new QueryWrapper<>();
        wrapper.eq("mid",mid);
        adminRoleMapper.delete(wrapper);

        //再进行添加
        if(rids==null||rids.length==0){
            return RespBean.success("操作员角色更新成功");
        }

        Integer integer = adminRoleMapper.insertAdminRoles(mid, rids);
        if(integer!=0){
            return RespBean.success("操作员角色更新成功");
        }
        return RespBean.error("操作员角色更新失败");
    }

    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        //判断旧密码是否正确
        if (passwordEncoder.matches(oldPass, admin.getPassword())) {
            admin.setPassword(passwordEncoder.encode(pass));
            if (1 == adminMapper.updateById(admin)) {
                return RespBean.success("更新密码成功！");
            }
        }
        return RespBean.error("密码输入错误，请重新输入！");
    }

}
