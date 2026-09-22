package com.hzs.shop.user.controller;

import com.hzs.shop.common.result.Result;
import com.hzs.shop.user.model.dto.LoginRequest;
import com.hzs.shop.user.model.dto.RegisterRequest;
import com.hzs.shop.user.model.vo.UserVO;
import com.hzs.shop.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 220419
 * @description
 * @date 2026/9/22
 */
@Slf4j
@Tag(name = "用户管理")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return Result.success("注册成功");
    }
    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result<String> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest);
        return Result.success(token);
    }
    @Operation(summary = "获取当前用户信息")
    @PostMapping("/current")
    public Result<UserVO> getCurrentUser(HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        return Result.success(userService.getCurrentUser(userId));
    }

}
