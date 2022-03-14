package com.zxf.server.service.impl;

import com.zxf.server.entity.Oplog;
import com.zxf.server.mapper.OplogMapper;
import com.zxf.server.service.OplogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zxf
 * @since 2022-03-10
 */
@Service
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements OplogService {

}
