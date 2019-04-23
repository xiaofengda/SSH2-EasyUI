package cn.itcast.erp.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.dao.IEmpDao;
import cn.itcast.erp.entity.Emp;

/**
 * 员工数据访问层接口实现类
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class EmpDao extends BaseDao<Emp> implements IEmpDao {

	public DetachedCriteria getDetachedCriteria(Emp emp1, Emp emp2, Object param) {
		DetachedCriteria dc = DetachedCriteria.forClass(Emp.class);
		if(emp1 != null){
			//根据登录名查询
			if(emp1.getUsername() != null && emp1.getUsername().trim().length() > 0){
				dc.add(Restrictions.like("username", emp1.getUsername(), MatchMode.ANYWHERE));
			}
			//根据真实姓名查询
			if(emp1.getName() != null && emp1.getName().trim().length() > 0){
				dc.add(Restrictions.like("name", emp1.getName(), MatchMode.ANYWHERE));
			}
			//根据性别查询
			if(emp1.getGender() != null){
				dc.add(Restrictions.eq("gender", emp1.getGender()));
			}
			//根据邮件查询
			if(emp1.getEmail() != null && emp1.getEmail().trim().length() > 0){
				dc.add(Restrictions.like("email", emp1.getEmail(), MatchMode.ANYWHERE));
			}
			//根据电话查询
			if(emp1.getTele() != null && emp1.getTele().trim().length() > 0){
				dc.add(Restrictions.like("tele", emp1.getTele(), MatchMode.ANYWHERE));
			}
			//根据地址查询
			if(emp1.getAddress() != null && emp1.getAddress().trim().length() > 0){
				dc.add(Restrictions.like("address", emp1.getAddress(), MatchMode.ANYWHERE));
			}
			//根据部门查询
			if(emp1.getDep() != null && emp1.getDep().getUuid() != null){
				dc.add(Restrictions.eq("dep", emp1.getDep()));
			}
			
			//根据出生日期 起始日期
			if(emp1.getBirthday() != null){
				dc.add(Restrictions.ge("birthday", emp1.getBirthday()));
			}
			
		}
		if(emp2 != null){
			//根据出生日期 结束日期
			if(emp2.getBirthday() != null){
				dc.add(Restrictions.le("birthday", emp2.getBirthday()));
			}
		}
		return dc;
	}
	
	/**
	 * 登陆
	 * @param username
	 * @param pwd
	 * @return
	 */
	public Emp findByUsernameAndPwd(String username, String pwd){
		List<Emp> list = (List<Emp>) this.getHibernateTemplate().find("from Emp where username=? and pwd=?", username, pwd);
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 修改密码
	 * @param uuid
	 * @param newPwd
	 * @param oldNew
	 */
	public void updatePwd(Long uuid, String newPwd){
		String hql= "update Emp set pwd = ? where uuid = ?";
		this.getHibernateTemplate().bulkUpdate(hql, newPwd, uuid);
	}
	
}
