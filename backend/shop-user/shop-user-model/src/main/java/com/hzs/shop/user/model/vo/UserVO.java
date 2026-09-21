package com.hzs.shop.user.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 220419
 * @description
 * @date 2026/9/21
 */
@Data
@Schema(description = "用户信息")
public class UserVO {
    @Schema(description = "用户ID")
    private Long id;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "昵称")
    private String nickname;
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "头像URL")
    private String avatar;
    @Schema(description = "角色,USER(普通用户)/ADMIN（管理员）")
    private String role;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
