package com.zxf.server.service;

import com.zxf.server.entity.Department;
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
public interface DepartmentService extends IService<Department> {

    RespBean addDepartment(Department department);

    RespBean delDepartmentById(Integer id);

    List<Department> getAllDepartment();
}
