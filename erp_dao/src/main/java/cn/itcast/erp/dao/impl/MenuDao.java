package cn.itcast.erp.dao.impl;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.erp.dao.IMenuDao;
import cn.itcast.erp.entity.Menu;

/**
 * 菜单数据访问层接口实现类
 * @author Administrator
 *
 */
public class MenuDao extends BaseDao<Menu> implements IMenuDao {
	
	/**
	 * 构建查询条件
	 */
	public DetachedCriteria getDetachedCriteria(Menu menu1, Menu menu2, Object param) {
		DetachedCriteria dc = DetachedCriteria.forClass(Menu.class);
		return dc;
	}


}
