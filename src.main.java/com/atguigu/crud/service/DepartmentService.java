package com.atguigu.crud.service;

import java.util.List;
import com.atguigu.crud.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.Department;
@Service
public class DepartmentService {

	@Autowired
	private DepartmentMapper departmentMapper;
	public List<Department> getDepts() {
		List<Department> list = departmentMapper.selectByExample(null);
		return list;
	}

}
