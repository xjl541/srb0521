package com.atguigu.srb.core.controller.admin;



import com.atguigu.srb.common.exception.Assert;
import com.atguigu.srb.common.result.R;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.core.mapper.IntegralGradeMapper;
import com.atguigu.srb.core.pojo.entity.IntegralGrade;
import com.atguigu.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2021-09-22
 */
@Slf4j
@RestController
@RequestMapping("/admin/core/integralGrade")
@Api("后台管理积分等级接口")
@CrossOrigin
public class AdminIntegralGradeController {
    @Autowired
    IntegralGradeService integralGradeService;

    @GetMapping("getById/{id}")
    @ApiOperation("根据id查找积分等级")
    public R getById(
            @PathVariable
            @ApiParam(value = "数据id", required = true, example = "1")Long id
    ){
        IntegralGrade byId = integralGradeService.getById(id);
        return R.ok().data("record",byId);
    }

    @GetMapping("list")
    @ApiOperation("积分等级列表")
    public R list(){

        log.info("info....................");
        log.warn("warn....................");
        log.error("error....................");

        List<IntegralGrade> integralGrades = integralGradeService.list(null);
        return R.ok().data("list",integralGrades);
    }

    @DeleteMapping("remove/{id}")
    @ApiOperation(value = "根据id删除积分等级", notes = "逻辑删除")
    public R remove(@PathVariable
                        @ApiParam(value = "数据id", required = true, example = "100")Long id){
        integralGradeService.removeById(id);
        return R.ok().message("删除成功");
    }

    @ApiOperation("新增积分等级")
    @PostMapping("save")
    public R save(
            @ApiParam(value = "积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade){
        BigDecimal borrowAmount = integralGrade.getBorrowAmount();
        int i = borrowAmount.compareTo(new BigDecimal("0"));// -1 0 1

        // 策略判断，assert
        Assert.isTrue(i == 1, ResponseEnum.BORROW_AMOUNT_0_ERROR);
        integralGradeService.save(integralGrade);
        return R.ok().message("保存成功");
    }

    @ApiOperation("修改积分等级")
    @PostMapping("update")
    public R update(
            @ApiParam(value = "积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade){
        BigDecimal borrowAmount = integralGrade.getBorrowAmount();
        int i = borrowAmount.compareTo(new BigDecimal("0"));// -1 0 1

        // 策略判断，assert
        Assert.isTrue(i == 1, ResponseEnum.BORROW_AMOUNT_0_ERROR);
        integralGradeService.updateById(integralGrade);
        return R.ok().message("保存成功");
    }
}
