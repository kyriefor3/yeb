package com.zxf.server.service;

import com.zxf.server.entity.params.LoginParams;
import com.zxf.server.entity.resp.RespBean;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    RespBean login(LoginParams loginParams, HttpServletRequest request);
}
