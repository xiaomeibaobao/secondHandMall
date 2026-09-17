package com.hzs.shop.common.result;

import lombok.Getter;

/**
 * @author 220419
 * @description
 * @date 2026/9/11
 */
@Getter
public enum Code {
    SUCCESS(200, "success"),
    ERROR(500, "系统错误"),
    PARAM_ERROR(400, "参数错误"),
    UNAUTHORIZED(401, "未登录"),
    FORBIDDEN(403, "无权限"),
    NOT_FOUND(404, "资源不存在"),
    BUSINESS_ERROR(500, "业务异常");

    private final Integer code;
    private final String message;
    Code(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
