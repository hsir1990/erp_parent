package cn.itcast.erp.biz;

import java.util.List;

import cn.itcast.erp.entity.Dep;

//部门业务接口
public interface IDepBiz {

	//查询所有部门接口类
	List<Dep> getList();
}
