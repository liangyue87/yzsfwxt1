package com.ljx.hfgsjt.main.config;

import com.ljx.hfgsjt.main.config.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                    throws Exception {
            response.setHeader("Access-Control-Allow-Credentials", "true");
          //  response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:8095");
            //response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");
            response.setHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers","Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,token");
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                ModelAndView modelAndView) throws Exception {
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                Exception ex) throws Exception {
        }
        });
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/UserController/login");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/WorkController /findBacklog");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/WorkController /findDone");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/uploadimge");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/uploadimge_1");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/saveQsXmzbgw");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/saveQsJtwzcy");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/getQsfk");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/saveQsFk");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/saveSgbmFk");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/saveXmjlFq");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/findBacklog");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/gettpck");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/appUpdate");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/appDownload");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/findDone");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/XkylxController/xmjlfq");
    }

}
