package com.atguigu.srb.common.exception;


import com.atguigu.srb.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Component
@RestControllerAdvice //在controller层添加通知。如果使用@ControllerAdvice，则方法上需要添加@ResponseBody
public class UnifiedExceptionHandler {
    @ExceptionHandler(value = Exception.class) //当controller中抛出Exception，则捕获
    public R handleException(Exception e) {
        log.error(e.getMessage(), e);
        return R.error();
    }
}
