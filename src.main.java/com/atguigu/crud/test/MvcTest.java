package com.atguigu.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.atguigu.crud.bean.Employee;
import com.github.pagehelper.PageInfo;

/**
 * 使用spring测试模块提供的请求测试功能
 * 测试crud请求功能的正确性
 * spring4的测试需要servlet3.0的支持，注意servlet-api.jar包的版本
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:WebRoot/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {
	//传入springmvc的ioc,而@Autowired只能注入ioc容器内部的对象
	//此处通过@WebAppConfiguration拿到ioc容器
	@Autowired
	WebApplicationContext context;
	//虚拟mvc请求，获取处理结果
    MockMvc mockMvc;
    @Before
    public void initMockMvc(){
    	//这个返回的mockMvc可以模拟请求的发送
    	mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void testPage()throws Exception{
    	//模拟请求，拿到返回值
    	MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
        //请求成功后，请求域中会有pageInfo
    	//取出pageInfo进行验证
    	MockHttpServletRequest request = result.getRequest();
    	PageInfo pi=(PageInfo)  request.getAttribute("pageInfo");
    	System.out.println(pi);
//    	System.out.println("当前页："+pi.getPageNum());
//    	System.out.println("总页码："+pi.getPages());
//    	System.out.println("总记录数："+pi.getTotal());
//    	int[] num = pi.getNavigatepageNums();
//    	for(int i:num){
//    		System.out.print(" "+i);
//    	}
//    	//获取员工数据
//    	List<Employee> list = pi.getList();
//    	for(Employee employee : list){
//    		System.out.println("ID:"+employee.getdId()+"=====>>name:"+employee.getEmpName());
//    	}
    }
}
