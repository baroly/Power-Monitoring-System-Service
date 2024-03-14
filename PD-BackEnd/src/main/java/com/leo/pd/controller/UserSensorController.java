package com.leo.pd.controller;


import com.leo.pd.comment.ReqResult;
import com.leo.pd.entity.UserIotDev;
import com.leo.pd.entity.UserSensor;
import com.leo.pd.mapper.UserSensorMapper;
import com.leo.pd.service.IUserSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

/**
 * <p>
 * 感測器基本資訊 前端控制器
 * </p>
 *
 * @author leo
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/userSensor")
public class UserSensorController {
    @Autowired
    IUserSensorService userSensorService;

    @Autowired
    UserSensorMapper userSensorMapper;

    @GetMapping("getSensors")
    public ReqResult getServers(String id){
        LocalDate date = LocalDate.now();//.with(TemporalAdjusters.lastDayOfMonth());
        // String startDateString = date.getYear() + "-" + date.getMonthValue() + "-01 00:00:00";
        // String endDateString = date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() + " 23:59:59";

        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // LocalDateTime startDate = LocalDateTime.parse(startDateString, dtf);
        // LocalDateTime endDate = LocalDateTime.parse(endDateString, dtf);

        UserIotDev userIotDev = new UserIotDev();
        userIotDev.setId(Integer.parseInt(id));
        userIotDev.setYear(String.valueOf(date.getYear()));
        userIotDev.setMonth(String.format("%2d", date.getMonthValue()));
        // userIotDev.setCreateTime(startDate);
        // userIotDev.setEditTime(endDate);

        List<UserSensor> resultList = userSensorMapper.getSensors(userIotDev);
        resultList.forEach(x -> x.formatSenseData());
        return resultList.size() > 0 ? ReqResult.success(resultList) : ReqResult.fail();
    }

    @PostMapping("modAlias")
    public ReqResult modAlias(@RequestBody UserSensor userSensor){
        if(userSensor.getVoltSelect() == true){
            userSensor.setVolt(220);
        }else{
            userSensor.setVolt(110);
        }

        return userSensorService.updateById(userSensor) ? ReqResult.success() : ReqResult.fail();
    }


}
