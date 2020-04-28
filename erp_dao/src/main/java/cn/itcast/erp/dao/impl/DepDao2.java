package cn.itcast.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.dao.IDepDao2;
import cn.itcast.erp.entity.Dep;
//部门数据访问层
public class DepDao2 extends BaseDao<Dep> implements IDepDao2{
	private DetachedCriteria detachedCriteria;

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

	

}
