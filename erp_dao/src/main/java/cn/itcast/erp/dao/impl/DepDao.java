package cn.itcast.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.dao.IDepDao;
import cn.itcast.erp.entity.Dep;

/**
 * 部门数据访问层接口实现类
 * @author Administrator
 *
 */
public class DepDao extends BaseDao<Dep> implements IDepDao {
	
	/**
	 * 构建查询条件
	 */
	public DetachedCriteria getDetachedCriteria(Dep dep1, Dep dep2, Object param) {
		DetachedCriteria dc = DetachedCriteria.forClass(Dep.class);
		if(dep1 != null){
			//根据部门名称查询
			if(dep1.getName() != null && dep1.getName().trim().length() > 0){
				dc.add(Restrictions.like("name", dep1.getName(), MatchMode.ANYWHERE));
			}
			//根据部门电话查询
			if(dep1.getTele() != null && dep1.getTele().trim().length() > 0){
				dc.add(Restrictions.like("tele", dep1.getTele(), MatchMode.ANYWHERE));
			}
		}

		return dc;
	}
	
}
