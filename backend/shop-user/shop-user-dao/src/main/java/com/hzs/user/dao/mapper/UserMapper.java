package com.hzs.user.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hzs.shop.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 220419
 * @description
 * @date 2026/9/21
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
