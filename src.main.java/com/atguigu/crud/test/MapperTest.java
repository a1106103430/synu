package com.atguigu.crud.test;


import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

/**
 * 测试dao层
 *spring的项目使用spring的测试单元，可以自动注入需要的组件
 *1.导入spring测试模块spring-test-3.2.0.RELEASE.jar
 *2.@ContextConfiguration指定spring配置文件的位置
 *3.直接@Autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
    /**
     * 测试DepartmentMapper
     */
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCRUD(){
		/*//1.创建springIOC容器
		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.从容器中获取mapper
		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);*/
		//System.out.println(departmentMapper);
		
		//测试多次会被重复插入
//		departmentMapper.insertSelective(new Department(null,"开发部"));
//		departmentMapper.insertSelective(new Department(null,"测试部"));
		
//		employeeMapper.insertSelective(new Employee(null, "Jerry", "M", "Jerry@", 1));
	    
//	    EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//	    for(int i=0;i<1000;i++){
//	    	String uid = UUID.randomUUID().toString().substring(0, 5) + i;
//	    	mapper.insertSelective(new Employee(null,uid, "M", uid+"@guigu.com", 1));
//	    }
		
//		employeeMapper.deleteByPrimaryKey(5);
		employeeMapper.updateByPrimaryKeySelective(new Employee(10, "Lisa", "F", "156468@qq.com", 1));
//		Employee employee = employeeMapper.selectByPrimaryKey(1);
//		System.out.println(employee);
		
//		DepartmentMapper department = sqlSession.getMapper(DepartmentMapper.class);
//		for(int i=0;i<10;i++){
//			String uid = UUID.randomUUID().toString().substring(0, 5) + i;
//			department.insertSelective(new Department(null,uid));
//		}
//		departmentMapper.deleteByPrimaryKey(2);
//		departmentMapper.updateByPrimaryKeySelective(new Department(1, "设计部"));
//		Department department2 = departmentMapper.selectByPrimaryKey(1);
//		System.out.println(department2);
	    //System.out.println("批量完成");
	}
}
