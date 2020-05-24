package com.example.springcloud.consumer.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter(urlPatterns = "/*")
public class RequestCacheFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(RequestCacheFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化init filter...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /**
         * 不初始化，会报如下错误
         * Request caching is not available. Maybe you need to
         * initialize the HystrixRequestContext
         * 初始化是在filter中进行（官方建议），但是每一次初始化之前的缓存就失效了，所以要测缓存，就只能在controller中调用两次，
         * 才能看到缓存的结果是否相同
         *  在同一用户请求的上下文中，相同依赖服务的返回数据始终保持一致  ---《spring cloud 微服务实战》有争论
         */
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            context.shutdown();
        }
    }

    @Override
    public void destroy() {

    }
}
