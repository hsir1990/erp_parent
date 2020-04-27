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

	@Override
	public List<Dep> getList(Dep dep1, Dep dep2, Object param, int firstResult, int maxResults) {
		
		return depDao.getList(dep1,dep2,param,firstResult,maxResults);
	}

	@Override
	public long getCount(Dep dep1, Dep dep2, Object param) {
		
		return depDao.getCount(dep1,dep2,param);
	}

	@Override
	public void add(Dep dep) {
		depDao.add(dep);
	}

	@Override
	public void delete(Long uuid) {
		depDao.delete(uuid);
	}
	
	//通过编号查询对象
	@Override
	public Dep get(Long uuid) {
		return depDao.get(uuid);
	}

	@Override
	public void update(Dep dep) {
		depDao.update(dep);
	}

}
