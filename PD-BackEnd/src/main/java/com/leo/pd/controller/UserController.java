package com.leo.pd.controller;

import com.leo.pd.comment.ReqResult;
import com.leo.pd.entity.User;
import com.leo.pd.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @PostMapping("login")
    public ReqResult login(@RequestBody User user){
        List<User> list = userService.lambdaQuery()
                .eq(User::getUserName, user.getUserName())
                .eq(User::getUserPassword, user.getUserPassword()).list();
        return list.size() > 0 ? ReqResult.success(list.get(0)) : ReqResult.fail();
    }

    @PostMapping("regist")
    public ReqResult createUser(@RequestBody User user){
        System.out.println(user);
        LocalDateTime time = LocalDateTime.now();
        user.setCreateTime(time);
        user.setEditTime(time);
        boolean succ = false;

        try {
            succ = userService.save(user);
        }catch (Exception e){
            succ = false;
        }

        return succ ? ReqResult.success() : ReqResult.fail();
    }
}
