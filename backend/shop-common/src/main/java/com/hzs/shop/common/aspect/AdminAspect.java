package com.hzs.shop.common.aspect;

import com.hzs.shop.common.annotation.AdminOnly;
import com.hzs.shop.common.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author 220419
 * @description
 * @date 2026/9/24
 */
@Slf4j
@Aspect
@Component
public class AdminAspect {
    @Before("@annotation(adminOnly)")
    public void checkAdmin(AdminOnly adminOnly) {
        // 获取当前请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes == null) {
            throw new BusinessException(403, "无权限访问");
        }
        HttpServletRequest request = attributes.getRequest();
        String role = (String) request.getAttribute("role");
        if(!"ADMIN".equals(role)) {
            log.warn("非管理员访问管理接口:{}", request.getRequestURI());
            throw new BusinessException(403, "无权限访问");
        }
        log.debug("管理员权限校验通过：{}", request.getRequestURI());
    }
}
