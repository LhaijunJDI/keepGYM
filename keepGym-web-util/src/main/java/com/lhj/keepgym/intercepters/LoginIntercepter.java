package com.lhj.keepgym.intercepters;

import com.alibaba.fastjson.JSON;
import com.lhj.keepgym.annotations.LoginRequired;
import com.lhj.keepgym.utils.CookieUtil;
import com.lhj.keepgym.utils.HttpclientUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 拦截器
 * @author Shinelon
 */
@Component
public class LoginIntercepter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //转化为handlerMethod可以获取调用方法上的注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired methodAnnotation = handlerMethod.getMethodAnnotation(LoginRequired.class);

        //若有注解则需要拦截，无注解则放行
        if (methodAnnotation == null) {
            return true;
        }

        //从浏览器中获取oldToken的值，若之前并未登录过则为空
        String token = CookieUtil.getCookieValue(request, "oldToken", true);

        //从登录校验返回的数据中获取token
        String newToken = request.getParameter("token");
        if (!StringUtils.isEmpty(newToken)) {
            token = newToken;
        }
        String result = "fail";
        Map<String, String> resultMap = new HashMap<String, String>();

        //token不为空则需要校验token是否为真，若为空则说明用户没有登录，返回到登录界面
        if (!StringUtils.isEmpty(token)) {
            //获取ip地址传递给校验方法
            String currentIp = request.getRemoteAddr();
            if (!StringUtils.isEmpty(currentIp)) {
                currentIp = "127.0.0.1";
            }
            //使用httpClient工具包发送浏览器请求并把当前ip传递过去
            String resultJson = HttpclientUtil.doGet("http://localhost:8080/verify?token=" + token + "&currentIp=" + currentIp);
            //将返回的json数据封装到map中
            resultMap = JSON.parseObject(resultJson, Map.class);
            result = resultMap.get("status");
            request.setAttribute("memberId", resultMap.get("memberId"));
            request.setAttribute("memberName", resultMap.get("memberName"));

            //若map中的状态为fail则说明token校验失败，返回到登录界面重新登录
            if ("fail".equals(result)) {
                request.setAttribute("message","账号已过期，请重新登录！");
                request.getRequestDispatcher("/main").forward(request,response);
                return false;
            }
            //map中状态为success，则将token保存在浏览器的cookie中并放行
            CookieUtil.setCookie(request, response, "oldToken", token, 60 * 60 * 2, true);
            return true;
        }
        request.setAttribute("message","您还没有登录，请登录后访问！");
        request.getRequestDispatcher("/main").forward(request,response);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
