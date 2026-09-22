package com.hzs.shop.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 220419
 * @description
 * @date 2026/9/22
 */
@Data
@Schema(description = "修改用户信息请求")
public class UserUpdateDTO {
    @Schema(description = "昵称", example = "张三")
    private String nickname;
    @Schema(description = "邮箱")
    private String email;
}
