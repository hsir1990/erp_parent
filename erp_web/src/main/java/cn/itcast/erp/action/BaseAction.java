package cn.itcast.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import cn.itcast.erp.biz.IBaseBiz;

public class BaseAction<T> {
private IBaseBiz<T> baseBiz;
	
	public void setBaseBiz(IBaseBiz<T> baseBiz) {
		this.baseBiz = baseBiz;
	}

	//属性驱动条件查询
	private T t1;
	private T t2;
	private Object param;
	public T getT2() {
		return t2;
	}
	public void setT2(T t2) {
		this.t2 = t2;
	}
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}
	public T getT1() {
		return t1;
	}
	public void setT1(T t1) {
		this.t1 = t1;
	}
	
	
	private int page;//页码
	private int rows;//页码的记录
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	//条件查询
	public void getList() {
		System.out.println("页码"+page+"记录数："+rows);
		int firstResult =(page-1)*rows;
		List<T> list = baseBiz.getList(t1,t2,param,firstResult,rows);
		long total = baseBiz.getCount(t1,t2,param);
		//{total:total,rows:[]}
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("total", total);
		mapData.put("rows", list);
		//把部门列表转JSON字符串
		String listString = JSON.toJSONString(mapData);
		try {
			//响应对象
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			//输出给页面
//					ServletActionContext.getResponse().getWriter().write(listString);
			response.getWriter().write(listString);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	

	//查询所有部门
	public void list() {
		//调用部门业务查询所有部门信息
		List<T> list = baseBiz.getList();
		//把部门列表转JSON字符串
		String listString = JSON.toJSONString(list);
		write(listString);
		
	}
	
	//响应的方法
	public void write(String jsonString) {
		try {
			//响应对象
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			//输出给页面
			//ServletActionContext.getResponse().getWriter().write(listString);
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	//新增，修改
	private T t;
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	//新增部门
	public void add() {
		//返回前端的JSON数据
		//{"success":true,"message":""}
		Map<String, Object> rth = new HashMap<String, Object>();
		try {
			baseBiz.add(t);
			rth.put("success",true);
			rth.put("message","新增成功");
		} catch (Exception e) {
			e.printStackTrace();
			rth.put("success",false);
			rth.put("message","新增失败");
		}
		write(JSON.toJSONString(rth));
	}
	
	
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	//删除
	public void delete() {
		try {
			baseBiz.delete(id);
			ajaxReturn(true,"删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false,"删除失败");
		}
	}
	//封装返回值
	public void ajaxReturn(boolean success, String message) {
		Map<String, Object> rth = new HashMap<String, Object>();
		rth.put("success",success);
		rth.put("message",message);
		write(JSON.toJSONString(rth));
	}
	
	//通过编号查询数据
	public void get() {
		T t = baseBiz.get(id);
		String jsonString = JSON.toJSONString(t);
		//转成map对象
		String jsonStringAfter = mapData(jsonString,"t");
		write(jsonStringAfter);
	}
	
	//JSON数据字符串，加前缀
	//{"name":"张三","id","11"} ==> {"t.name":"张三","t.id","11"}
	public String mapData(String jsonString, String prefix) {
		Map<String, Object> map = JSON.parseObject(jsonString);
		
		//储存key加上前缀后的值
		Map<String, Object> dataMap = new HashMap<String, Object>();
		//给每个key值加上前缀
		for (String key : map.keySet()) {
			dataMap.put(prefix+"."+key, map.get(key));
		}
		
		return JSON.toJSONString(dataMap);
	}
	
	//更新
	public void update() {
		try {
			baseBiz.update(t);
			ajaxReturn(true,"修改成功");
		} catch (Exception e) {
			
			e.printStackTrace();
			ajaxReturn(false,"修改失败");
		}
	}
}
