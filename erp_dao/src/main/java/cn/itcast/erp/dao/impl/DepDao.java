package cn.itcast.erp.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;
//部门数据访问层
public class DepDao extends HibernateDaoSupport implements IDepDao{
	private DetachedCriteria detachedCriteria;

	//查询所有的部门信息
	@Override
	public List<Dep> getList() {
		return (List<Dep>) this.getHibernateTemplate().find("from Dep");
	}

	//条件查询
	@Override
	public List<Dep> getList(Dep dep1, Dep dep2, Object param, int firstResult, int maxResults) {
		//QBC离线查询
		DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		
		if(null != dep1) {
			//是否输入部门名称
			if(null != dep1.getName() && dep1.getName().trim().length() > 0) {
				//MatchMode.ANYWHERE ==> %  %
				//MatchMode.END ==> %name
				//MatchMode.START ==> name%
				dc.add(Restrictions.like("name", dep1.getName(),MatchMode.ANYWHERE));
			}
			//是否输入部门电话
			if(null != dep1.getTele() && dep1.getTele().trim().length() > 0) {
				dc.add(Restrictions.like("tele", dep1.getTele(),MatchMode.ANYWHERE));
			}
		}
		
		return  (List<Dep>) this.getHibernateTemplate().findByCriteria(dc,firstResult,maxResults);
	}

	//记录条件查询的总记录数
	@Override
	public long getCount(Dep dep1, Dep dep2, Object param) {
		DetachedCriteria dc = getDetachedCriteria(dep1);
		//因为是聚合函数所以才这么写
		dc.setProjection(Projections.rowCount());
		//return (long) this.getHibernateTemplate().findByCriteria(dc).get(0);
		//或者
		List<Long> list = (List<Long>)getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
	}
	
	//抽成方法
	private DetachedCriteria getDetachedCriteria(Dep dep1) {
		//QBC离线查询
		DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		
		if(null != dep1) {
			//是否输入部门名称
			if(null != dep1.getName() && dep1.getName().trim().length() > 0) {
				//MatchMode.ANYWHERE ==> %  %
				//MatchMode.END ==> %name
				//MatchMode.START ==> name%
				dc.add(Restrictions.like("name", dep1.getName(),MatchMode.ANYWHERE));
			}
			//是否输入部门电话
			if(null != dep1.getTele() && dep1.getTele().trim().length() > 0) {
				dc.add(Restrictions.like("tele", dep1.getTele(),MatchMode.ANYWHERE));
			}
		}
		return dc;
	}

	//新增
	@Override
	public void add(Dep dep) {
		this.getHibernateTemplate().save(dep);
	}
	
	//删除
	public void delete(Long uuid) {
		//让对象进入持久化状态
		Dep dep = this.getHibernateTemplate().get(Dep.class, uuid);
		//删除持久化状态
		this.getHibernateTemplate().delete(dep);
	}

	//通过编号查询对象
	@Override
	public Dep get(Long uuid) {
		return getHibernateTemplate().get(Dep.class, uuid);
	}

	//更新
	@Override
	public void update(Dep dep) {
		this.getHibernateTemplate().update(dep); 
	}

}
