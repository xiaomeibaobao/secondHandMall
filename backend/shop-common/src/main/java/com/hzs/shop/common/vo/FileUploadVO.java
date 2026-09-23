package com.hzs.shop.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 220419
 * @description
 * @date 2026/9/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "文件上传响应")
public class FileUploadVO {
    @Schema(description = "文件访问URL")
    private String url;
    @Schema(description = "原始文件名")
    private String originalName;
    @Schema(description = "新文件名")
    private String newName;
    @Schema(description = "文件大小")
    private Long size;
}
