package com.zxf.server.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zxf.server.entity.Employee;
import com.zxf.server.entity.params.EmployeePageParam;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.entity.resp.RespPageBean;
import com.zxf.server.mapper.EmployeeMapper;
import com.zxf.server.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public RespPageBean getEmployeeByPage(EmployeePageParam pageParam) {
        Page<Employee> page = new Page<>(pageParam.getCurrentPage(),pageParam.getSize());
        IPage<Employee> iPage = employeeMapper.getEmployeeByPage(page,pageParam.getEmployee(),pageParam.getBeginDateScope());
        return new RespPageBean(iPage.getTotal(),iPage.getRecords());
    }

    @Override
    public RespBean addEmp(Employee employee) {
        //员工表的contract需要计算得到
        //处理合同期限，保留2位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);//以 天为单位 计算两个时间的差值
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days / 365.00)));//以年为单位，保留两位小数

        if(this.save(employee)){
            return RespBean.success("员工添加成功");
        }
        return RespBean.error("员工添加失败");
    }

    @Override
    public void exportEmp(HttpServletResponse response) {
        //获取所有员工信息
        List<Employee> employees = this.getEmployee(null);

        //导出
        ExportParams params = new ExportParams("员工表", "yeb员工表", ExcelType.HSSF);
        Workbook workbook = ExcelExportUtil.exportExcel(params, Employee.class, employees);

        //流的方式
        ServletOutputStream outputStream = null;
        try {
            //流形式
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition", "attachment;filename="
                                    + URLEncoder.encode("员工表.xls", "UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 获取某些id的员工
     * @param ids
     * @return
     */
    @Override
    public List<Employee> getEmployee(Integer[] ids) {
        return employeeMapper.getEmployee(ids);
    }
}
