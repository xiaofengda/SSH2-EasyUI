package cn.itcast.erp.action;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.entity.Emp;

/**
 * 员工Action类
 * @author Administrator
 *
 */
public class EmpAction extends BaseAction<Emp> {
	
	//注入业务逻辑层
	private IEmpBiz empBiz;

	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
		super.setBaseBiz(this.empBiz);
	}
	

}
