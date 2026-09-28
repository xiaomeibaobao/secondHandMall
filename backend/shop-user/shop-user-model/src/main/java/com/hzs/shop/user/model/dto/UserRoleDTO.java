package com.hzs.shop.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 220419
 * @description
 * @date 2026/9/28
 */
@Data
public class UserRoleDTO {
    @Schema(description = "用户id")
    private Long id;
    @Schema(description = "角色：USER/ADMIN")
    private String role;
}
