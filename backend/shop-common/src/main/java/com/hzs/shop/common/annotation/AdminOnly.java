package com.hzs.shop.common.annotation;

import java.lang.annotation.*;

/**
 * @author 220419
 * @description
 * @date 2026/9/24
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminOnly {

}
