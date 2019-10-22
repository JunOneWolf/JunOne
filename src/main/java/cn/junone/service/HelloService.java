package cn.junone.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test1")
@ResponseBody
public class HelloService {
    @RequestMapping("/hello1")
    public String hello() {
        return "你好1";
    }
}
