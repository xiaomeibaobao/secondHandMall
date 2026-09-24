package com.hzs.shop.common.result;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 220419
 * @description
 * @date 2026/9/24
 */
@Data
public class PageResult<T> {
    private List<T> records;
    private Long total;
    private Long pageNum;
    private Long pageSize;
    private Long totalPages;
    public PageResult(List<T> records, Long total, Long pageNum, Long pageSize) {
        this.records = records;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalPages = (total + pageSize - 1) / pageSize;
    }
    /**
     * 从 MyBatis-Plus 的 Page 转换
     */
    public static <T> PageResult<T> of(List<T> records, Long total, Long pageNum, Long pageSize) {
        return new PageResult<T>(records, total, pageNum, pageSize);
    }
    /**
     * 从 MyBatis-Plus Page 转换为 PageResult
     */
    public static <T> PageResult<T> from(Page<T> page) {
        return new PageResult<>(
                page.getRecords(),
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );
    }
    /**
     * 从 MyBatis-Plus Page 转换为 PageResult，并转换元素类型
     */
    public static <E, T> PageResult<T> from(Page<E> page, Function<E, T> converter) {
        List<T> records = page.getRecords().stream()
                .map(converter)
                .collect(Collectors.toList());
        return new PageResult<>(
                records,
                page.getTotal(),
                page.getCurrent(),
                page.getSize()
        );
    }
}
