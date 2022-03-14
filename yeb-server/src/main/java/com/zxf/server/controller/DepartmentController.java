package com.zxf.server.controller;


import com.zxf.server.entity.Department;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxf
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @ApiOperation("获取所有部门")
    @GetMapping("/")
    public List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @ApiOperation("添加部门")
    @PostMapping("/")
    public RespBean addDepartment(Department department){
        return departmentService.addDepartment(department);
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    public RespBean delDepartment(@PathVariable Integer id){
        return departmentService.delDepartmentById(id);
    }
}
