package com.yhc.srb.core.controller.admin;

import com.yhc.common.exception.Assert;
import com.yhc.common.result.R;
import com.yhc.common.result.ResponseEnum;
import com.yhc.srb.core.pojo.entity.IntegralGrade;
import com.yhc.srb.core.service.IntegralGradeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

//@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {
    @Resource
    private IntegralGradeService integralGradeService;


    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public R listAll(){
        log.info("-----------");
        log.warn("===============");
        log.error("?????????????");

        List<IntegralGrade> list = integralGradeService.list();
        return R.ok().data("list",list);
    }

    @ApiOperation(value = "根据id删除积分等级",notes = "逻辑删除")
    @DeleteMapping("/remove/{id}")
    public R removeById(
            @ApiParam(value = "数据id",required = true,example = "1")
            @PathVariable Long id
    ){
        boolean b = integralGradeService.removeById(id);
        if(b)
            return R.ok().message("删除成功");
        else
            return R.error().message("删除失败");
    }

    // 新增数据
    @ApiOperation(value = "新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "积分登记对象",required = true)
            @RequestBody IntegralGrade integralGrade
    ){

        Assert.notNull(integralGrade.getBorrowAmount(), ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        boolean res = integralGradeService.save(integralGrade);
        if(res)
            return R.ok().message("保存成功");
        else
            return R.ok().message("保存失败");
    }

    // 根据id查询
    @ApiOperation("根据id获取积分等级")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id",required = true,example = "1")
            @PathVariable Long id
    ){
        IntegralGrade byId = integralGradeService.getById(id);
        if(byId!=null)
            return R.ok().data("record",byId);
        else
            return R.ok().message("数据不存在");
    }

    // 根据id修改
    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade
    ){
        boolean b = integralGradeService.updateById(integralGrade);
        if(b)
            return R.ok().message("修改成功");
        else
            return R.ok().message("修改失败");
    }



}
