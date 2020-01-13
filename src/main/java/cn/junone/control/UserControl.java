package cn.junone.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserControl {

    @RequestMapping("login")
    public String login(String username, String password) {
        return "";
    }

}
