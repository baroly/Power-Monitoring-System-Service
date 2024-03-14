package com.leo.pd.controller;

import com.leo.pd.comment.ReqResult;
import com.leo.pd.entity.UserServer;
import com.leo.pd.mapper.UserServerMapper;
import com.leo.pd.service.IUserIotDevService;
import com.leo.pd.service.IUserServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/userServer")
public class UserServerController {
    @Autowired
    IUserServerService userServerService;

    @Autowired
    UserServerMapper userServerMapper;

    @Autowired
    IUserIotDevService userIotDevService;

    @GetMapping("getServer")
    public ReqResult getServerById(String userId) {
        int id = Integer.parseInt(userId);
        List<UserServer> list = userServerService.lambdaQuery()
                .eq(UserServer::getUserId, id)
                .eq(UserServer::getDeleted, 0).list();
        System.out.println(list);
        return ReqResult.success(list, (long) list.size());
    }

    @PostMapping("creatServer")
    public ReqResult creatServer(@RequestBody UserServer userServer/**HashMap<String, Object> map**/) {
        LocalDateTime time = LocalDateTime.now();
        List<UserServer> serverResult = userServerService.lambdaQuery()
                .eq(UserServer::getUid, userServer.getUid()).list();
        int count = serverResult.size();
        if (count > 0 && serverResult.get(0).getDeleted() == 0) {
            return ReqResult.fail("伺服器已存在, 新增失敗");
        }

        List<UserServer> aliasList = userServerService.lambdaQuery()
                .eq(UserServer::getAlias, userServer.getAlias())
                .eq(UserServer::getUserId, userServer.getUserId()).list();
        if (aliasList.size() > 0) {
            return ReqResult.fail("別名已存在, 請更換別名");
        }
        // List<UserIotDev> devResult = userIotDevService.lambdaQuery()
        //         .eq(UserIotDev::getUid, iotUid).list();
        // if (devResult.size() > 0 && devResult.get(0).getDeleted() == 0) {
        //     return ReqResult.fail("IoT裝置已存在, 新增失敗");
        // }
        userServer.setEditTime(time);
        if (count > 0) {
            return userServerService.updateById(userServer) ? ReqResult.success() : ReqResult.fail();
        } else {
            userServer.setCreateTime(time);
            return userServerService.save(userServer) ? ReqResult.success() : ReqResult.fail();
        }
    }

    @PostMapping("modServer")
    public ReqResult modServer(@RequestBody UserServer userServer) {
        return userServerService.updateById(userServer) ? ReqResult.success() : ReqResult.fail();
    }
}
