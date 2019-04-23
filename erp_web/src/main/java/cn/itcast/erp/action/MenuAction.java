package cn.itcast.erp.action;

import com.alibaba.fastjson.JSON;

import cn.itcast.erp.biz.IMenuBiz;
import cn.itcast.erp.entity.Menu;

/**
 * 菜单Action类
 * @author Administrator
 *
 */
public class MenuAction extends BaseAction<Menu> {
	
	//注入业务层
	private IMenuBiz menuBiz;
	public void setMenuBiz(IMenuBiz menuBiz) {
		this.menuBiz = menuBiz;
		super.setBaseBiz(this.menuBiz);
	}
	
	/**
	 * 获取菜单树
	 */
	public void getMenuTree(){
		//查询顶级菜单，即可带出其下所有子菜单
		Menu menu = menuBiz.get("0");
		String jsonString = JSON.toJSONString(menu, true);
		write(jsonString);
	}
	

}
