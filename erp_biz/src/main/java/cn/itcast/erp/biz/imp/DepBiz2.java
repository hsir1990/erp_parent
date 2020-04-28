package cn.itcast.erp.biz.imp;

import java.util.List;

import cn.itcast.erp.biz.IDepBiz;
import cn.itcast.erp.biz.IDepBiz2;
import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.dao.IDepDao2;
import cn.itcast.erp.entity.Dep;
//部门业务实现
public class DepBiz2 extends BaseBiz<Dep> implements IDepBiz2 {
	
	//数据访问注入
	private IDepDao2 depDao2;
	
	public void setDepDao2(IDepDao2 depDao2) {
		this.depDao2 = depDao2;
		super.setBaseDao(this.depDao2);
	}
	
}
