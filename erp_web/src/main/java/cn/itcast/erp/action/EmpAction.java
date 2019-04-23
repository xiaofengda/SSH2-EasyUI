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
	
	//修改密码属性
	private String newPwd;	//新密码
	private String oldPwd;	//旧密码

	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	
	/**
	 * 修改密码
	 */
	public void updatePwd(){
		//获取当前登陆名
		Emp loginUser = getLoginUser();
		
		if(loginUser == null){
			ajaxReturn(false, "亲！您还没有登陆！");
			return;
		}
		try {
			this.empBiz.updatePwd(loginUser.getUuid(), newPwd, oldPwd);
			ajaxReturn(true, "密码修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "密码修改失败");
		}
		
	}
	
	/**
	 * 重置密码
	 */
	public void updatePwd_reset(){
		//获取当前登陆名
		Emp loginUser = getLoginUser();
		if(loginUser == null){
			ajaxReturn(false, "亲！您还没有登陆！");
			return;
		}
		try {
			this.empBiz.updatePwd_reset(loginUser.getUuid(), newPwd);
			ajaxReturn(true, "密码重置成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "密码重置失败");
		}
	}
	

}
