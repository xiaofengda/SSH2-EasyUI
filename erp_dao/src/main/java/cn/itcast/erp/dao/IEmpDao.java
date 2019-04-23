package cn.itcast.erp.dao;

import cn.itcast.erp.entity.Emp;

/**
 * 员工数据访问接口
 * @author Administrator
 *
 */
public interface IEmpDao extends IBaseDao<Emp> {
	
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
	 */
	public void updatePwd(Long uuid, String newPwd);
	
}
