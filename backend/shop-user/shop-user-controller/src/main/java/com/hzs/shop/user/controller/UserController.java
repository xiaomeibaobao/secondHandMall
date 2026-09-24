package com.hzs.shop.user.controller;

import com.hzs.shop.common.annotation.AdminOnly;
import com.hzs.shop.common.result.PageResult;
import com.hzs.shop.common.result.Result;
import com.hzs.shop.common.service.FileService;
import com.hzs.shop.common.vo.FileUploadVO;
import com.hzs.shop.user.model.dto.*;
import com.hzs.shop.user.model.vo.UserVO;
import com.hzs.shop.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private FileService fileService;
    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        userService.register(registerRequest);
        return Result.success("注册成功");
    }
    @Operation(summary = "管理员注册")
    @PostMapping("/adminregister")
    public Result<String> adminRegister(@Valid @RequestBody RegisterRequest registerRequest) {
        userService.adminRegister(registerRequest);
        return Result.success("管理员注册成功");
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
    @Operation(summary = "修改用户信息")
    @PostMapping("/update")
    public Result<Void> updateUser(@Valid @RequestBody UserUpdateDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        userService.updateUser(userId, dto);
        return Result.success();
    }
    @Operation(summary = "修改密码")
    @PostMapping("/updatepassword")
    public Result<Void> updatePassword(@Valid @RequestBody PasswordUpdateDTO dto, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        userService.updatePassword(userId, dto);
        return Result.success();
    }
    @Operation(summary = "上传头像")
    @PostMapping("/uploadavatar")
    public Result<String> uploadAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        FileUploadVO fileUploadVO = fileService.uploadImage(file, request);
        userService.updateAvatar(userId, fileUploadVO.getUrl());
        return Result.success(fileUploadVO.getUrl());
    }
    @Operation(summary = "用户列表（管理员）")
    @AdminOnly
    @PostMapping("/list")
    public Result<PageResult<UserVO>> getUserList(@RequestBody UserQueryDTO dto) {
        return Result.success(userService.getUserList(dto));
    }
}
