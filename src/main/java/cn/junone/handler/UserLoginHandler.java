package cn.junone.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class UserLoginHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String test = request.getParameter("test");
        if (!StringUtils.isEmpty(test) && test.equalsIgnoreCase("junone")) {
            return true;
        } else {
            System.out.println("拦截了>");
            return false;
        }

        // return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
