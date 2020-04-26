package cn.itcast.erp.biz.imp;

import java.util.List;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;
//部门业务实现
public class DepBiz implements IDepBiz {
	
	//数据访问注入
	private IDepDao depDao;
	
	public void setDepDao(IDepDao depDao) {
		this.depDao = depDao;
	}

	public List<Dep> getList() {

		
		return depDao.getList();
	}

}
