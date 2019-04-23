package cn.itcast.erp.biz.impl;

import org.apache.shiro.crypto.hash.Md5Hash;

import cn.itcast.erp.biz.IEmpBiz;
import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.entity.Emp;
import exception.ErpException;

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
	//散列次数
	private int hashIterations = 2;

	/**
	 * 登陆
	 * @param username
	 * @param pwd
	 * @return
	 */
	public Emp findByUsernameAndPwd(String username, String pwd){
		
		//查询前需要对密码进行加密,因为数据库中的密码是加密了的。
		pwd = encrypt(pwd, username);
		//如果登陆不了系统就把控制台打印出来的md5更新到数据库
		//System.out.println(md5);
		return empDao.findByUsernameAndPwd(username, pwd);
	}
	
	/**
	 * 新增员工加密
	 * @param t
	 * @return
	 */
	public void add(Emp emp){
		// String pwd = emp.getPwd();
		// source: 原密码
		// salt: 盐 =》扰乱码
		// hashIterations: 散列次数，加密次数
		// Md5Hash md5 = new Md5Hash(pwd, emp.getUsername(), hashIterations);
		// 取出加密后的密码
		// 设置初始密码
		String newPwd = encrypt(emp.getUsername(), emp.getUsername());
		// System.out.println(newPwd);
		// 设置成加密后的密码
		emp.setPwd(newPwd);
		// 保存到数据库中
		super.add(emp);

	}
	
	/**
	 * 修改密码
	 * @param uuid
	 * @param newPwd
	 * @param oldNew
	 */
	public void updatePwd(Long uuid, String newPwd, String oldPwd){
		//取出员工信息
		Emp emp = empDao.get(uuid);
		//加密旧密码
		String encrypted = encrypt(oldPwd, emp.getUsername());
		//旧密码是否正确匹配
		if(!encrypted.equals(emp.getPwd())){
			//抛出自定义异常
			throw new ErpException("亲！旧密码不正确。");
		}
		//修改的密码也要进行加密
		empDao.updatePwd(uuid, encrypt(newPwd, emp.getUsername()));
			
	}
	
	/**
	 * 重置密码
	 * @param uuid
	 * @param newPwd
	 */
	public void updatePwd_reset(Long uuid, String newPwd){
		// 取出员工信息
		Emp emp = empDao.get(uuid);
		empDao.updatePwd(uuid, encrypt(newPwd, emp.getUsername()));
		
		
	}
	
	/**
	 * 加密
	 * @param source	//原密码	
	 * @param salt	//盐
	 * @return
	 */
	public String encrypt(String source, String salt){
		Md5Hash md5 = new Md5Hash(source, salt, hashIterations);
		return md5.toString();
		
	}


}
