package com.leo.pd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leo.pd.entity.User;
import com.leo.pd.mapper.UserMapper;
import com.leo.pd.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServicImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
