package cn.itcast.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;


import cn.itcast.erp.dao.IBaseDao;

@SuppressWarnings("unchecked")
public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	
	private Class<T> entityClass;
	
	public BaseDao(){
		//通过子类来获取父类
		Type baseDaoClass = getClass().getGenericSuperclass();
		//转成参数化的类型
		ParameterizedType pType = (ParameterizedType)baseDaoClass;
		//获取参数类型的数组
		Type[] types = pType.getActualTypeArguments();
		//得到了泛型里的T的类型
		Type targetType = types[0];
		//转成class类型
		entityClass = (Class<T>) targetType;
	}
	
	/**
	 * 条件查询
	 * @param t1
	 * @param t2
	 * @param param
	 * @return
	 */
	public List<T> getList(T t1, T t2, Object param){
		DetachedCriteria dc = getDetachedCriteria(t1, t2, param);
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
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
	public List<T> listByPage(T t1, T t2, Object param, int firstResult, int maxResults ){
		DetachedCriteria dc = getDetachedCriteria(t1, t2, param );
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
	}
	
	/**
	 * 分页统计
	 * @param t1
	 * @param t2
	 * @param param
	 * @return
	 */
	public Long getCount(T t1, T t2, Object param){
		DetachedCriteria dc = getDetachedCriteria(t1, t2, param );
		dc.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(dc);
		return list.get(0);
		
	}
	
	/**
	 * 新增
	 * @param t
	 * @return
	 */
	public void add(T t){
		this.getHibernateTemplate().save(t);
		
	}
	
	/**
	 * 删除
	 * @param uuid
	 */
	public void delete(Long uuid){
		T t = this.getHibernateTemplate().get(entityClass, uuid);
		this.getHibernateTemplate().delete(t);
	}
	
	/**
	 * 通过编辑查询对象
	 * @param id
	 */
	public T get(long uuid){
		return this.getHibernateTemplate().get(entityClass, uuid);
	}
	
	/**
	 * 修改
	 * @param t
	 */
	public void update(T t){
		this.getHibernateTemplate().update(t);
	}
	
	
	/**
	 * 由子类实现
	 * @param t1
	 * @param t2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(T t1, T t2, Object param){
		
		return null;
	}

}
