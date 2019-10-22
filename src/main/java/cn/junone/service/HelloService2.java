package cn.junone.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test2")
@ResponseBody
public class HelloService2 {
    @RequestMapping("/hello2")
    public String hello() {
        return "你好2";
    }
}
