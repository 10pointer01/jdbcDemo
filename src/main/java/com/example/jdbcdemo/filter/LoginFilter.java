package com.example.jdbcdemo.filter;

import com.example.jdbcdemo.pojo.Manager;
import com.example.jdbcdemo.service.Impl.LoginServiceImpl;
import com.example.jdbcdemo.service.ManagerService;
import com.example.jdbcdemo.util.CookieUtil;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "FilterDemo01", urlPatterns = { "/*" })
public class LoginFilter implements Filter {
    @Resource
    ManagerService managerService;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String request_url = request.getRequestURI();
        if(     request_url.contains(".css") || request_url.contains(".js") ||
                request_url.contains(".png")|| request_url.contains(".do") ||
                request_url.contains(".jpg")
        ){
            //如果发现是css或者js文件，直接放行
            System.out.println("文件加载");
            chain.doFilter(request, response);
        }

        if(request_url.contains("/login")) {//如果访问login
            chain.doFilter(request, response);
            System.out.println("访问登录");
        }else {
            Integer userID = (Integer) request.getSession().getAttribute("id");
            String val = CookieUtil.getCookieValByKey("auto", request);
            if(userID==null) {
                if(val!= null&& !val.equals("")) {
                    int id = Integer.parseInt(val.split("_")[0]);
                    String pass = val.split("_")[1];
                    if(managerService.check(id, pass) > 0){
                        request.getSession().setAttribute("id", id);
                        chain.doFilter(request, response);
                    }else{
                        System.out.println("44444");
                        response.sendRedirect("http://192.168.1.85:8088/login");
                    }
                }else{
                    System.out.println("没有曾经记录");
                    response.sendRedirect("http://192.168.1.85:8088/login");
                }
            } else {
                chain.doFilter(request, response);
            }

        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
