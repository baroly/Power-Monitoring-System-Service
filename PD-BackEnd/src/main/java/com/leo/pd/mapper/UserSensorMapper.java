package com.leo.pd.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.pd.entity.UserIotDev;
import com.leo.pd.entity.UserSensor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 感測器基本資訊 Mapper 接口
 * </p>
 *
 * @author leo
 * @since 2023-11-30
 */
@Mapper
public interface UserSensorMapper extends BaseMapper<UserSensor> {
    List<UserSensor> getSensors(UserIotDev userIotDev);
}
