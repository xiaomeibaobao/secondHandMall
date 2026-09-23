package com.hzs.shop.user.service;

import com.hzs.shop.user.model.dto.LoginRequest;
import com.hzs.shop.user.model.dto.PasswordUpdateDTO;
import com.hzs.shop.user.model.dto.RegisterRequest;
import com.hzs.shop.user.model.dto.UserUpdateDTO;
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
}
