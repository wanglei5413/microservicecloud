package com.techlei.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techlei.springcloud.entities.Dept;
import com.techlei.springcloud.service.DeptService;

@RestController
public class DeptController {
	
	@Autowired
	private DeptService service;
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value="/dept/add",method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}

	@RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return service.get(id);
	}

	@RequestMapping(value="/dept/list",method=RequestMethod.GET)
	public List<Dept> list(){
		return service.list();
	}
	
	@RequestMapping(value="/dept/discovery",method=RequestMethod.GET)
	public Object discovery(){
		System.out.println(client.getServices());
		
		List<ServiceInstance> list = client.getInstances("MICROSERVICECLOUD-DEPT");
		for (ServiceInstance serviceInstance : list) {
			System.out.println(serviceInstance.getServiceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());
		}
		return this.client;
	}
	
}
