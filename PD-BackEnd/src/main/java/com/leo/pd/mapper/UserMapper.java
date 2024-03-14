package com.leo.pd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.pd.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
