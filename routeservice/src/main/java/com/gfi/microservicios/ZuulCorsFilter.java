package com.gfi.microservicios;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ZuulCorsFilter extends ZuulFilter {

	public static Logger LOG = LoggerFactory.getLogger(ZuulCorsFilter.class);

	public String filterType() {
		return "route";
	}

	public int filterOrder() {
		return 0;
	}

	public boolean shouldFilter() {
		//RequestContext context = getCurrentContext();
		//LOG.debug(context.getRequest().getMethod());
		//return "OPTIONS".equals(context.getRequest().getMethod());
		return Boolean.TRUE;
	}

	public Object run() {
		RequestContext context = getCurrentContext();
		
		//String origin = context.getResponse().getHeader("Origin");
		//context.addZuulResponseHeader("Access-Control-Allow-Origin", origin);
		//context.addZuulResponseHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		//context.addZuulResponseHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Cache-Control");
		// context.setSendZuulResponse(false);
		context.sendZuulResponse();

		LOG.debug(context.toString());
		
		
		for(String h : Collections.list(context.getRequest().getHeaderNames()))
			LOG.debug("RequestHeader: " + h + " : " + context.getRequest().getHeader(h));


		if ("OPTIONS".equals(context.getRequest().getMethod())) {
			LOG.info("OPTIONS METHOD detected");
			context.setRouteHost(null);
			context.setResponseStatusCode(200);
		}
		
		for(String h : context.getResponse().getHeaderNames())
			LOG.debug("ResponseHeader: " + h + " : " + context.getResponse().getHeader(h));

		return null;
	}
}