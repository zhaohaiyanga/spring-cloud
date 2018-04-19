package com.glodon;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class SpringCloudZuulApplication {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(SpringCloudZuulApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudZuulApplication.class, args);
	}
	
	@Component
	class Myfilter extends ZuulFilter{

		/* (非 Javadoc)  
		*   
		*   
		* @return  
		* @see com.netflix.zuul.IZuulFilter#run()  
		*/
		@Override
		public Object run() {
			RequestContext ctx = RequestContext.getCurrentContext();
	        HttpServletRequest request = ctx.getRequest();
	        logger.info("method:{},url:{}", request.getMethod(), request.getRequestURL().toString());

	        Object accessToken = request.getParameter("token");
	        if(accessToken == null) {
	        	logger.warn("token is empty");
	            ctx.setSendZuulResponse(false);
	            ctx.setResponseStatusCode(401);
	            try {
	                ctx.getResponse().getWriter().write("token is empty");
	            }catch (Exception e){}

	            return null;
	        }
	        logger.info("ok");
	        return null;
		}

		/* (非 Javadoc)  
		*   
		*   
		* @return  
		* @see com.netflix.zuul.IZuulFilter#shouldFilter()  
		*/
		@Override
		public boolean shouldFilter() {
			return true;
		}

		/* (非 Javadoc)  
		*   
		*   
		* @return  
		* @see com.netflix.zuul.ZuulFilter#filterOrder()  
		*/
		@Override
		public int filterOrder() {
			return 0;
		}

		/* (非 Javadoc)  
		*   
		*   pre：路由之前
			routing：路由之时
			post： 路由之后
			error：发送错误调用  
		* @return  
		* @see com.netflix.zuul.ZuulFilter#filterType()  
		*/
		@Override
		public String filterType() {
			return "pre";
		}
		
	}
}
