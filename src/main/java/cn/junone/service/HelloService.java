package cn.junone.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {
    @RequestMapping("hello")
    public String hello() {
        return "你好";
    }
}
