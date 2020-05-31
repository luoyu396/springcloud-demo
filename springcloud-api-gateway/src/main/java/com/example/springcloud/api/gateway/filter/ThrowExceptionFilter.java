package com.example.springcloud.api.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class ThrowExceptionFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(ThrowExceptionFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        logger.info("this is pre filter , it is a run time exception");
        doSomeThing();
        return null;
    }

    private void doSomeThing() {
        throw new RuntimeException("exists some errors");
    }
}
