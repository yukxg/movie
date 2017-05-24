package com.redteamobile.movie.interceptor;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.redteamobile.movie.service.BaseService;

@Component
public class LogInterceptor extends BaseService implements HandlerInterceptor {

    private static final ThreadLocal<Long> startTimeThreadLocal = new NamedThreadLocal<Long>(
            "ThreadLocal StartTime");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        long beginTime = System.currentTimeMillis();
        startTimeThreadLocal.set(beginTime);
        logger.info("开始记时: {}  URI: {}", new SimpleDateFormat("hh:mm:ss.SSS").format(beginTime),
                request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            logger.info("ViewName: " + modelAndView.getViewName());
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
        StringBuilder sb = new StringBuilder();
        String type = ex == null ? "Access" : "Exception";
        sb.append(type).append(",ip: ").append(request.getRemoteAddr()).append(",user-agent: ")
                .append(request.getHeader("user-agent"));
        sb.append(",requset-uri:").append(request.getRequestURI());
        sb.append(request.getParameterMap());
        logger.info(sb.toString());

        long beginTime = startTimeThreadLocal.get();// 得到线程绑定的局部变量（开始时间）
        long endTime = System.currentTimeMillis(); // 2、结束时间
        logger.debug(
                "计时结束：{}  耗时：{} ms,  URI: {}  最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                new SimpleDateFormat("hh:mm:ss.SSS").format(endTime), (endTime - beginTime),
                request.getRequestURI(), Runtime.getRuntime().maxMemory() / 1024 / 1024,
                Runtime.getRuntime().totalMemory() / 1024 / 1024,
                Runtime.getRuntime().freeMemory() / 1024 / 1024,
                (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()
                        + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        // 删除线程变量中的数据，防止内存泄漏
        startTimeThreadLocal.remove();
    }

}
