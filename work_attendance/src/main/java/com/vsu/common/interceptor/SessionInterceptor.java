package com.vsu.common.interceptor;

import com.vsu.user.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by vsu on 2018/03/15.
 */
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String uri = httpServletRequest.getRequestURI();

        if ((uri.indexOf("login") >= 0) || (uri.indexOf("sign") >= 0) ||(uri.indexOf("error")>=0)){
            return true;
        }

        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        /*HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("userInfo");*/

        if (user != null) {
            return true;
        }

//        转发到登录
        httpServletRequest.getRequestDispatcher("/login").forward(httpServletRequest, httpServletResponse);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
