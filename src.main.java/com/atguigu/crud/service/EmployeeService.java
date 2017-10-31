package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import org.springframework.web.bind.annotation.PathVariable;




//import org.hibernate.*;
import com.atguigu.crud.bean.*;
import com.atguigu.crud.bean.EmployeeExample.Criteria;
import com.atguigu.crud.dao.*;
@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	
	//查询所有员工
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDept(null);
	}

	//保存新增员工的信息
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	//检验用户名是否可用,true代表当前用户名可用
	public boolean checkuser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count =  employeeMapper.countByExample(example);
		return count == 0;
	}

	//按ID查询员工
	public Employee getEmp(Integer id) {
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	//员工更新
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}

	//员工删除
	public void deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}

	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//拼装形式为：delete from xxx where id in (1,2,3)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}

}
