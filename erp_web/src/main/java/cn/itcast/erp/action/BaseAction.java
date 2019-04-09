package cn.itcast.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;

import cn.itcast.erp.biz.IBaseBiz;

@SuppressWarnings({"rawtypes", "unchecked"})
public class BaseAction<T> {
	
	//注入部门业务逻辑层
	
	private IBaseBiz baseBiz;
	public void setBaseBiz(IBaseBiz baseBiz) {
		this.baseBiz = baseBiz;
	}
	
	// 属性驱动：条件查询、分页
	private int page;	//当前页
	private int rows;	//每页显示条数
	private T t1;
	private T t2;
	private Object param;
	public T getT1() {
		return t1;
	}
	public void setT1(T t1) {
		this.t1 = t1;
	}
	public T getT2() {
		return t2;
	}
	public void setT2(T t2) {
		this.t2 = t2;
	}
	public Object getParam() {
		return param;
	}
	public void setParam(Object param) {
		this.param = param;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * 条件查询
	 * @param t1
	 * @param t2
	 * @param param
	 * @return
	 */
	public void getList(){
		List<T> list = baseBiz.getList(t1, t2, param);
		String jsonString = JSON.toJSONString(list);
		getWrite(jsonString);
	}
	
	/**
	 * 分页
	 * @param t1
	 * @param t2
	 * @param param
	 * @param firstResult	
	 * @param rows
	 * @return
	 */
	public void listByPage(){
		//当前页
		int firstResult = (page -1) * rows;
		//调用业务层查询分页
		List<T> list = baseBiz.listByPage(t1, t2, param, firstResult, rows);
		//调用业务层统计条数
		long total = baseBiz.getCount(t1, t2, param);
		Map<String, Object> map = new HashMap<>();
		map.put("total", total);
		map.put("rows", list);
		String jsonString = JSON.toJSONString(map);
		getWrite(jsonString);
		
	}
	
	//属性驱动：新增、修改
	private T t;
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	/**
	 * 新增
	 * @param t
	 * @return
	 */
	public void add(){
		try {
			baseBiz.add(t);
			getWrite(ajaxReturn(true, "新增成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			getWrite(ajaxReturn(false, "新增失败！"));
		}
		
	}
	
	/**
	 * 通过编辑查询对象
	 * @param id
	 */
	public void get(){
		T t = (T) baseBiz.get(id);
		String jsonString = JSON.toJSONString(t);
		getWrite(mapDate(jsonString, "t"));
		
	}
	/**
	 * 修改
	 * @param t
	 */
	public void update(){
		try {
			baseBiz.update(t);
			getWrite(ajaxReturn(true, "修改成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			getWrite(ajaxReturn(false, "修改失败！"));
		}
	}
	
	//属性驱动：删除
	private long id;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * 删除
	 * @param uuid
	 */
	public void delete(){
		try {
			baseBiz.delete(id);
			getWrite(ajaxReturn(true, "删除成功！"));
		} catch (Exception e) {
			e.printStackTrace();
			getWrite(ajaxReturn(false, "删除失败！"));
		}
		
	}
	
	/**
	 * 自定义加前缀
	 *  转换之前{"name":"总裁办","tele":"111111","uuid":58}
	 * 	转换之后{"t.tele":"111111","t.uuid":58,"t.name":"总裁办"}
	 * @param jsonString
	 * @param prefix
	 * @return
	 */
	public String mapDate(String jsonString, String prefix){
		Map<String, Object> map = JSON.parseObject(jsonString);
		Map<String, Object> newMap = new HashMap<String, Object>();
		for(String key : map.keySet()){
			newMap.put(prefix+"."+key, map.get(key));
		}
		return JSON.toJSONString(newMap);
	}
	
	/**
	 * 封装页面相应
	 * @param jsonString
	 */
	public void getWrite(String jsonString){
		try {
			//相应对象
			HttpServletResponse  response = ServletActionContext.getResponse();
			//设置编码
			response.setContentType("text/html;charset=utf-8");
			//输出给页面
			response.getWriter().println(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 返回ajax结构返回体
	 */
	public String ajaxReturn(boolean success, String message){
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("success", success);
		rtn.put("message", message);
		String jsonString = JSON.toJSONString(rtn);
		return jsonString;
		
	}

}
