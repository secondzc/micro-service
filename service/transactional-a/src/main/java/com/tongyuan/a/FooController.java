package com.tongyuan.a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangcy on 2018/5/31
 */
@RestController
public class FooController {

    @Autowired
    private FooService fooService;

    @GetMapping("/foo")
    public void foo(@RequestParam("name") String name) {
        fooService.insertFoo(name);
    }
}
