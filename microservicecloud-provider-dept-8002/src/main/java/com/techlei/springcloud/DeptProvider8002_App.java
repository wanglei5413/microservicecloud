package com.techlei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient//服务端启动后注册到eureka注册中心
@EnableDiscoveryClient
public class DeptProvider8002_App {
	
	public static void main(String[] args) {
		SpringApplication.run(DeptProvider8002_App.class,args);
	}
}
