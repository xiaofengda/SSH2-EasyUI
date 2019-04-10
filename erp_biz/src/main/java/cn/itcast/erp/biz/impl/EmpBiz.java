package cn.itcast.erp.biz.impl;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.entity.Emp;

/**
 * 员工业务逻辑层接口实现类
 * @author Administrator
 *
 */
public class EmpBiz extends BaseBiz<Emp> implements IEmpBiz {
	
	//注入数据访问层
	private IEmpDao empDao;

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
		super.setBaseDao(this.empDao);
	}
	
	/**
	 * 登陆
	 * @param username
	 * @param pwd
	 * @return
	 */
	public Emp findByUsernameAndPwd(String username, String pwd){
		return empDao.findByUsernameAndPwd(username, pwd);
	}
	

}
