package com.hzs.shop.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 220419
 * @description
 * @date 2026/9/24
 */
@Data
@Schema(description = "分页查询参数")
public class PageQuery {
    @Schema(description = "页码")
    private Integer pageNum;
    @Schema(description = "每页条数")
    private Integer pageSize;
    public Integer getOffset() {
        return (pageNum - 1) * pageSize;
    }
}
