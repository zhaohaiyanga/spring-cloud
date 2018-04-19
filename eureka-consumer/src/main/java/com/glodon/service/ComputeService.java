
/**    
* @Title: ComputeService.java  
* @Package com.glodon.service  
* @Description: TODO(用一句话描述该文件做什么)  
* @author zhaohy-c  
* @date 2018年4月18日  
* @version V1.0    
*/

package com.glodon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @ClassName: ComputeService
 * @Description: 测试断路器
 * @author zhaohy-c
 * @date 2018年4月18日
 * 
 */
@Service
public class ComputeService {

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "addServiceFallback")
	public String find() {
		String s = restTemplate.getForEntity("http://API-USER-SERVER/user/find/123", String.class).getBody();
		return s;
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
	public String addServiceFallback() {
		return "error";
	}
	
}
