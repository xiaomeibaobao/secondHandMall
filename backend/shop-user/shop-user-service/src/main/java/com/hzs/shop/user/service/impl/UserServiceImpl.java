package com.hzs.shop.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzs.shop.common.exception.BusinessException;
import com.hzs.shop.common.result.Code;
import com.hzs.shop.common.result.PageResult;
import com.hzs.shop.common.utils.JwtUtil;
import com.hzs.shop.common.utils.PasswordUtil;
import com.hzs.shop.user.model.dto.*;
import com.hzs.shop.user.model.entity.User;
import com.hzs.shop.user.model.vo.UserVO;
import com.hzs.shop.user.service.UserService;
import com.hzs.user.dao.mapper.UserMapper;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author 220419
 * @description
 * @date 2026/9/21
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        Long count = userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, request.getUsername()));
        if (count > 0) {
            throw new BusinessException(400, "用户名已存在");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(PasswordUtil.encode(request.getPassword()));
        user.setNickname(request.getNickname());
        user.setEmail(request.getEmial());
        user.setRole("USER");
        user.setStatus(1);

        userMapper.insert(user);
        log.info("用户注册成功：{}", request.getUsername());
    }

    @Override
    @Transactional
    public void adminRegister(RegisterRequest request) {
        Long count = baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, request.getUsername()));
        if (count > 0) {
            throw new BusinessException(400, "用户名已存在");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(PasswordUtil.encode(request.getPassword()));
        user.setNickname(request.getNickname());
        user.setEmail(request.getEmial());
        user.setRole("ADMIN");
        user.setStatus(1);
        baseMapper.insert(user);
        log.info("管理员注册成功：{}", request.getUsername());
    }

    @Override
    public String login(LoginRequest request) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, request.getUsername()));
        if (user == null) {
            throw new BusinessException(401, "用户不存在");
        }
        if(!PasswordUtil.matches(request.getPassword(), user.getPassword())){
            throw new BusinessException(401, "密码错误");
        }
        if(user.getStatus() == 0) {
            throw new BusinessException(403, "账号已被禁用");
        }
        String token = jwtUtil.generateToken(user.getId(), user.getUsername(), user.getRole());
        log.info("用户登录成功：{}", request.getUsername());
        return token;
    }

    @Override
    public UserVO getUserById(Long id) {
        User user = userMapper.selectById(id);
        if(user == null) {
            throw new BusinessException(404, "用户不存在");
        }
        return getUserVO(user);
    }

    @Override
    public UserVO getCurrentUser(Long userId) {
        if(userId == null) {
            throw new BusinessException(Code.UNAUTHORIZED);
        }
        return getUserById(userId);
    }

    @Override
    @Transactional
    public void updateUser(Long userId, UserUpdateDTO userUpdateDTO) {
        User user = userMapper.selectById(userId);
        if(user == null) {
            throw new BusinessException(404, "用户不存在");
        }
        if(userUpdateDTO.getNickname() != null) {
            user.setNickname(userUpdateDTO.getNickname());
        }
        if(userUpdateDTO.getEmail() != null) {
            user.setEmail(userUpdateDTO.getEmail());
        }
        userMapper.updateById(user);
        log.info("用户信息更新成功：{}", userId);
    }

    @Override
    @Transactional
    public void updatePassword(Long userId, PasswordUpdateDTO passwordUpdateDTO) {
        User user = userMapper.selectById(userId);
        if(user == null) {
            throw new BusinessException(404, "用户不存在");
        }
        if(!PasswordUtil.matches(passwordUpdateDTO.getOldPassword(), user.getPassword())) {
            throw new BusinessException(400, "旧密码错误");
        }
        user.setPassword(PasswordUtil.encode(passwordUpdateDTO.getNewPassword()));
        userMapper.updateById(user);
        log.info("密码修改成功：{}", userId);
    }

    @Override
    @Transactional
    public void updateAvatar(Long userId, String avatarUrl) {
        User user = baseMapper.selectById(userId);
        if(user == null) {
            throw new BusinessException(404, "用户不存在");
        }
        user.setAvatar(avatarUrl);
        userMapper.updateById(user);
        log.info("头像更新成功：{}", userId);
    }

    @Override
    public PageResult<UserVO> getUserList(UserQueryDTO dto) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if(StringUtils.hasText(dto.getUsername())) {
            wrapper.like(User::getUsername, dto.getUsername());
        }
        if(StringUtils.hasText(dto.getRole())) {
            wrapper.eq(User::getRole, dto.getRole());
        }
        if(dto.getStatus() != null) {
            wrapper.eq(User::getStatus, dto.getStatus());
        }
        wrapper.orderByDesc(User::getCreateTime);
        Page<User> page = page(new Page<>(dto.getPageNum(), dto.getPageSize()), wrapper);
        return PageResult.from(page, this::convertToVo);
    }

    @Override
    public void updateUserStatus(Long userId, Integer status) {

    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void updateUserRole(Long userId, String role) {

    }

    private UserVO getUserVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
    private UserVO convertToVo(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}
