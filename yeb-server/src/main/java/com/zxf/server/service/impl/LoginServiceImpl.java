package com.zxf.server.service.impl;

import com.zxf.server.entity.params.LoginParams;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.service.LoginService;
import com.zxf.server.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Override
    public RespBean login(LoginParams loginParams, HttpServletRequest request) {
        //检查验证码
        String code = loginParams.getCode();
        String captcha = (String) request.getSession().getAttribute("captcha");
        if(StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码错误!");
        }

        String username = loginParams.getUsername();
        String password = loginParams.getPassword();

        //从数据库中查出对应的用户
        //TIP:这里直接借用Spring-Security中UserDetailsService,重写里面的方法
        // 这个类本质上就是在完成这样的功能

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails == null){
            return RespBean.error("当前用户不存在");
        }
        //还需要比较密码，因为前端输入的密码是经过加密才存入数据库的
        // 这里借用Spring-Security中PasswordEncoder帮我们完成
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("密码错误，请重新输入！");
        }

        //用户存在,生成token
        String token = jwtTokenUtil.generateToken(userDetails);

        //更新security登录用户对象
        //放在SpringSecurity的全局里面
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //返回前端数据，包装成map
        Map<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("tokenHead",tokenHead);

        return RespBean.success(map);
    }
}
