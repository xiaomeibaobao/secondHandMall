package com.hzs.shop.user.service;

import com.hzs.shop.common.result.PageResult;
import com.hzs.shop.user.model.dto.*;
import com.hzs.shop.user.model.vo.UserVO;

/**
 * @author 220419
 * @description
 * @date 2026/9/21
 */
public interface UserService {
    /* 用户注册 */
    void register(RegisterRequest request);

    /**
     * 管理员注册
     * @param request
     */
    void adminRegister(RegisterRequest request);
    /**
     * 用户登录
     */
    String login(LoginRequest request);
    /*
    * 根据ID获取用户信息
    * */
    UserVO getUserById(Long id);
    /**
     * 获取当前登录用户信息
     */
    UserVO getCurrentUser(Long userId);

    /**
     * 修改用户信息
     * @param userId
     * @param userUpdateDTO
     */
    void updateUser(Long userId, UserUpdateDTO userUpdateDTO);

    /**
     * 修改密码
     * @param userId
     * @param passwordUpdateDTO
     */
    void updatePassword(Long userId, PasswordUpdateDTO passwordUpdateDTO);

    /**
     * 更新头像
     * @param userId
     * @param avatarUrl
     */
    void updateAvatar(Long userId, String avatarUrl);

    /**
     * 分页查询用户列表（管理员）
     * @param dto
     * @return
     */
    PageResult<UserVO> getUserList(UserQueryDTO dto);

    /**
     * 修改用户状态（管理员）
     * @param userId
     * @param status
     */
    void updateUserStatus(Long userId, Integer status);

    /**
     * 删除用户（管理员）
     * @param userId
     */
    void deleteUser(Long userId);

    /**
     * 修改用户角色
     * @param userId
     * @param role
     */
    void updateUserRole(Long userId, String role);
}
