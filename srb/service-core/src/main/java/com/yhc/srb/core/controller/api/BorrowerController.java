package com.yhc.srb.core.controller.api;


import com.yhc.common.result.R;
import com.yhc.srb.base.util.JwtUtils;
import com.yhc.srb.core.pojo.vo.BorrowerVO;
import com.yhc.srb.core.service.BorrowerService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 借款人 前端控制器
 * </p>
 *
 * @author yhc
 * @since 2022-07-06
 */
@RestController
@Slf4j
@RequestMapping("/api/core/borrower")
public class BorrowerController {

    @Resource
    private BorrowerService borrowerService;


    @ApiOperation("保存借款人信息")
    @PostMapping("/auth/save")
    public R save(
            @RequestBody BorrowerVO borrowerVO,
            HttpServletRequest request
    ){
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        borrowerService.saveBorrowerVOByUserId(borrowerVO, userId);
        return R.ok().message("信息提交成功");
    }

    @ApiOperation("获取借款人认证状态")
    @GetMapping("/auth/getBorrowerStatus")
    public R getBorrowerStatus(HttpServletRequest request){
        String token = request.getHeader("token");
        log.error("==========token====",token);
        Long userId = JwtUtils.getUserId(token);
        log.error("==========userId====",userId);
        Integer status = borrowerService.getStatusByUserId(userId);
        return R.ok().data("borrowerStatus", status);
    }

}

