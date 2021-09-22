package com.atguigu.srb.core.controller.admin;


import com.atguigu.srb.common.result.R;
import com.atguigu.srb.core.mapper.IntegralGradeMapper;
import com.atguigu.srb.core.pojo.entity.IntegralGrade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2021-09-22
 */
@RestController
@RequestMapping("/admin/core/integralGrade")
@Api("后台管理积分等级接口")
public class AdminIntegralGradeController {
    @Autowired
    IntegralGradeMapper integralGradeMapper;

    @GetMapping("list")
    @ApiOperation("积分等级列表")
    public R list(){
        List<IntegralGrade> integralGrades = integralGradeMapper.selectList(null);
        return R.ok().data("list",integralGrades);
    }

    @DeleteMapping("remove/{id}")
    @ApiOperation(value = "根据id删除积分等级", notes = "逻辑删除")
    public R remove(@PathVariable
                        @ApiParam(value = "数据id", required = true, example = "100")Long id){
        integralGradeMapper.deleteById(id);
        return R.ok().message("删除成功");
    }
}
