package com.hzs.shop.common.exception;

import com.hzs.shop.common.result.Code;
import com.hzs.shop.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 220419
 * @description
 * @date 2026/9/11
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        log.warn("业务异常：" + e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(FieldError::getDefaultMessage)
                .orElse("参数错误");
        log.warn("参数校验错误：{}", message);
        return Result.error(Code.PARAM_ERROR.getCode(), message);
    }
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.warn("系统异常" + e.getMessage());
        return Result.error(Code.ERROR);
    }
}
