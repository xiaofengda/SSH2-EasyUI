package cn.itcast.erp.biz.impl;

import java.util.List;

import cn.itcast.erp.biz.IBaseBiz;
import cn.itcast.erp.dao.IBaseDao;
public class BaseBiz<T> implements IBaseBiz<T> {


	//注入数据访问层
	private IBaseDao<T> baseDao;
	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	/**
	 * 条件查询
	 * @param t1
	 * @param t2
	 * @param param
	 * @return
	 */
	@Override
	public List<T> getList(T t1, T t2, Object param) {
		return baseDao.getList(t1, t2, param);
		
	}
	
	/**
	 * 分页
	 * @param t1
	 * @param t2
	 * @param param
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	@Override
	public List<T> listByPage(T t1, T t2, Object param, int firstResult, int maxResults) {
		return baseDao.listByPage(t1, t2, param, firstResult, maxResults);
	}
	
	/**
	 * 分页统计
	 * @param t1
	 * @param t2
	 * @param param
	 * @return
	 */
	public Long getCount(T t1, T t2, Object param){
		return baseDao.getCount(t1, t2, param);
		
	}
	
	/**
	 * 新增
	 * @param t
	 * @return
	 */
	public void add(T t){
		baseDao.add(t);
	}
	
	/**
	 * 删除
	 * @param uuid
	 */
	public void delete(Long uuid){
		baseDao.delete(uuid);
	}
	
	/**
	 * 通过编辑查询对象
	 * @param id
	 */
	public T get(long uuid){
		return (T) baseDao.get(uuid);
	}
	
	/**
	 * 修改
	 * @param t
	 */
	public void update(T t){
		baseDao.update(t);
	}

}
