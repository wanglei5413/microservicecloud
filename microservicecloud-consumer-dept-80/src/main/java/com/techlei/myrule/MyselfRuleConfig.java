package com.techlei.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

@Configuration
public class MyselfRuleConfig {

	@Bean
	public IRule getMyRule() {
//		return new RoundRobinRule();//轮询
		return new MyselfRule();
	}
}
