package com.hzs.shop.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 220419
 * @description
 * @date 2026/9/28
 */
@Data
public class UserStatusDTO {
    @Schema(description = "用户id")
    private Long id;
    @Schema(description = "状态：1正常 0禁用")
    private Integer status;
}
