package cn.itcast.erp.action;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.entity.Emp;

/**
 * 登陆Action类
 * @author Administrator
 *
 */
public class LoginAction extends BaseAction<Emp>{
	
	//注入业务逻辑层
	private IEmpBiz empBiz;

	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
		super.setBaseBiz(this.empBiz);
	}
	
	//属性驱动：用户名、密码
	private String username;
	private  String pwd;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	/**
	 * 登陆
	 * @param username
	 * @param pwd
	 * @return
	 */
	public void checkUser(){
		try {
			Emp loginuser = empBiz.findByUsernameAndPwd(username, pwd);
			if(loginuser == null){	//失败
				getWrite(ajaxReturn(false, "用户名或密码错误！"));
				return;
			}else {	//成功
				//保存到session中，表示用户已经登陆了
				ActionContext.getContext().getSession().put("loginuser", loginuser);
				getWrite(ajaxReturn(true, ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			getWrite(ajaxReturn(false, "登陆失败"));
		}
	}
	
	/**
	 * 显示用户名
	 */
	public void showName(){
		Emp loginuser = (Emp) ActionContext.getContext().getSession().get("loginuser");
		if(loginuser == null){
			getWrite(ajaxReturn(false, "亲！您还没有登陆。"));
		}else {
			getWrite(ajaxReturn(true, loginuser.getName()));
		}
	}
	
	/**
	 * 退出
	 */
	public void loginOut(){
		try {
			ActionContext.getContext().getSession().remove("loginuser");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
