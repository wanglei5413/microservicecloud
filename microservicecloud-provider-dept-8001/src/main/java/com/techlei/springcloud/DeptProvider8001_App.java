package com.techlei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient//服务端启动后注册到eureka注册中心
public class DeptProvider8001_App {
	
	public static void main(String[] args) {
		SpringApplication.run(DeptProvider8001_App.class,args);
	}
}
