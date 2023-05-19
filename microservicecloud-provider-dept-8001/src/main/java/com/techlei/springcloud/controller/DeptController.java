package com.techlei.springcloud.controller;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = "/idx",method = RequestMethod.GET)
	public String idx(int i){
		int j=1/i;
		return "结果"+j;
	}

	/**
	 * 分页
	 * @return
	 */
	@RequestMapping(value="/dept/page",method=RequestMethod.GET)
	public PageInfo<Dept> page(@RequestParam("pageNo") Integer  pageNo, @RequestParam("pageSize") Integer pageSize){
		return service.page(pageNo,pageSize);
	}

}
