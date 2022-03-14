package com.zxf.server.entity.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {
    private Integer code;
    private String message;
    private Object object;

    public static RespBean success(Object object){
        return new RespBean(200,"操作成功",object);
    }
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }
}
