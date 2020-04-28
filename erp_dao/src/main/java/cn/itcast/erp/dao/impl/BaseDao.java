package cn.itcast.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.dao.IBaseDao;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	private Class<T> entityClass;
	
	public BaseDao() {
		//通过子类获取父类
		Type baseDaoClass = getClass().getGenericSuperclass();
		//转成参数化的类型
		ParameterizedType pType= (ParameterizedType)baseDaoClass;
		//获取参数类型的数组
		Type[] types = pType.getActualTypeArguments();
		//得到了泛型里的T的类型
		Type targetType = types[0];
		//转成class类型
		entityClass = (Class<T>)targetType;
	}

	//查询所有的部门信息
	@Override
	public List<T> getList() {
		return (List<T>) this.getHibernateTemplate().find("from Dep");
	}

	//条件查询
	@Override
	public List<T> getList(T t1, T t2, Object param, int firstResult, int maxResults) {
		//QBC离线查询
		DetachedCriteria dc = getDetachedCriteria(t1);
		
		return  (List<T>) this.getHibernateTemplate().findByCriteria(dc,firstResult,maxResults);
	}

	//记录条件查询的总记录数
	@Override
	public long getCount(T t1, T t2, Object param) {
		DetachedCriteria dc = getDetachedCriteria(t1);
		//因为是聚合函数所以才这么写
		dc.setProjection(Projections.rowCount());
		//return (long) this.getHibernateTemplate().findByCriteria(dc).get(0);
		//或者
		List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}
	
	//抽成方法   由子类实现
	private DetachedCriteria getDetachedCriteria(T t1) {
		
		return null;
	}

	//新增
	@Override
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}
	
	//删除
	public void delete(Long uuid) {
		//让对象进入持久化状态
		T t = this.getHibernateTemplate().get(entityClass, uuid);
		//删除持久化状态
		this.getHibernateTemplate().delete(t);
	}

	//通过编号查询对象
	@Override
	public T get(Long uuid) {
		return getHibernateTemplate().get(entityClass, uuid);
	}

	//更新
	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t); 
	}
	

}
 	