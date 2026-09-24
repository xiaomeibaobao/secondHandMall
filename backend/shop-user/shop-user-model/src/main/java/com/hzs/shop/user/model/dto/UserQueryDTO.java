package com.hzs.shop.user.model.dto;

import com.hzs.shop.common.dto.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 220419
 * @description
 * @date 2026/9/24
 */
@Data
@Schema(description = "用户查询请求")
public class UserQueryDTO extends PageQuery {
    @Schema(description = "用户名（模糊搜索）")
    private String username;
    @Schema(description = "角色")
    private String role;
    @Schema(description = "状态：1正常 0禁用")
    private Integer status;
}
