package cn.itcast.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
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
			//验证账号密码
			Emp loginuser = empBiz.findByUsernameAndPwd(username, pwd);
			if(loginuser == null){	//失败
				ajaxReturn(false, "用户名或密码错误！");
				return;
			}else {	//成功
				//保存到session中，表示用户已经登陆了
				ActionContext.getContext().getSession().put("loginuser", loginuser);
				ajaxReturn(true, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "登陆失败");
		}
	}
	
	/**
	 * 显示用户名
	 */
	public void showName(){
		//从session中获得loginuser
		Emp loginuser = (Emp) ActionContext.getContext().getSession().get("loginuser");
		if(loginuser == null){
			ajaxReturn(false, "亲！您还没有登陆。");
		}else {
			ajaxReturn(true, loginuser.getName());
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
	
	/**
	 * 返回前端操作结果
	 * @param success
	 * @param message
	 */
	public void ajaxReturn(boolean success, String message){
		//返回前端的JSON数据
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success",success);
		rtn.put("message",message);
		//JSON.toJSONString(rtn) => {"success":true,"message":'超级管理员'}
		write(JSON.toJSONString(rtn));
	}
	
	/**
	 * 输出字符串到前端
	 * @param jsonString
	 */
	public void write(String jsonString){
		try {
			//响应对象
			HttpServletResponse response = ServletActionContext.getResponse();
			//设置编码
			response.setContentType("text/html;charset=utf-8"); 
			//输出给页面
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
