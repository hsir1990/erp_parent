package cn.itcast.erp.dao;

import java.util.List;

import cn.itcast.erp.entity.Dep;

public interface IDepDao {
	List<Dep> getList();
	
	//条件查询
	List<Dep> getList(Dep dep1, Dep dep2, Object param, int firstResult, int maxResults);
	
	//记录条件查询的总记录数
	long getCount(Dep dep1, Dep dep2, Object param);
	
	//新增部门
	void add(Dep dep);
	
	//删除
	void delete(Long uuid);

	//通过编号查询对象
	Dep get(Long uuid);
	
	//更新
	void update(Dep dep);
}
