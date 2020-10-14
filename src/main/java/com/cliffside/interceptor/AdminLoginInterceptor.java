package com.cliffside.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@Component//注册到ioc容器当中
public class AdminLoginInterceptor implements HandlerInterceptor {
    /**
     * preHandle：在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(uri.startsWith("/admin")&&request.getSession().getAttribute("loginUser")==null){
            request.getSession().setAttribute("errorMsg","请登录");
            response.sendRedirect(request.getContextPath()+"/admin/login");
            return false;
        }else {
            request.getSession().removeAttribute("errorMsg");
            return true;
        }
    }

    /**
     * postHandle：在业务处理器处理请求执行完成后，生成视图之前执行。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * afterCompletion：在 DispatcherServlet 完全处理完请求后被调用，可用于清理资源等，返回处理（已经渲染了页面）；
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
/**
 * 在实现拦截器的相关方法之后，我们需要对该拦截器进行配置以使其生效，
 * 在 Spring Boot 1.x 版本中我们通常会继承 WebMvcConfigurerAdapter 类，
 * 但是在 Spring Boot 2.x 版本中，WebMvcConfigurerAdapter 被弃用，
 * 虽然继承 WebMvcConfigurerAdapter 这个类虽然有此便利，
 * 但在 Spring 5.0 里面已经被弃用了，官方文档也说了，
 * WebMvcConfigurer 接口现在已经有了默认的空白方法，
 * 所以在 Spring Boot 2.x 版本下更好的做法还是实现
 * WebMvcConfigurer 接口。
 */
