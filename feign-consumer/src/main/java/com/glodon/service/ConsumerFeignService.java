/**    
* @Title: ConsumerFeignService.java  
* @Package com.glodon.service  
* @author zhaohy-c  
* @date 2018年4月18日  
* @version V1.0    
*/
package com.glodon.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
* @ClassName: ConsumerFeignService  
* @Description: feign 消费端 测试
* @author zhaohy-c  
* @date 2018年4月18日  
*    
*/  
@FeignClient(value = "api-user-server")
public interface ConsumerFeignService {
	
	@RequestMapping(value = "/user/find/123",method = RequestMethod.GET)
	String find();
	
}
