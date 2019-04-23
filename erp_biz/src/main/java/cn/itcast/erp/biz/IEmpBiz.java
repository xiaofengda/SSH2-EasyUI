package cn.itcast.erp.biz;

import cn.itcast.erp.entity.Emp;

/**
 * 员工业务逻辑层接口
 * @author Administrator
 *
 */
public interface IEmpBiz extends IBaseBiz<Emp> {
	
	/**
	 * 登陆
	 * @param username
	 * @param pwd
	 * @return
	 */
	public Emp findByUsernameAndPwd(String username, String pwd);

	/**
	 * 修改密码
	 * @param uuid
	 * @param newPwd
	 * @param oldNew
	 */
	public void updatePwd(Long uuid, String newPwd, String oldNew);
	
	/**
	 * 重置密码
	 * @param uuid
	 * @param newPwd
	 */
	public void updatePwd_reset(Long uuid, String newPwd);


}
