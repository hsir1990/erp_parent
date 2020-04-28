package cn.itcast.erp.dao;

import java.util.List;

public interface IBaseDao<T> {
	List<T> getList();
	
	//条件查询
	List<T> getList(T t1, T t2, Object param, int firstResult, int maxResults);
	
	//记录条件查询的总记录数
	long getCount(T t1, T t2, Object param);
	
	//新增部门
	void add(T t);
	
	//删除
	void delete(Long uuid);

	//通过编号查询对象
	T get(Long uuid);
	
	//更新
	void update(T t);
}
