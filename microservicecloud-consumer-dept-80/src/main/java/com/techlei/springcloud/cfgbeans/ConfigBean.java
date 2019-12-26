package com.techlei.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;

@Configuration
public class ConfigBean {

	@Bean
	@LoadBalanced  //Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端       负载均衡的工具。
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public IRule MyRule() {
//		return new RoundRobinRule();//轮询 默认
//		return new RandomRule();//随机 
//		return new RetryRule();//retry 
//		return new AvailabilityFilteringRule();//不可用性过滤 
//		return new BestAvailableRule();//最佳可用 
//		return new WeightedResponseTimeRule();//权重相应时间
		return new ZoneAvoidanceRule();//
	}
}
