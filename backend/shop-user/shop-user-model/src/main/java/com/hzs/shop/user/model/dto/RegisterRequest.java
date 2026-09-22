package com.hzs.shop.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author 220419
 * @description
 * @date 2026/9/21
 */
@Data
@Schema(description = "注册请求")
public class RegisterRequest {
    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 20, message = "用户名长度2-20位")
    @Schema(description = "用户名", example = "zhangsan")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度6-20位")
    @Schema(description = "密码", example = "123456")
    private String password;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "邮箱")
    private String emial;
}
