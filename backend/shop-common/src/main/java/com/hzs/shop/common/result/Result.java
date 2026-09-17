package com.hzs.shop.common.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 220419
 * @description
 * @date 2026/9/11
 */
@Data
@Schema(description = "统一响应结果")
public class Result<T> {
    @Schema(description = "状态码", example = "200")
    private Integer code;
    @Schema(description = "提示信息", example = "success")
    private String message;
    @Schema(description = "响应数据")
    private T data;
    public Result() {}
    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static <T> Result<T> success(T data) {
        return new Result<>(Code.SUCCESS.getCode(), "success", data);
    }
    public static <T> Result<T> success() {
        return new Result<>(Code.SUCCESS.getCode(), "success", null);
    }
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(Code.SUCCESS.getCode(), message, data);
    }
    public static <T> Result<T> error(Code code) {
        return new Result<>(code.getCode(), code.getMessage(), null);
    }
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }
    public static <T> Result<T> error(String message) {
        return new Result<>(Code.ERROR.getCode(), message, null);
    }
}
