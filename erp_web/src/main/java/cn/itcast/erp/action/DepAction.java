package cn.itcast.erp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.entity.Dep;

//部门Action
public class DepAction {
	private IDepBiz depBiz;
	
	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
	}

	//查询所有部门
	public void list() {
		//调用部门业务查询所有部门信息
		List<Dep> list = depBiz.getList();
		//把部门列表转JSON字符串
		String listString = JSON.toJSONString(list);
		try {
			//响应对象
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			//输出给页面
//			ServletActionContext.getResponse().getWriter().write(listString);
			response.getWriter().write(listString);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
