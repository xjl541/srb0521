package com.atguigu.srb.sms.controller;

import com.atguigu.srb.common.exception.Assert;
import com.atguigu.srb.common.result.R;
import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.common.utils.RegexValidateUtils;
import com.atguigu.srb.sms.service.SmsService;
import com.google.j2objc.annotations.AutoreleasePool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/sms")
@Api("短信管理")
public class ApiSmsController {
    @Autowired
    SmsService smsService;

    @ApiOperation("发送验证码")
    @GetMapping("send/{mobile}")
    public R send (
            @ApiParam("手机号码")
            @PathVariable("mobile") String mobile
    ){
        boolean b = RegexValidateUtils.checkCellphone(mobile);
        Assert.isTrue(b, ResponseEnum.MOBILE_ERROR);

        smsService.send(mobile);
        return R.ok().message("手机验证码发送成功");
    }
}
