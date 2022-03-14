package com.zxf.server.mapper;

import com.zxf.server.entity.Department;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    void addDepartment(Department department);

    void delDepartment(Department department);

    List<Department> getAllDepartment(Integer id);

}
