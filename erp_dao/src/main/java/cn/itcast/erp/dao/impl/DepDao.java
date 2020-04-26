package cn.itcast.erp.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;
//部门数据访问层
public class DepDao extends HibernateDaoSupport implements IDepDao{
	//查询所有的部门信息
	public List<Dep> getList() {
		return (List<Dep>) this.getHibernateTemplate().find("from Dep");
	}

}
