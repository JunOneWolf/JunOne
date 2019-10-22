package cn.junone.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.junone.handler.UserLoginHandler;

@Configuration
public class HandlerConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry handlers) {
        handlers.addInterceptor(new UserLoginHandler()).addPathPatterns("/**").excludePathPatterns("/test2");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resources) {
        resources.addResourceHandler("/resource/**");
    }
}
