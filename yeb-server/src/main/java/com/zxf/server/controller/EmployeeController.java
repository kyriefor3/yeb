package com.zxf.server.controller;


import com.zxf.server.entity.*;
import com.zxf.server.entity.params.EmployeePageParam;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.entity.resp.RespPageBean;
import com.zxf.server.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/employee/basic")
public class EmployeeController{

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private NationService nationService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private JoblevelService joblevelService;
    @Autowired
    private PoliticsStatusService politicsStatusService;


    @ApiOperation("分页[+搜索条件]查询员工列表")
    @GetMapping("/")
    public RespPageBean getEmployee(@RequestBody EmployeePageParam pageParam) {
        return employeeService.getEmployeeByPage(pageParam);
    }

    //===============获取所有和employee相关的表的内容==================
    @ApiOperation("获取所有政治面貌")
    @GetMapping("/politicsStatus")
    public List<PoliticsStatus> getAllPoliticsStatuses() {
        return politicsStatusService.list();
    }

    @ApiOperation("获取所有职称")
    @GetMapping("/jobLevels")
    public List<Joblevel> getAllJobLevels() {
        return joblevelService.list();
    }

    @ApiOperation("获取所有民族")
    @GetMapping("/nations")
    public List<Nation> nations() {
        return nationService.list();
    }

    @ApiOperation("获取所有职位")
    @GetMapping("/positions")
    public List<Position> positions() {
        return positionService.list();
    }

    @ApiOperation("获取所有部门")
    @GetMapping("/deps")
    public List<Department> departments() {
        return departmentService.getAllDepartment();
    }

    //===============END==================

    @ApiOperation("添加员工")
    @PostMapping("/")
    public RespBean addEmp(Employee employee) {
        return employeeService.addEmp(employee);
    }


    @ApiOperation("更新员工")
    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee) {
        //应该也要修改contract的内容，这里简化处理
        if (employeeService.updateById(employee)) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }

    @ApiOperation("删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id) {
        if (employeeService.removeById(id)) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation("导出员工信息")
    @GetMapping(value = "/export",produces = "application/octet-stream")
    public void exportEmp(HttpServletResponse response){
        employeeService.exportEmp(response);
    }
}
