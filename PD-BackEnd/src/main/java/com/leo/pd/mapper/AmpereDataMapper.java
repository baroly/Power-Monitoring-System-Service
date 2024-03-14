package com.leo.pd.mapper;

import com.leo.pd.entity.AmpereData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leo.pd.entity.UserSensor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 霍爾感測器感測數據 Mapper 接口
 * </p>
 *
 * @author leo
 * @since 2023-11-30
 */
@Mapper
public interface AmpereDataMapper extends BaseMapper<AmpereData> {
    List<AmpereData> getMonthSenseData(AmpereData ampereData);
}
