package com.zxf.server.mapper;

import com.zxf.server.entity.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zxf
 * @since 2022-03-10
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    Integer insertMenuRole(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
