package cn.itcast.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.biz.IDepBiz2;
import cn.itcast.erp.entity.Dep;

//部门Action
public class DepAction2 extends BaseAction<Dep>{
	private IDepBiz2 depBiz2;
	
	public void setDepBiz(IDepBiz2 depBiz2) {
		this.depBiz2 = depBiz2;
		super.setBaseBiz(depBiz2);
	}

	
	
	
	
}
