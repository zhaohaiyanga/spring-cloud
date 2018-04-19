/**    
* @Title: Controller.java  
* @Package com.glodon.controller  
* @author zhaohy-c  
* @date 2018年4月18日  
* @version V1.0    
*/
package com.glodon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.glodon.service.ConsumerFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**  
* @ClassName: Controller  
* @Description:  
* @author zhaohy-c  
* @date 2018年4月18日  
*    
*/
@RestController
public class Controller {
	
	@Autowired
	ConsumerFeignService consumerFeignService;
	
	
	@RequestMapping(value = "/user/find",method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "findFallback")
	public String find() {
		return consumerFeignService.find();
	}
	
	
	/**  
	* @Title: addServiceFallback  
	* @Description: 测试find调用失败时调用此方法 
	* @author zhaohy-c 
	* @param @return    参数  
	* @return String    返回类型  
	* @date 2018年4月18日  
	* @throws  
	*/   
	public String findFallback() {
		return "error";
	}
}
