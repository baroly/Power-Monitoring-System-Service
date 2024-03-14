package com.leo.pd.controller;

import com.leo.pd.comment.ReqResult;
import com.leo.pd.entity.UserIotDev;
import com.leo.pd.entity.UserSensor;
import com.leo.pd.service.IUserIotDevService;
import com.leo.pd.service.IUserSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userDev")
public class UserIoTDevController {
    @Autowired
    IUserIotDevService userIotDevService;

    @Autowired
    IUserSensorService userSensorService;

    @GetMapping("getDev")
    public ReqResult getDev(String serverId) {
        List<UserIotDev> resultList = userIotDevService.lambdaQuery()
                .eq(UserIotDev::getUserServerId, serverId)
                .eq(UserIotDev::getDeleted, 0).list();
        return resultList != null ? ReqResult.success(resultList, (long) resultList.size()) : ReqResult.fail();
    }

    @PostMapping("createDev")
    public ReqResult createDev(@RequestBody UserIotDev userIotDev) {
        LocalDateTime time = LocalDateTime.now();

        List<UserIotDev> resultList = userIotDevService.lambdaQuery()
                .eq(UserIotDev::getUid, userIotDev.getUid()).list();
        int count = resultList.size();

        if (count > 0 && resultList.get(0).getDeleted() == 0) {
            return ReqResult.fail("IoT裝置已存在, 新增失敗");
        }

        List<UserIotDev> aliasList = userIotDevService.lambdaQuery()
                .eq(UserIotDev::getAlias, userIotDev.getAlias())
                .eq(UserIotDev::getUserServerId, userIotDev.getUserServerId()).list();
        if (aliasList.size() > 0) {
            return ReqResult.fail("別名已存在, 請更換別名");
        }

        userIotDev.setEditTime(time);

        //batchInsert(userIotDev, time);
        //System.out.println(userIotDev.getUid());
        System.out.println(userIotDev.getId());
        int portCount = Integer.parseInt(userIotDev.getUid().substring(2, 4)) + 1;

        List<UserSensor> sensorList = new ArrayList<>();


        if (count > 0) {
            userIotDevService.updateById(userIotDev);
        } else {
            userIotDev.setCreateTime(time);
            userIotDevService.save(userIotDev);
        }

        List<UserIotDev> idList = userIotDevService.lambdaQuery()
                .eq(UserIotDev::getUid, userIotDev.getUid())
                .eq(UserIotDev::getDeleted, 0).list();

        int iotDevId = idList.get(0).getId();

        for (int i = 1; i < portCount; i++) {
            UserSensor sensor = new UserSensor();
            sensor.setIotDevId(iotDevId);
            sensor.setPortNum(i);
            sensor.setAlias(String.valueOf(i));
            sensor.setCreateTime(time);
            sensor.setEditTime(time);

            sensorList.add(sensor);
        }

        return userSensorService.saveBatch(sensorList) ? ReqResult.success() : ReqResult.fail();
    }

    @PostMapping("modDev")
    public ReqResult modDev(@RequestBody UserIotDev userIotDev) {
        return userIotDevService.updateById(userIotDev) ? ReqResult.success() : ReqResult.fail();
    }

    // public boolean batchInsert(UserIotDev userIotDev, LocalDateTime time) {
    //     int portCount = Integer.parseInt(userIotDev.getAlias().substring(2, 4)) + 1;
    //
    //     List<UserSensor> sensorList = new ArrayList<>();
    //     for (int i = 1; i < portCount; i++) {
    //         UserSensor sensor = new UserSensor();
    //         sensor.setIotDevId(userIotDev.getId());
    //         sensor.setPortNum(i);
    //         sensor.setAlias(String.valueOf(i));
    //         sensor.setCreateTime(time);
    //         sensor.setEditTime(time);
    //
    //         sensorList.add(sensor);
    //     }
    //
    //     return userSensorService.saveBatch(sensorList);
    // }
}
