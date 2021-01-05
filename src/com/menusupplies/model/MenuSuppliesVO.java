package com.menusupplies.model;

public class MenuSuppliesVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String menuSupcharId;
	private String menuId;
	
	public String getMenuSupcharId() {
		return menuSupcharId;
	}
	public void setMenuSupcharId(String menuSupcharId) {
		this.menuSupcharId = menuSupcharId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
