package com.leo.pd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.pd.entity.UserServer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserServerMapper extends BaseMapper<UserServer> {
    int getCountByUid(@Param("uid") String uid);
}
