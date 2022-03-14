package com.zxf.server.service;

import com.zxf.server.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zxf.server.entity.params.EmployeePageParam;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.entity.resp.RespPageBean;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxf
 * @since 2022-03-10
 */
public interface EmployeeService extends IService<Employee> {

    RespPageBean getEmployeeByPage(EmployeePageParam pageParam);

    RespBean addEmp(Employee employee);

    void exportEmp(HttpServletResponse response);

    List<Employee> getEmployee(Integer[] ids);
}
