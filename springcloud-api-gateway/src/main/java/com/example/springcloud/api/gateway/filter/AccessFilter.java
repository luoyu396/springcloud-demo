package com.example.springcloud.api.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tanbb
 * @create 2020-05-29 18:47
 */
public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

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
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            logger.warn("accessToken is empty");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseBody("accessToken is empty");
            requestContext.setResponseStatusCode(401);
        }
        logger.info("accessToken ok");
        return null;
    }
}
