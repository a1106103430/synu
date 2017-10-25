package com.atguigu.crud.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 处理员工增删改查请求
 * 
 *
 */

@Controller
@RequestMapping("/emps")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model){
		//引入pageHelper插件
		PageHelper.startPage(pn, 5);
		//startPage后紧跟的查询就是分页查询
		List<Employee> emps = employeeService.getAll();
		//使用PageInfo包装查询后的结果
		//PageInfo封装了详细的分页信息包括查询出的数据，只需将pageInfo交给页面即可
		//参数5表示连续显示5页
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo", page);
		System.out.println("当前页："+page.getPageNum());
    	System.out.println("总页码："+page.getPages());
    	System.out.println("总记录数："+page.getTotal());
		//System.out.print("*******************************");
		return"list";
	}
}
