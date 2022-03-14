package com.zxf.server;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxf.server.entity.Admin;
import com.zxf.server.entity.Department;
import com.zxf.server.entity.Employee;
import com.zxf.server.entity.Menu;
import com.zxf.server.entity.params.EmployeePageParam;
import com.zxf.server.entity.resp.RespPageBean;
import com.zxf.server.mapper.EmployeeMapper;
import com.zxf.server.service.AdminService;
import com.zxf.server.service.DepartmentService;
import com.zxf.server.service.EmployeeService;
import com.zxf.server.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.AntPathMatcher;

import java.util.List;

@SpringBootTest
class YebServerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private MenuService menuService;
    @Test
    void testMenuService(){
        List<Menu> menusWithRoles = menuService.getMenusWithRoles();
        for (Menu menu:
             menusWithRoles) {
            System.out.println(menu);
        }
    }

    @Autowired
    private AdminService adminService;
    @Test
    void testAdminService(){
        Admin leisu = adminService.getAdminWithRolesByUsername("leisu");
        System.out.println(leisu);
    }

    @Test
    void testAntMatcher(){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/system/**", "/system/pos");
        boolean match2 = antPathMatcher.match("/system/**", "/system/pos/pos");
        System.out.println(match + "," +match2);

    }

    @Autowired
    private DepartmentService departmentService;
    @Test
    void testDepartmentService(){
        List<Department> allDepartment = departmentService.getAllDepartment();
        allDepartment.forEach(department -> System.out.println(department));
    }

    @Autowired
    private EmployeeMapper employeeMapper;
    @Test
    void testEmpMapper1(){
        EmployeePageParam employeePageParam = new EmployeePageParam();
        /*employeePageParam.setCurrentPage(1);
        employeePageParam.setSize(5);*/
        Page<Employee> page = new Page<>(1, 5);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page,null,null);

        List<Employee> records = employeeByPage.getRecords();
        System.out.println(records);
        System.out.println(employeeByPage.getSize());
        System.out.println(employeeByPage.getTotal());
    }

    @Test
    void testEmpMapper2(){
        List<Employee> employee = employeeMapper.getEmployee(new Integer[]{1,2,3});
        System.out.println(employee);
    }

}
