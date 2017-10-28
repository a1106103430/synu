package com.atguigu.crud.bean;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.New;
import javax.naming.spi.DirStateFactory.Result;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.util.concurrent.SuccessCallback;

/**
 * 通用的返回的类
 * 弥补PageInfo的不足
 * @author 青衣灬紫猪
 *
 */
public class Msg {
  //状态码 100-成功  200-失败
	private int code;
	//提示信息
	private String msg;
	
	//用户返回给浏览器的数据
	private Map<String, Object> extend = new HashMap<String,Object>();
	
	public static Msg success(){
		Msg result = new Msg();
		result.setCode(100);
		result.setMsg("处理成功！");
		return result;
	}

	public static Msg fail(){
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("处理失败！");
		return result;
	}

    public Msg add(String key,Object value){
    	this.extend.put(key,value);
    	return this;
    }
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	
}
