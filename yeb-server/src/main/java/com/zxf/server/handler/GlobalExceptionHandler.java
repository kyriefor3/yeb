package com.zxf.server.handler;

import com.zxf.server.entity.resp.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕捉 IOException
     */
    @ExceptionHandler(IOException.class)
    public RespBean iOException(IOException e) {
        return RespBean.error("IO异常");
    }

    /**
     * SQLException 数据库异常
     */
    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            return RespBean.error("该数据存在关联数据，操作失败！");
        }
        return RespBean.error("数据库异常，操作失败！");
    }

    /**
     * 其他异常处理
     * ...
     */
}
