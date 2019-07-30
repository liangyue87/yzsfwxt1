package com.ljx.hfgsjt.main.config.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    //跳过登录检测的请求
    private static final String[] IGNORE_URI = {"/XkylxController/uploadCamera","/uploader/chunk",
            "/UserController/login","/WorkController/findBacklog","/WorkController/findDone",
            "/XkylxController/getXmxx","/XkylxController/getFkQsCode","/XkylxController/saveQsXmzbgw",
            "/XkylxController/saveQsJtwzcy","/XkylxController/getQsfk","/XkylxController/saveXckcFk",
            "/XkylxController/saveQsFk","/XkylxController/saveXmjlFq","/XkylxController/getFkpjKcbm",
            "/XkylxController/findBacklog","/XkylxController/gettpck","/XkylxController/saveSgbmFk",
            "/XkylxController/appUpdate","/XkylxController/appDownload","/XkylxController/findDone",
            "/XkylxController/xmjlfq"};

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        boolean flag = false;
        String url = request.getRequestURL().toString();
        //System.out.println(">>>: " + url);
        for (String s : IGNORE_URI) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
    if (!flag) {
            HttpSession session = request.getSession();
            //这里的User是登陆时放入session的
            Object user =  session.getAttribute("user");
            //如果session中没有user，表示没登陆
            if (user == null){
                response.getWriter().print("noLogin");
                flag = false;
            }else {
                flag = true;
            }
        }
        return flag;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
