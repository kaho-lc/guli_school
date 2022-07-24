package com.lc.eduservice.controller;

import com.lc.commomutils.ResultClass;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    //login
    @PostMapping("/login")
    public ResultClass login() {
        return ResultClass.Success().data("token", "admin");

    }

    //info
    @GetMapping("/info")
    public ResultClass info() {
        return ResultClass.Success().data("name" , "admin").data("avatar" , "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
