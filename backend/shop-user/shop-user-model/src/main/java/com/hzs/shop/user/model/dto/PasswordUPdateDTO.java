package com.hzs.shop.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author 220419
 * @description
 * @date 2026/9/22
 */
@Data
@Schema(description = "修改密码请求")
public class PasswordUPdateDTO {
    @NotBlank(message = "旧密码不能为空")
    @Schema(description = "旧密码")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    @Size(min = 6, max=20, message = "新密码长度6-20位")
    @Schema(description = "新密码")
    private String newPassword;
}
