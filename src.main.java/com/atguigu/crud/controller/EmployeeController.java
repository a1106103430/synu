package com.atguigu.crud.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 处理员工增删改查请求
 * 
 *
 */

@Controller
//@RequestMapping("/emps")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	/**
	 * 单个删除与批量删除二合一
	 * 批量删除传递id示例：1-2-3
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("ids")String ids){
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			for(String string:str_ids){
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		
		return Msg.success();
	}
	
	/**
	 * 如果直接使用put方式请求
	 * 封装的employee对象除了路径带的id，其余属性全是null
	 * @RequestParam("employee")报employee不存在
	 * 
	 * 前台查看请求体发现有数据
	 * 但employee封装不上
	 * 由于其他字段全为空，sql拼装成了update tble_emp where emp_id=#{empId}
	 * 故报sql语法错误
	 * 
	 * tomcat封装过程：
	 *    1.将请求体中数据封装一个map
	 *    2.request.getParameter("empName")就从map中取值
	 *    3.springMVC封装POJO对象时，会为POJO中每个属性调用request.getParameter("");
	 *    
	 * request.getParameter("")拿不到put请求的请求体中的数据
	 * 因为tomcat不会将put请求的请求体中的数据封装成一个map,只有Post请求才会被封装
	 * 可以配置HttpPutFormContentFilter解决
	 * 该过滤器将请求体中的数据包装成一个map,并重新包装request(重写父类request.getParameter("")方法)
	 * 此方法从自己封装的map中取数据
	 */
	//修改员工信息保存
	//邮箱，性别等信息从表单中获取，springMVC自动封装到employee对象中
	//ID属性从路径中获取，保持名称与bean中属性名称相同也可以自动封装
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee){
		//System.out.println(employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	//根据ID查询员工
	//@PathVariable指定参数来源于请求路径
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id") Integer id){
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuser(@RequestParam("empName")String empName){
		//先判断用户名是否合法
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|([\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regex)){
			return Msg.fail().add("va_msg", "用户名必须的6-16位的数字字母组合或2-5位中文");
		}
		//数据库用户名重复校验
		boolean b = employeeService.checkuser(empName);
		if(b){
			return Msg.success();
		}else{
			//System.out.println(Msg.fail().getCode());
			return Msg.fail().add("va_msg", "用户名不可用");
		}
	}
	//新增员工保存
	//为支持JSR303校验，导入hibernate-validator-4.3.0.Final.jar
	//tomcat7以下的服务器须额外给服务器的lib包替换新的标准的el,tomcat7及以上只需添加jar包即可
	@RequestMapping(value="/emp", method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result){
		if(result.hasErrors()){
			List<FieldError> errors = result.getFieldErrors();
			Map<String, Object> map = new HashMap<>();
			for(FieldError fieldError : errors){
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("FieldErrors", map);
		}else{
		employeeService.saveEmp(employee);
		return Msg.success();
		}
	}
	
	@RequestMapping("/emps")
	//此注解可以自动返回json数据,需要导入jacson包
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn){
		    //引入pageHelper插件
			PageHelper.startPage(pn, 5);
		    //startPage后紧跟的查询就是分页查询
			List<Employee> emps = employeeService.getAll();
		    //使用PageInfo包装查询后的结果
			//PageInfo封装了详细的分页信息包括查询出的数据，只需将pageInfo交给页面即可
			//参数5表示连续显示5页
			PageInfo page = new PageInfo(emps,5);
			//JSONObject jsonObject = new JSONObject(page);
			return Msg.success().add("pageInfo", page);
	}
	
	
	//@RequestMapping("/emps")
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
//		System.out.println("当前页："+page.getPageNum());
//    	System.out.println("总页码："+page.getPages());
//    	System.out.println("总记录数："+page.getTotal());
		return"list";
	}
}
