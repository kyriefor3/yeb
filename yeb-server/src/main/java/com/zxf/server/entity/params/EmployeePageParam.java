package com.zxf.server.entity.params;

import com.zxf.server.entity.Employee;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * 封装前端的 条件查询 Employee分页数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "员工资料首页请求")
public class EmployeePageParam implements Serializable {

    @ApiModelProperty(value = "当前页", required = true)
    private Integer currentPage = 1;//默认为1

    @ApiModelProperty(value = "页面数据条数", required = true)
    private Integer size = 10;//默认为10

    @ApiModelProperty(value = "员工信息[搜索条件]")
    private Employee employee;

    @ApiModelProperty(value = "入职时间范围[搜索条件]")
    private Date[] beginDateScope;



}
