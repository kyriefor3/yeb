package com.zxf.server.service.impl;

import com.zxf.server.entity.Department;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.mapper.DepartmentMapper;
import com.zxf.server.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public RespBean addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);

        if(department.getResult()==1){
            return RespBean.success(department);
        }
        return RespBean.error("部门插入失败");
    }

    @Override
    public RespBean delDepartmentById(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.delDepartment(department);

        Integer result = department.getResult();

        switch (result){
            case -2 : return RespBean.error("该部门下有子部门，删除失败");//直接return，所以不需要写break
            case -1 : return RespBean.error("该部门下有员工，删除失败");
            case 1: return RespBean.success("删除部门成功");
        }

        return RespBean.success("删除部门失败");

    }

    /**
     * 获取所有部门信息，包括子部门
     *
     */
    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment(-1);
    }
}
