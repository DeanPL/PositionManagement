
package com.infosys.demo.utils;


import java.io.Serializable;
import java.util.List;

/** 
*
*author： Dean Peng email：dean.peng@infosys.com Date:May 16, 2020 4:01:02 PM
*/
public class MenuItem implements Serializable {
	private static final long serialVersionUID = 1L;



	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单URL
	 */
	private String url;


	/**
	 * 类型 0：目录 1：菜单 2：按钮
	 */
	private Integer type;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer orderNum;


	private List<?> list;

	/**
	 * 设置：菜单名称
	 * 
	 * @param name
	 *            菜单名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：菜单名称
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置：菜单URL
	 * 
	 * @param url
	 *            菜单URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取：菜单URL
	 * 
	 * @return String
	 */
	public String getUrl() {
		return url;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 设置：菜单图标
	 * 
	 * @param icon
	 *            菜单图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取：菜单图标
	 * 
	 * @return String
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置：排序
	 * 
	 * @param orderNum
	 *            排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取：排序
	 * 
	 * @return Integer
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}
}
