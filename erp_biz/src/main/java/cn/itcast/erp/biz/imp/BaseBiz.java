package cn.itcast.erp.biz.imp;

import java.util.List;

import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.dao.IBaseDao;

public class BaseBiz<T> implements IBaseBiz<T> {
		//数据访问注入
		private IBaseDao<T> baseDao;
		
		public void setBaseDao(IBaseDao<T> baseDao) {
			this.baseDao = baseDao;
		}

		public List<T> getList() {

			
			return baseDao.getList();
		}

		@Override
		public List<T> getList(T t1, T t2, Object param, int firstResult, int maxResults) {
			
			return baseDao.getList(t1,t2,param,firstResult,maxResults);
		}

		@Override
		public long getCount(T t1, T t2, Object param) {
			
			return baseDao.getCount(t1,t2,param);
		}

		@Override
		public void add(T t) {
			baseDao.add(t);
		}

		@Override
		public void delete(Long uuid) {
			baseDao.delete(uuid);
		}
		
		//通过编号查询对象
		@Override
		public T get(Long uuid) {
			return baseDao.get(uuid);
		}

		@Override
		public void update(T t) {
			baseDao.update(t);
		}
}
