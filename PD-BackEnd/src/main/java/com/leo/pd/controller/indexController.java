package com.leo.pd.controller;

import com.leo.pd.comment.ReqResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
    @GetMapping("/")
    public ReqResult index(){
        return ReqResult.success();
    }
}
