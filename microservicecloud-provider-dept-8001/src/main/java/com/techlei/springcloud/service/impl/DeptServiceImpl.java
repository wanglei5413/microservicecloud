package com.techlei.springcloud.service.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlei.springcloud.dao.DeptDao;
import com.techlei.springcloud.entities.Dept;
import com.techlei.springcloud.service.DeptService;

@Service
@Log4j
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptDao dao;
	@Override
	public boolean add(Dept dept) {
		return dao.addDept(dept);
	}

	@Override
	public Dept get(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Dept> list() {
		return dao.findAll();
	}

	@Override
	public PageInfo<Dept> page(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo,pageSize);
		List<Dept> all = dao.findAll();
		log.info(all.getClass()+"\n"+all);//list返回的已经不是原始的list
		return new PageInfo<Dept>(all);
	}

}
