package com.hzs.shop.common.exception;

import com.hzs.shop.common.result.Code;
import lombok.Getter;

/**
 * @author 220419
 * @description
 * @date 2026/9/11
 */
@Getter
public class BusinessException extends RuntimeException{
    private final Integer code;

    public BusinessException(Code code) {
        super(code.getMessage());
        this.code = code.getCode();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public BusinessException(String message) {
        super(message);
        this.code = Code.BUSINESS_ERROR.getCode();
    }
}
