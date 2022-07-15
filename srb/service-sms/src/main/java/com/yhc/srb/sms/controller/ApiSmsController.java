package com.yhc.srb.sms.controller;


import com.yhc.common.exception.Assert;
import com.yhc.common.result.R;
import com.yhc.common.result.ResponseEnum;
import com.yhc.srb.sms.client.CoreUserInfoClient;
import com.yhc.srb.sms.service.SmsService;
import com.yhc.srb.sms.utils.RandomUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
//@CrossOrigin
public class ApiSmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CoreUserInfoClient client;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public R send(
            @ApiParam(value = "手机号",required = true)
            @PathVariable String mobile
    ){
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        // 手机是否注册
        boolean res = client.checkMobile(mobile);
        log.info("res",res);
        // 之后验证
        Assert.isTrue(res==false,ResponseEnum.MOBILE_EXIST_ERROR);
        String code = RandomUtil.getFourBitRandom();
        String[] templateParamSet1 = {code};
//        smsService.send(mobile,templateParamSet1);
        // 将验证码存入redis
        redisTemplate.opsForValue().set("srb:core:code"+mobile,code,5, TimeUnit.MINUTES);
        return R.ok().message("短信发送成功！");

    }
}
