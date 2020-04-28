package cn.itcast.erp.biz;

import java.util.List;

public interface IBaseBiz<T> {
	//查询所有部门接口类
		List<T> getList();
		
		//条件查询
		List<T> getList(T t1, T t2, Object param, int firstResult, int maxResults);
		
		long getCount(T t1, T t2, Object param);
		
		void add(T t);
		
		void delete(Long uuid);
		
		T get(Long uuid);
		
		void update(T t);
}
