package com.codingmobi.ui.core.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HttpLogInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
        // 获取输入参数  
        Map inputParamMap = request.getParameterMap();

        // 获取请求地址  
        String schemem = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String requestPath = request.getRequestURI();
        String targetUrl = schemem + "://" + serverName + ":" + serverPort + requestPath;
        
        String queryStr = request.getQueryString();
		if(StringUtils.isNotBlank(queryStr)){
			targetUrl = targetUrl + "?" + queryStr;
		}
//		logger.info("url: "+requestPath+"?"+queryStr+"; method: "+ request.getMethod() + "; param: " + inputParamMap);
        logger.info("url:" + targetUrl);
//        logger.info("url2:" + request.getContextPath());
		return super.preHandle(request, response, handler);
	}

}
