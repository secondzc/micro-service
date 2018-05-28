package com.tongyuan;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangcy on 2018/5/28
 */
@RestController
public class HelloController {
    @GetMapping(value = "hello")
    public String hello(){
        return "hello";
    }
}
