package com.zxf.server.controller;


import com.zxf.server.entity.Position;
import com.zxf.server.entity.resp.RespBean;
import com.zxf.server.service.PositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zxf
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @ApiOperation("获取所有position")
    @GetMapping("/")
    public RespBean getAllPosition(){
        List<Position> list = positionService.list();
        return RespBean.success(list);
    }

    @ApiOperation("增加position")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        return (positionService.save(position))? RespBean.success("添加成功"):RespBean.error("添加失败");
    }

    @ApiOperation("更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position) {
        if (positionService.updateById(position)) {
            return RespBean.success("更新职位成功！");
        }
        return RespBean.error("更新职位失败！");
    }

    @ApiOperation("删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id) {
        if (positionService.removeById(id)) {
            return RespBean.success("删除职位成功！");
        }
        return RespBean.error("删除职位失败！");
    }

    @ApiOperation("批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deletePositionsByIds(Integer[] ids) {
        if (positionService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("批量删除职位成功！");
        }
        return RespBean.error("批量删除职位失败！");
    }
}
